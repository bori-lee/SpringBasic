package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // 자동의존관계주입을 위해 생성자에게 붙여줌..ac.getBean(MemberRepository.class)랑 같다고보면됨.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);

    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
