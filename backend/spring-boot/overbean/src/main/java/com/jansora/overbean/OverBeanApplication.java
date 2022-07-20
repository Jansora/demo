package com.jansora.overbean;

import com.jansora.overbean.service.AsianService;
import com.jansora.overbean.service.EuropeanService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class OverBeanApplication implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	@Autowired
	AsianService asianService;

	@Autowired
	EuropeanService europeanService;



	public static void main(String[] args) {
		SpringApplication.run(OverBeanApplication.class, args);

		context.getBean(AsianService.class).sayHello();
		context.getBean(EuropeanService.class).sayHello();
	}

}
