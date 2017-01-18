package com.crisrodfe.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//Cada petición que se haga entrará por esta clase/componente
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor  extends HandlerInterceptorAdapter
{
	
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

	//Se ejecuta antes de entrar en el método del controlador
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		
		return true;
	}
	
	
	//Se ejecuta antes de devolver la vista del navegador,al final del método del controlador, antes justo del return.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		long startTime = (long) request.getAttribute("startTime");
		
		LOG.info("-- REQUEST URL: '" + request.getRequestURL().toString() + "' --TOTAL TIME: '" 
		+ System.currentTimeMillis() + startTime + "ms'");
		
	}



}
