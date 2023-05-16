package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

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
