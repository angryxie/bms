package com.wwxn.config;

import com.wwxn.bms.po.Role;
import com.wwxn.bms.po.User;
import com.wwxn.bms.service.RoleService;
import com.wwxn.bms.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Value("realmName")
    private String realmName;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user= (User) principalCollection.getPrimaryPrincipal();
        List<Role> roles=roleService.getRolesByUserId(user.getUserId());
        Set<String> rolesString=new HashSet<>(roles.size());
        Set<String> permissions=new HashSet<>();
        if (!CollectionUtils.isEmpty(roles)){
            roles.forEach(r->{
                rolesString.add(r.getRoleName());
                if (!CollectionUtils.isEmpty(r.getPermissions())){
                    r.getPermissions().forEach(p->permissions.add(p.getPermissionName()));
                }
            });
        }
        SimpleAuthorizationInfo  authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(rolesString);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        User user=userService.getUserByUsername(username);
        if (user==null)
            return null;
        AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),realmName);
        return authenticationInfo;
    }


}
