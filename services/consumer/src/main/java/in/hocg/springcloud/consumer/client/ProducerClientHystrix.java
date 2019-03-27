package in.hocg.springcloud.consumer.client;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2019/3/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class ProducerClientHystrix implements ProducerClient {
    
    @Override
    public String worked() {
        return "fail worked";
    }
    
    @Override
    public String fail() {
        return "must fail";
    }
    
    @Override
    public String params(LocalDateTime now) {
        return "fail";
    }
    
    @Override
    public String upload(MultipartFile file) {
        return "fail";
    }
}
