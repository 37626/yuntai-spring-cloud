package com.yuntai.web.config;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroCnofig {
    private final static Logger logger = LoggerFactory.getLogger(com.yuntai.web.config.ShiroCnofig.class);

    // 下面两个方法对 注解权限起作用有很大的关系，请把这两个方法，放在配置的最上面
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    //将自己的验证方式加入容器
    @Bean
    public UserRealm myRealm() {
        System.out.println( "注入 realm" );
        UserRealm myRealm = new UserRealm();
        return myRealm;
    }

    //配置shiro session 的一个管理器
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间
        sessionManager.setGlobalSessionTimeout(60*60*1000);
        return sessionManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm( myRealm() );
        //defaultWebSecurityManager.setSessionManager( getDefaultWebSessionManager() );
        return defaultWebSecurityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }



    //设置过滤条件和拦截条件
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager  securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //获取nacos中的配置


       /* NacosService nacosService = new NacosService();
        String s = nacosService.getConfig();
        System.err.println(s);*/
        Map<String,String> map = new LinkedHashMap<String, String>();

        //授权验证 授权认证要写在登录认证之前
        map.put("/user/add","perms[user:add]");
        //角色验证
       // map.put("/user/*","roles[admin]");
        //登录验证
       // map.put("/test","authc");

        //登录验证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置登录验证失败的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置未授权的跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized ");
        return shiroFilterFactoryBean;
    }

}
