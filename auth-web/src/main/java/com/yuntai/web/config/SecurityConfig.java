package com.yuntai.web.config;

import com.yuntai.web.filter.JwtFilter;
import com.yuntai.web.filter.JwtLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * @author yuanyemustang
 * @title
 * @description
 * @Data 2020/6/8 3:49 下午
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    public void configure(WebSecurity web){
        web.ignoring()
                .antMatchers(
                        "/favicon.ico",
                        "/axios",
                        "/vue",
                        "/MyLogin",
                        "/abc",
                        "/yt-role/**",
                        "/yt-perms/**"
                );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("$2a$10$KYHiFjZt6wqpur3M7SXYr.3TItZEitrisUht3gzzntGhYNsKz03IS")
                .roles("user")
                .and()
                .withUser("admin")
                .password("$2a$10$KYHiFjZt6wqpur3M7SXYr.3TItZEitrisUht3gzzntGhYNsKz03IS")
                .roles("admin");
    }

    /**
     * http过滤器
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                //匹配Url
                .antMatchers( "/hello" )
                //访问hello必须有user权限
                .hasRole( "user" )

                //访问admin必须有admin权限
                .antMatchers( "/admin" )
                .hasRole( "admin" )

                //post请求的login全部放行
                .antMatchers( HttpMethod.POST, "/login" )
                .permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilterBefore( new JwtLoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class )
                .addFilterBefore( new JwtFilter(), UsernamePasswordAuthenticationFilter.class )
                .csrf().disable();



    }
}
