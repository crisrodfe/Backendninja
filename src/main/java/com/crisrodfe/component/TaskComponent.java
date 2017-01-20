package com.crisrodfe.component;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * @author CrisRodFe
 * Ejemplo sencillo de clase para realizar una tarea de manera repetitiva, en este caso lanza un log por consola cada 5 segundos.
 */
@Component("taskComponent")
@EnableScheduling
public class TaskComponent 
{
	private static final Log LOG = LogFactory.getLog(TaskComponent.class);
	
	@Scheduled(fixedDelay=5000)
	public void doTask()
	{
		LOG.info("TIME IS: " + new Date());
	}
}
