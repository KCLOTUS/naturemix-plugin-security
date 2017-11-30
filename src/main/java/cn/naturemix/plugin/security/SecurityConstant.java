package cn.naturemix.plugin.security;

/**
 * 常量接口
 * @author flytoyou
 * @version 1.0.0
 */
public interface SecurityConstant {

    String REALMS = "naturemix.plugin.security.realms";
    String REALMS_JDBC = "jdbc";
    String REALMS_CUSTOM = "custom";

    String SMART_SECURITY = "naturemix.plugin.security.custom.class";

    String JDBC_AUTHC_QUERY = "naturemix.plugin.security.jdbc.authc_query";
    String JDBC_ROLES_QUERY = "naturemix.plugin.security.jdbc.roles_query";
    String JDBC_PERMISSION_QUERY = "naturemix.plugin.security.jdbc.permission_query";

    String CACHE = "naturemix.plugin.security.cache";

}
