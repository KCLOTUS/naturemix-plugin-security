package cn.naturemix.plugin.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Naturemix Security 插件
 * @author flytoyou
 * @version 1.0.0
 */
public class NaturemixSecurityPlugin implements ServletContainerInitializer {

    public void onStartup(Set<Class<?>> handlesTypes, ServletContext servletContext) throws ServletException {
        //设置初始化参数
        servletContext.setInitParameter("shiroConfigLocations","classpath:naturemix-security.ini");
        //注册Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        //注册Filter
        FilterRegistration.Dynamic naturemixSecurityFilter = servletContext.addFilter("NaturemixSecurityFilter",NaturemixSercurityFilter.class);
        naturemixSecurityFilter.addMappingForUrlPatterns(null,false,"/*");
    }

}
