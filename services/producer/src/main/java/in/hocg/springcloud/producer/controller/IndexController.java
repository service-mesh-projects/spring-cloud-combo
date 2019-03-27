package in.hocg.springcloud.producer.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;

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
    
    @Trace
    @GetMapping("/worked")
    public ResponseEntity ok() {
        
        return ResponseEntity.ok("This is Producer" + TraceContext.traceId());
    }
    
    @Trace
    @GetMapping("/params")
    public ResponseEntity params(@RequestParam("now") LocalDateTime now) {
        return ResponseEntity.ok("NOW() " + now);
    }
    
    @Trace
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok("file " + Objects.nonNull(file));
    }
}
