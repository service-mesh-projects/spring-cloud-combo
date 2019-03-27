package in.hocg.springcloud.filter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocgin on 2019/3/27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class SentinelGatewayFilter implements GlobalFilter, Ordered {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        String resource = exchange.getRequest().getPath().toString();
        this.initFlowRules(resource);
        Entry entry = null;
        
        try {
            ContextUtil.enter(resource);
            entry = SphU.entry(resource, EntryType.IN, 1, exchange.getRequest().getQueryParams().values());
            return chain.filter(exchange);
        } catch (BlockException e) {
            if (!BlockException.isBlockException(e)) {
                Tracer.trace(e);
            }
            e.printStackTrace();
        } finally {
            if (entry != null) {
                entry.exit();
            }
            ContextUtil.exit();
        }
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
    }
    
    // 配置规则
    private void initFlowRules(String resource) {
        List<FlowRule> rules = new ArrayList<>();
        // 限流
        FlowRule rule = new FlowRule();
        rule.setResource(resource);
        rule.setCount(5);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
    
    @Override
    public int getOrder() {
        return -1;
    }
}
