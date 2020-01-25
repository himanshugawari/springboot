package com.gawari._himanshu.springbootsimpleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@ComponentScan("com.gawari._himanshu.springbootsimpleapi")    default in this package
public class SpringBootSimpleApiApplication {

	public static void main(String[] args) {
		// ApplicationContext applicationContext =
		SpringApplication.run(SpringBootSimpleApiApplication.class, args);

		/*
		 * Object[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		 * for (Object t : beanDefinitionNames) System.out.println(t.toString());
		 */

	}

	@Profile("dev")
	@Bean
	public String dummy() {
		return "something";
	}

}
