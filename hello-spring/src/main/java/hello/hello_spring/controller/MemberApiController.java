package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "http://localhost:5173") // Vite 개발 서버 허용
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 목록 조회
    @GetMapping
    public List<Member> list() {
        return memberService.findMembers();
    }

    // 회원 단건 조회 (옵션)
    @GetMapping("/{id}")
    public Optional<Member> getOne(@PathVariable Long id) {
        return memberService.findOne(id);
    }

    // 회원 가입
    @PostMapping
    public Member create(@RequestBody CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        memberService.join(member);
        return member;
    }

    // 요청 DTO
    public static class CreateMemberRequest {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
