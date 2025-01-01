package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = context.getBean(StatefulService.class);
        StatefulService statefulService2 = context.getBean(StatefulService.class);

        // A 사용자가 10000원 주문
        int price1 = statefulService1.order("userA", 10000);
        // B 사용자가 20000원 주문
        int price2 = statefulService1.order("userB", 20000);

        // 스레드A가 주문 금액 조회
        System.out.println(price1);
//        int price = statefulService1.getPrice();
//        System.out.println("price: "+ price);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}