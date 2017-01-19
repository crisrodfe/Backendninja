package com.crisrodfe.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.crisrodfe.repository.LogRepository;

//Cada petición que se haga entrará por esta clase/componente
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor  extends HandlerInterceptorAdapter
{
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
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
		String url = request.getRequestURL().toString();
		String username = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null && auth.isAuthenticated())
		{
			username = auth.getName();
		}
		
		logRepository.save(new com.crisrodfe.entity.Log(new Date(),auth.getDetails().toString(),username,url));
		
		LOG.info("URL to : '" + url + "' -- in: '" + System.currentTimeMillis() + startTime + "ms'");
		
	}



}
