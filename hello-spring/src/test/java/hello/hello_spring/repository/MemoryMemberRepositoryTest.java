package hello.hello_spring.repository;

import org.junit.jupiter.api.Test;
import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.Assertions;

class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == null));
        Assertions.assertEquals(result, member);
    }

}
