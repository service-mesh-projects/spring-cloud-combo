package in.hocg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by hocgin on 2019/3/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProducerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}
