// src/main/java/hello/hello_spring/controller/AuthApiController.java
package hello.hello_spring.controller;

import hello.hello_spring.controller.dto.*;
import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthApiController {

    private final MemberService memberService;

    public AuthApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    // === 로그인 ===
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpSession session
    ) {
        Optional<Member> loginMember = memberService.login(request.getLoginId(), request.getPassword());

        if (loginMember.isEmpty()) {
            return ResponseEntity
                    .status(401)
                    .body(new LoginResponse(false, "아이디 또는 비밀번호가 올바르지 않습니다.", null, null));
        }

        Member member = loginMember.get();

        // 세션에 로그인 정보 저장
        session.setAttribute("loginMemberId", member.getId());

        return ResponseEntity.ok(
                new LoginResponse(true, "로그인 성공", member.getId(), member.getName())
        );
    }

    // === 회원가입 1단계: 기본 정보 저장 (세션에 임시 저장) ===
    @PostMapping("/signup/step1")
    public ResponseEntity<?> signupStep1(
            @RequestBody SignupStep1Request request,
            HttpSession session
    ) {

        System.out.println("====== Signup Step1 Request ======");
        System.out.println("name = " + request.getName());
        System.out.println("email = " + request.getEmail());
        System.out.println("loginId = " + request.getLoginId());
        System.out.println("phone = " + request.getPhone());
        System.out.println("password = " + request.getPassword());
        System.out.println("==================================");
        // 이메일 / 아이디 중복 체크
        if (memberService.isEmailDuplicate(request.getEmail())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 이메일입니다.");
        }
        if (memberService.isLoginIdDuplicate(request.getLoginId())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 아이디입니다.");
        }

        // 세션에 1단계 정보 저장
        session.setAttribute("signupStep1", request);




        return ResponseEntity.ok().build();
    }

    // === 회원가입 2단계: 개인정보 입력 + 실제 저장 ===
    @PostMapping("/signup/step2")
    public ResponseEntity<?> signupStep2(
            @RequestBody SignupStep2Request request,
            HttpSession session
    ) {
        // 세션에서 1단계 정보 꺼내오기
        SignupStep1Request step1 = (SignupStep1Request) session.getAttribute("signupStep1");
        if (step1 == null) {
            return ResponseEntity.badRequest().body("1단계 정보가 없습니다. 다시 진행해주세요.");
        }

        // Member 엔티티 생성 및 채우기
        Member member = new Member();
        member.setName(step1.getName());
        member.setEmail(step1.getEmail());
        member.setLoginId(step1.getLoginId());
        member.setPhone(step1.getPhone());
        member.setPassword(step1.getPassword());

        member.setBirth(request.getBirth());
        member.setStudentId(request.getStudentId());
        member.setMajor(request.getMajor());
        member.setGraduationTerm(request.getGraduationTerm());

        // 가입 처리
        Long savedId = memberService.join(member);

        // 세션에서 1단계 정보 제거
        session.removeAttribute("signupStep1");

        return ResponseEntity.ok(savedId);
    }
}
