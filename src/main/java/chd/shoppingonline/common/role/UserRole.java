package chd.shoppingonline.common.role;
/*
 * @ClassName UserRole
 * @Author 从林
 * @Date 2019-06-03 13:45
 * @Description
 */


import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum UserRole {
    ROLE_ADMIN ((short)1,"ROLE_ADMIN"),
    ROLE_BUYER((short)(1 << 1), "ROLE_BUYER"),
    ROLE_SELLER((short)(1 << 2), "ROLE_SELLER");

    private short shortValue;
    private String stringValue;

    UserRole(short shortValue, String stringValue){
        this.shortValue = shortValue;
        this.stringValue = stringValue;
    }

    public short getShortValue(){
        return shortValue;
    }

    public String getStringValue(){
        return stringValue;
    }

    public String getTrimStringValue(){
        return stringValue.substring(5);
    }

    public static List<SimpleGrantedAuthority> getAuthorities(int value){
        return getRoleString(value).parallelStream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public static List<String> getRoleString(int value){
        List<String> list = new ArrayList<>();

        for(UserRole userRole : UserRole.values()){
            if((value & userRole.getShortValue()) != 0)
                list.add(userRole.getStringValue());
        }
        return list;
    }

    public static String getFirstRoleString(int value){

        for(UserRole userRole : UserRole.values()){
            if((value & userRole.getShortValue()) != 0)
                return userRole.getStringValue();
        }
        return null;
    }
}