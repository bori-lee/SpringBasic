package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class SingletonServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //스레드 A : A사용자가 만원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //스레드 B : B사용자가 2만원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
       // int price = statefulService1.getPrice();
        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("price = " + userAPrice);

       // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService stateFullService() {
            return new StatefulService();
        }
    }

}