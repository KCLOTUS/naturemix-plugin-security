package cn.naturemix.plugin.security.realm;

import cn.naturemix.framework.helper.DatabaseHelper;
import cn.naturemix.plugin.security.SecurityConfig;
import cn.naturemix.plugin.security.password.Md5CredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * 基于naturemix的JDBC Reaml(需要提供相关naturemix.plugin.security.jdbc)
 * @author flytoyou
 * @version 1.0.0
 */
public class NaturemixJdbcRealm extends JdbcRealm {

    public NaturemixJdbcRealm(){
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());//使用MD5加密算法
    }

}
