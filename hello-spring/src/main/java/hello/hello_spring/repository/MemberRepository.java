package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

    // 로그인/이메일 중복 체크용
    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByEmail(String email);

//    void clearStore();
}
