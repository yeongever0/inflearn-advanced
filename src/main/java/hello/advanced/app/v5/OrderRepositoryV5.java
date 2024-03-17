package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logTrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    public void save(String itemId) {
        traceTemplate.execute("OrderRepository.save()", (TraceCallback<Void>) () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(5000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
