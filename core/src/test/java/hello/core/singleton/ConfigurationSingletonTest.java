package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = context.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = context.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository2 = context.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository = memberService.getMemberRepository();
        MemberRepository memberRepository1 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository);
        System.out.println("orderService -> memberRepository = " + memberRepository1);
        System.out.println("memberRepository = " + memberRepository2);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = context.getBean(AppConfig.class);

        System.out.println("bean =" + bean.getClass());
    }
}
