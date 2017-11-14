package ar.com.gvallerino.xMenML;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"ar.com.gvallerino.xMenML.service.DnaAnalyzerService"})
public class XmenMlApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XmenMlApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(XmenMlApplication.class, args);
	}
}
