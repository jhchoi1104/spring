package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링이 없는 순수 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때마다 객체 생성
        MemberService memberService = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService: "+ memberService);
        System.out.println("memberService1: "+ memberService1);

        Assertions.assertThat(memberService).isNotSameAs(memberService1);
    }

    @Test
    @DisplayName("싱글톤 패턴 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService1 = SingletonService.getInstance();

        Assertions.assertThat(singletonService).isSameAs(singletonService1);

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회: 호출할 때마다 객체 생성
        MemberService memberService1 = context.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때마다 객체 생성
        MemberService memberService2 = context.getBean("memberService", MemberService.class);

        // 참조값이 다른 것을 확인
        System.out.println("memberService1: "+ memberService1);
        System.out.println("memberService2: "+ memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
