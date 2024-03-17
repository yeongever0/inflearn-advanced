package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logTrace.LogTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;

    @Autowired
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/request")
    public String request(@RequestParam(value="itemId")String itemId)  {
        return traceTemplate.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
