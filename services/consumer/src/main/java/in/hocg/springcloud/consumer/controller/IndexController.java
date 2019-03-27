package in.hocg.springcloud.consumer.controller;

import in.hocg.springcloud.consumer.client.ProducerClient;
import in.hocg.springcloud.consumer.service.IndexService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2019/3/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RestController
@AllArgsConstructor
public class IndexController {
    
    private final IndexService indexService;
    private final ProducerClient producerManager;
    
    @Trace
    @GetMapping("/worked")
    public ResponseEntity ok() {
        ActiveSpan.tag("test,call", "这是测试信息");
        log.debug("Debug Info {}", indexService.index());
        return ResponseEntity.ok("Ok" + TraceContext.traceId());
    }
    
    @Trace
    @GetMapping("/link")
    public ResponseEntity link() {
        return ResponseEntity.ok(producerManager.worked());
    }
    
    @Trace
    @GetMapping("/fail")
    public ResponseEntity testFail() {
        return ResponseEntity.ok(producerManager.fail());
    }
    
    @Trace
    @GetMapping("/params")
    public ResponseEntity params() {
        return ResponseEntity.ok(producerManager.params(LocalDateTime.now()));
    }
    
    @Trace
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(producerManager.upload(file));
    }
    
}
