package com.example.BedManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan
@EnableTransactionManagement
@EnableSwagger2
public class BedManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(BedManagementApplication.class, args);
	}

	@GetMapping("/springboot")
	@ResponseBody
	public String getInput(@RequestParam(required = false) String input) {
		return "input parameter value: " + input;
	}
}
