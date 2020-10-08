package com.knila.machine;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KnilaMachineTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnilaMachineTestApplication.class, args);
	}

	@Bean(name="org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		List<String> mappingFiles =Arrays.asList("dozer-bean-mappings.xml");
		
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		return dozerBean;
		
	}
	
}
