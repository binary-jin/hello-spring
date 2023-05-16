package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }//각 테스트 실행 전에 memorymemberrepository를 만들고 memberservice를 넣어줌
    //이게 di...?



    @AfterEach
    public void afterEach() {
        memberRepository.clearStore(); //테스트가 끝날 때마다 한 번씩 저장소를 다 지움, 테스트 코드에 순서가 상관 없어짐
        //테스트는 서로 의존 관계가 없어야함 ->  서로 영향 주지 않기 위해 저장소를 지우는 거
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when 뭘 검증하는지
        Long saveId = memberService.join(member); //join하면 id가 나옴,,,

        //then 기대하는 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {

        //given
        Member member = new Member();
        member.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        /*memberService.join(member);
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e) {
           assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원이 있습니다");
        }*/
        //밑에 코드와 같음

        memberService.join(member);
        assertThrows(IllegalStateException.class,()-> memberService.join(member2));
        //->화살표 뒤에 로직을 실행하면 앞에 exception이 터져야함

        //then
    }

    @Test
    void findMembers() {
        //given

        //when

        //then

    }

    @Test
    void findOne() {
    }
}