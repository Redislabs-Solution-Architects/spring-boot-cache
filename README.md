#spring-boot-cache

## Sample application using Redis as the data store for Spring Boot cache.

Some clients use the caching functionalities implemented in the Spring Boot framework.

Specifically, one of our clients uses it to cache the results of method invocations from services that query a mainframe.

The goal is to reduce the load on the underlying system by decreasing the number of MIPS, thus saving costs.

This application is a small example that simulates this use case, using Redis running locally to store the cached results.

Jedis and Lettuce can both be used as clients. In this case, we’ve opted for the latter to manage the underlying connections.

When the application is run, you can see that the first execution of the service takes several seconds, whereas the second response time is just a few milliseconds.

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.2)

2024-07-24T14:38:15.439+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Starting SpringBootCache using Java 22.0.1 with PID 23653 (/Users/manuel.guisado/redis/eclipse-workspace/spring-boot-cache/target/classes started by manuel.guisado in /Users/manuel.guisado/redis/eclipse-workspace/spring-boot-cache)
2024-07-24T14:38:15.440+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : No active profile set, falling back to 1 default profile: "default"
2024-07-24T14:38:15.580+02:00  INFO 23653 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode
2024-07-24T14:38:15.581+02:00  INFO 23653 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2024-07-24T14:38:15.589+02:00  INFO 23653 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 4 ms. Found 0 Redis repository interfaces.
2024-07-24T14:38:15.871+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Started SpringBootCache in 0.569 seconds (process running for 0.73)
2024-07-24T14:38:19.010+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 0,0 -> 3138 ms
2024-07-24T14:38:22.019+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 0,1 -> 3009 ms
2024-07-24T14:38:25.028+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 0,2 -> 3009 ms
2024-07-24T14:38:28.034+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 0,3 -> 3006 ms
2024-07-24T14:38:31.043+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 0,4 -> 3009 ms
2024-07-24T14:38:31.054+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 1,0 -> 10 ms
2024-07-24T14:38:31.056+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 1,1 -> 2 ms
2024-07-24T14:38:31.057+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 1,2 -> 1 ms
2024-07-24T14:38:31.058+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 1,3 -> 1 ms
2024-07-24T14:38:31.059+02:00  INFO 23653 --- [           main] com.redis.SpringBootCache                : Iterarion: 1,4 -> 1 ms
```

By default, the results are stored in Redis using as prefix in the key that is the value of the value parameter in the Cacheable annotation 

> @Cacheable(value = "items")

```
127.0.0.1:6379> scan 0
1) "0"
2) 1) "items::0"
   2) "items::3"
   3) "items::2"
   4) "items::1"
   5) "items::4"
127.0.0.1:6379> get "items::0"
"\xac\xed\x00\x05t\x00\x06Item 0"
```



The project has been developed using the Eclipse IDE.