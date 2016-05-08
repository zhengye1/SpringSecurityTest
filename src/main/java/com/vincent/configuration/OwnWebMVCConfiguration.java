package com.vincent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = "com.vincent")
public class OwnWebMVCConfiguration extends WebMvcConfigurerAdapter{
    
   @Bean
   public ViewResolver viewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setViewClass(JstlView.class);
       viewResolver.setPrefix("/WEB-INF/views/");
       viewResolver.setSuffix(".jsp");

       return viewResolver;
   }

    /*
    * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
    */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**").addResourceLocations("/static/");
   }
}