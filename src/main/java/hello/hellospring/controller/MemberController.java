package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

//    @Autowired    // 필드 주입 (별로 좋은 방법은 아님. 바꿔치기 할 수 있는 방법이 없음.)
    private final MemberService memberService;

//    @Autowired  // 단점 : 퍼블릭으로 노출된다는 단점.
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @Autowired
    public MemberController(MemberService memberService) {
        // 단축키 = 커맨드 N
        this.memberService = memberService;
    }
}
