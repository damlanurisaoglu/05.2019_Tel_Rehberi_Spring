package com.telRehber.spring.config;

import com.telRehber.Interceptor.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.telRehber.spring.config"})
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/images/")
                .setCachePeriod(3600) // saniye
                .resourceChain(true) // cacheResources parametresi
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).addPathPatterns("/saveOrUpdatePersonel.ajax");
        registry.addInterceptor(new Interceptor()).addPathPatterns("/saveOrUpdateLogin.ajax");
        registry.addInterceptor(new Interceptor()).addPathPatterns("/savaOrUpdateBirim.ajax");
        registry.addInterceptor(new Interceptor()).addPathPatterns("/savaOrUpdateUnvan.ajax");
    }
}




