
package com.moger.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication		//will combine springbootconfiguration(@configuration-java configuration),enableautoconfiguration,componentscan
public class FirstAppApplication {
	public static void main(String[] args) {
				SpringApplication.run(FirstAppApplication.class, args);//returns applicationcontext(ioc container)
	 
	} 
}
 