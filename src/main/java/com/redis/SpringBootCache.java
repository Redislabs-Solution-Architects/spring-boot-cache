package com.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@Configuration
@EnableCaching
public class SpringBootCache implements CommandLineRunner {

    private static final Logger log = 
    		LoggerFactory.getLogger(SpringBootCache.class);

	@Bean
	LettuceConnectionFactory lettuceConnectionFactory() {
		LettuceConnectionFactory connectionFactory =
			new LettuceConnectionFactory("localhost", 6379);
		return connectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(lettuceConnectionFactory());
	    return template;
	}	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCache.class, args);
	}

	@Autowired
	ExampleService exampleService;
	
	@Override
	  public void run(String... strings) throws Exception {
		long start, elapsed = 0L;
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 5; y ++) {
				start = System.currentTimeMillis();
				exampleService.getItemById(Integer.toString(y));
				elapsed = System.currentTimeMillis() - start;
				log.info("Iterarion: " + x + "," + y + " -> " + elapsed + " ms");
			}
		}
	}
	
}
