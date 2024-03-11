package hello.advanced.trace.threadLocal;

import hello.advanced.trace.threadLocal.code.FieldService;
import hello.advanced.trace.threadLocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            service.login("userA");
        };
        Runnable userB = () -> {
            service.login("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        //sleep(2000); //동시성 문제 X
        sleep(100); //동시성 문제 O
        threadB.start();

        sleep(2000);
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
