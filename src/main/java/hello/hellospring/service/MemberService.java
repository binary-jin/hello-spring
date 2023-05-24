package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //이걸 붙이면 스프링이 서비스넹 하고 스프링 컨테이너에 등록해줌
//원래 @component를 붙이면 되는데 service 안에 component가 포함 되어있음 (repository, controller도 마찬가지)
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired //memberService가 뜰 때 서비스네 하면서 컨테이너에 등록하면서 생성자 호출->autowired가 있으면 memberrepository가 필요하구나 하고 컨테이너에 있는 repository를 주입을 해줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } //memberRepository를 직접 new로 생성하는 게 아니라 외부에서 가져오게 바꿈

    public Long join(Member member) {  //회원가입
        //같은 이름이 있는 중복 회원은 가입이 안 됨
        ValidateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId(); //저장하고 아이디만 반환해주겠다,,,
    }

    private void ValidateDuplicateMember(Member member) {
        /*Optional<Member> result = (옵셔널 생략한 거)*/
        memberRepository.findByName(member.getName()) //중복 이름이 안 되니까 이름으로 먼저 찾음
        .ifPresent(m -> { //null이 아니라 값이 있으면 밑에 문장 띄움 (ifPresent는 Optional로 감쌌기 때문에 사용 가능)
            throw new IllegalStateException("이미 존재하는 회원이 있습니다");
        });
    }

    public List<Member> findMembers() { //전체 회원 조회
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
