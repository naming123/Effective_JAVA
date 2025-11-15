package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){
        // 중복 회원 체크 (이름/아이디/이메일)
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 이름 중복
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이름입니다.");
                });

        // 아이디 중복
        if (member.getLoginId() != null) {
            memberRepository.findByLoginId(member.getLoginId())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 사용 중인 아이디입니다.");
                    });
        }

        // 이메일 중복
        if (member.getEmail() != null) {
            memberRepository.findByEmail(member.getEmail())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 사용 중인 이메일입니다.");
                    });
        }
    }

    // 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 로그인
    public Optional<Member> login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password));
    }

    // 이메일 중복 여부
    public boolean isEmailDuplicate(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    // 아이디 중복 여부
    public boolean isLoginIdDuplicate(String loginId) {
        return memberRepository.findByLoginId(loginId).isPresent();
    }
}
