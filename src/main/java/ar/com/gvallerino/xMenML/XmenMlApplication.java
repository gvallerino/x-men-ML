package ar.com.gvallerino.xMenML;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * X MEN Application 
 */
@SpringBootApplication
@EnableScheduling
public class XmenMlApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(XmenMlApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XmenMlApplication.class);
	}
	
}
