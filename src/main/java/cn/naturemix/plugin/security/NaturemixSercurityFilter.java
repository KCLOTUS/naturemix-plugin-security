package cn.naturemix.plugin.security;

import cn.naturemix.plugin.security.realm.NaturemixCustomRealm;
import cn.naturemix.plugin.security.realm.NaturemixJdbcRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 安全过滤器
 * @author flytoyou
 * @version 1.0.0
 */
public class NaturemixSercurityFilter extends ShiroFilter {

    @Override
    public void init() throws Exception{
        WebSecurityManager webSecurityManager = super.getSecurityManager();
        //设置Reaml 可同时支持多个Reaml 并按照先后顺序用逗号分割
        setReaml(webSecurityManager);
        //设置 Cache 用于减少数据库查询次数 降低I/O访问
        setCache(webSecurityManager);
    }

    private void setReaml(WebSecurityManager webSecurityManager){
        //读取naturemix.plugin.security.reamls配置项
        String securityReamls = SecurityConfig.getRealms();
        if (securityReamls != null){
            //根据逗号进行拆分
            String [] securityRealmArray = securityReamls.split(",");
            if (securityRealmArray.length > 0){
                //使Realm具备唯一性与顺序性
                Set<Realm> realms = new LinkedHashSet<Realm>();
                for (String securityRealm :securityRealmArray){
                    if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)){
                        //添加基于JDBC的Realm,配置相关SQL查询语句
                        addJdbcReaml(realms);
                    }else if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)){
                        //添加基于定制的的Realm,实现NaturemixSecurity接口
                        addCustomReaml(realms);
                    }
                }
                RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
                realmSecurityManager.setRealms(realms);//设置Reaml
            }
        }
    }

    private void addJdbcReaml(Set<Realm> realms) {
        //添加自己实现的基于JDBC的Reaml
        NaturemixJdbcRealm naturemixJdbcRealm = new NaturemixJdbcRealm();
        realms.add(naturemixJdbcRealm);
    }

    private void addCustomReaml(Set<Realm> realms) {
        //读取naturemix.plugin.security.custom.class配置项
        NaturemixSecurity naturemixSecurity = SecurityConfig.getNaturemixSecurity();
        //添加自己实现的Reaml
        NaturemixCustomRealm naturemixCustomRealm = new NaturemixCustomRealm(naturemixSecurity);
        realms.add(naturemixCustomRealm);
    }

    private void setCache(WebSecurityManager webSecurityManager) {
        //读取naturemix.plugin.security.cache配置项
        if (SecurityConfig.isCache()){
            CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;
            //使用基于内存的CacheManager
            CacheManager cacheManager = new MemoryConstrainedCacheManager();
            cachingSecurityManager.setCacheManager(cacheManager);
        }
    }

}
