package springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@RequestMapping("/")
	String index() {
		logger.info("Hello World!");  
		return "Hello World!";
	}

	@RequestMapping("/hello/{name}")
	String hello(@PathVariable String name) {
		long start = System.currentTimeMillis();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String re = "Hello " + name + "!!!";
		
		long used = System.currentTimeMillis() - start;
		logger.info(re + ", Used time: " + used + "ms.");  
		
		return re;
	}

}
