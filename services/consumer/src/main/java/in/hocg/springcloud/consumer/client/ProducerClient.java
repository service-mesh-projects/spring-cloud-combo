package in.hocg.springcloud.consumer.client;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2019/3/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = All.PRODUCER_APP,
        fallback = ProducerClientHystrix.class,
        configuration = ProducerClient.MultipartSupportConfig.class)
public interface ProducerClient {
    
    /**
     * 检查生产者是否存活
     * @return
     */
    @GetMapping("/worked")
    String worked();
    
    /**
     * 失败
     * @return
     */
    @GetMapping("/fail")
    String fail();
    
    /**
     * 测试时间类型
     * @param now
     * @return
     */
    @GetMapping("/params")
    String params(@RequestParam("now") LocalDateTime now);
    
    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    String upload(@RequestPart("file") MultipartFile file);
    
    class MultipartSupportConfig{
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
