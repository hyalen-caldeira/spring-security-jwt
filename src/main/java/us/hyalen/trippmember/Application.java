package us.hyalen.trippmember;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
        		.main(Application.class)
        		.run(args);
	}
}