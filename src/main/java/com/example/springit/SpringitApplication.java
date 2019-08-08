package com.example.springit;

import com.example.springit.config.SpringitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(SpringitProperties.class)
public class SpringitApplication {

	@Autowired
	private SpringitProperties springitProperties;

	@Autowired
	private ApplicationContext applicationContext;


	private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner runner(){
		return args -> {
			System.out.println("Welcome message is " + springitProperties.getWelcomeMessage());
			System.out.println("Printing out all the bean names in the application context");
			String[] beans = applicationContext.getBeanDefinitionNames();
			Arrays.sort(beans);
			for(String bean:beans){
				System.out.println(bean);
			}

		};
	}

}
