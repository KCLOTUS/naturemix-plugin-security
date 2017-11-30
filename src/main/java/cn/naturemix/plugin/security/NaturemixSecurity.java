package cn.naturemix.plugin.security;

import java.util.Set;

/**
 * Naturemix Security接口
 * 可在应用中实现该接口，或者在naturemix.properties文件中提供以下基于SQL的配置项:
 * <ul>
 *     <li>naturemix.plugin.security.jdbc,authc_query:根据用户名获取密码</li>
 *     <li>naturemix.plugin.security.jdbc,roles_query:根据用户名获取角色名合集</li>
 *     <li>naturemix.plugin.security.jdbc,permission_query:根据角色名获取权限名合集</li>
 * <ul/>
 * @author flytoyou
 * @version 1.0.0
 */
public interface NaturemixSecurity {

    /**
     * 根据用户名获取密码
     * @param username 用户名
     * @return 密码
     */
    String getPassword(String username);

    /**
     * 根据用户名获取角色名合集
     * @param username 用户名
     * @return 角色名合集
     */
    Set<String> getRoleNameSet(String username);

    /**
     * 根据角色名获取权限名合集
     * @param roleName 角色名
     * @return 取权限名合集
     */
    Set<String> getPermissionNameSet(String roleName);

}
