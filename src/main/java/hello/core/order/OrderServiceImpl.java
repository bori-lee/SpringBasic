package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final이 붙은걸 가지고, 생성자를 롬북이 만들어줌...
public class OrderServiceImpl implements OrderService {

    //생성자 주입(final 키워드넣어서 안바뀜... 생성자에서만 값을 세팅할수있따)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //setter 주입시 사용
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

    //필드 주입
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private DiscountPolicy discountPolicy

//    //생성자 주입(롬북때문에 주석)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //setter(수정자) 주입, 멤버리파지토리에 등록이 안된거라도 사용할수있다. 그래서 선택, 변경가능
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원의아이디 찾고
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인정책에다가 회원을 넘김..

        //단일책임의 원칙.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
