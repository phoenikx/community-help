package com.phoenikx.communityhelp.config;

import com.google.common.collect.Lists;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public Filter userContextFilter() {
        return new UserContextFilter();
    }

    @Bean
    public FilterRegistrationBean userFilterRegistration() {
        FilterRegistrationBean result = new FilterRegistrationBean();
        result.setFilter(this.userContextFilter());
        result.setUrlPatterns(Lists.newArrayList("/api/posts/*"));
        result.setName("User context Filter");
        result.setOrder(1);
        return result;
    }

    @Bean(destroyMethod = "destroy")
    public ThreadLocalTargetSource threadLocalTenantStore() {
        ThreadLocalTargetSource result = new ThreadLocalTargetSource();
        result.setTargetBeanName("userStore");
        return result;
    }

    @Primary
    @Bean(name = "proxiedThreadLocalTargetSource")
    public ProxyFactoryBean proxiedThreadLocalTargetSource(ThreadLocalTargetSource threadLocalTargetSource) {
        ProxyFactoryBean result = new ProxyFactoryBean();
        result.setTargetSource(threadLocalTargetSource);
        return result;
    }

    @Bean(name = "userStore")
    @Scope(scopeName = "prototype")
    public UserContextStore userStore() {
        return new UserContextStore();
    }

}
