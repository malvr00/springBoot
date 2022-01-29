package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // Service 단은 좀 더 비즈니스적 네이밍을 택함.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     *  회원가입
     */
    public Long join(Member member){
        validateDuplicateMember(member);    // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // ifPresent Optional 메소드
        // 값이 있으면 실행
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                try {
                    throw new IllegalAccessException("이미 존재하는 회원입니다. ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
    }

    /**
     *  전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 개별 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
