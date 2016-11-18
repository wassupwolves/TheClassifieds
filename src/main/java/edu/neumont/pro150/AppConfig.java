package edu.neumont.pro150;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import edu.neumont.pro150.hibernate5.QueryUtilConnectionException;
import edu.neumont.pro150.hibernate5.emqueryutil.EMQueryUtil;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "edu.neumont.pro150")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean(name="multipartResolver")
	@Qualifier(value="multipartResolver")
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		//cmr.setDefaultEncoding("utf-8");
		cmr.setResolveLazily(true);
		//cmr.setMaxUploadSize(-1);
		return cmr;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	public EMQueryUtil getQueryUtil() throws QueryUtilConnectionException{
		EMQueryUtil.RegisterEMF("fahim_list", "edu.neumont.pro150.datamodels");
		return new EMQueryUtil("fahim_list");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/CSS/**").addResourceLocations("/CSS/");
	}

}