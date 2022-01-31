package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/membersList";
    }
}
