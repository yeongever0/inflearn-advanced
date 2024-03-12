package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogig1;
import hello.advanced.trace.template.code.SubClassLogig2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime= startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime= startTime;
        log.info("resultTime = {}", resultTime);
    }

    /**
     * Template Method Pattern 적용
     */
    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogig1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogig2();
        template2.execute();
    }

    /**
     * 익명 내부 클래스 사용
     */
    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("클래스 이름1 = {}", template1.getClass());
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("클래스 이름2 = {}", template2.getClass());
        template2.execute();
    }
}
