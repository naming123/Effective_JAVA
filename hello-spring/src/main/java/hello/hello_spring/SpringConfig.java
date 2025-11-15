package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.SpringDataJpaMemberRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final SpringDataJpaMemberRepository memberRepository;

    public SpringConfig(SpringDataJpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

}
