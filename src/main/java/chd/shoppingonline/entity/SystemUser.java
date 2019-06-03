package chd.shoppingonline.entity;
/*
 * @ClassName SystemUser
 * @Author 从林
 * @Date 2019-03-14 16:40
 * @Description 实现UserDetails，检查用户授权信息
 */

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Builder(toBuilder = true)
@Data
public class SystemUser implements UserDetails {
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private List<SimpleGrantedAuthority> authorities;
    private User user;

    /*以下方法实现 UserDetails 接口 */
    //用户是否可用
    @Override
    public boolean isEnabled(){
        return this.user.isEnabled();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }
}
