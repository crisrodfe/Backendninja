package com.crisrodfe.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.crisrodfe.component.RequestTimeInterceptor;

/**
 * 
 * @author CrisRodFe
 * Clase de configuración para poder tener un Interceptor en nuestro proyecto.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter
{

	@Autowired
	@Qualifier("requestTimeInterceptor")
	private RequestTimeInterceptor requestTimeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(requestTimeInterceptor);
	}
	
}
