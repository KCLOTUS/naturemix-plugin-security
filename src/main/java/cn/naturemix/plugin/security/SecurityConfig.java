package cn.naturemix.plugin.security;

import cn.naturemix.framework.helper.ConfigHelper;
import cn.naturemix.framework.util.ReflectionUtil;

/**
 * 从配置文件中获取相关属性
 * @author flytoyou
 * @version 1.0.0
 */
public class SecurityConfig {

    public static String getRealms(){
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    public static NaturemixSecurity getNaturemixSecurity(){
        String className = ConfigHelper.getString(SecurityConstant.SMART_SECURITY);
        return (NaturemixSecurity) ReflectionUtil.newInstance(className);
    }

    public static String getJdbcAuthcQuery(){
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery(){
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery(){
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSION_QUERY);
    }

    public static boolean isCache(){
        return ConfigHelper.getBoolean(SecurityConstant.CACHE);
    }

}
