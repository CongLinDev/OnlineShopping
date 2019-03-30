package chd.shoppingonline.filter;
/*
 * @ClassName JWTAuthenticationTokenFilter
 * @Author 从林
 * @Date 2019-03-30 20:14
 * @Description
 */

import chd.shoppingonline.entity.SystemUser;
import chd.shoppingonline.util.JWTUtil;
import chd.shoppingonline.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        String authHeader = request.getHeader(jwtUtil.getHeader());
        try {
            if (authHeader != null && StringUtils.isNotEmpty(authHeader)) {
                String username = jwtUtil.getUsernameFromToken(authHeader);
                jwtUtil.validateToken(authHeader);//验证令牌
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    if (jwtUtil.validateToken(authHeader)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
            chain.doFilter(request, response);
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            Map<String,Object> map = jwtUtil.parseJwtPayload(authHeader);
            String userid = (String)map.get("userid");
            //这里的方案是如果令牌过期了，先去判断redis中存储的令牌是否过期，如果过期就重新登录，如果redis中存储的没有过期就可以
            //继续生成token返回给前端存储方式key:userid,value:令牌
            String redisResult = redisUtil.get(userid);
            String username= (String) map.get("sub");
            if(StringUtils.isNoneEmpty(redisResult)){
                SystemUser systemUser = new SystemUser();
                systemUser.setId(Long.parseLong(userid));
                systemUser.setUsername(username);
                Map<String, Object> claims = new HashMap<>(2);
                claims.put("sub", systemUser.getUsername());
                claims.put("userid", systemUser.getId());
                claims.put("created", new Date());
                String token = jwtUtil.generateToken(systemUser);
                //更新redis中的token
                //首先获取key的有效期，把新的token的有效期设为旧的token剩余的有效期
                redisUtil.setAndTime(userid,token,redisUtil.getExpireTime(userid));
                if (token != null && StringUtils.isNotEmpty(token)) {
                    jwtUtil.validateToken(token);//验证令牌
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                        if (jwtUtil.validateToken(token)) {
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
                response.setHeader("newToken",token);
                response.addHeader("Access-Control-Expose-Headers","newToken");
                response.setContentType("application/json;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                try {
                    chain.doFilter(request, response);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ServletException e1) {
                    e1.printStackTrace();
                }
            } else {
                response.addHeader("Access-Control-Allow-origin","http://localhost:8080");
                response.setContentType("application/json;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString("抱歉，您的登录信息已过期，请重新登录"));
                System.out.println("redis过期");
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
