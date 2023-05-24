package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //Long 타입의 id와 회원 member 매핑
    private static long sequence = 0L; //시퀀스는 키 값을 생성해주는 애

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //아이디를 시퀀스 값 하나 증가해 세팅
        store.put(member.getId(), member); //세팅한 아이디 저장
        return member; //저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환 될 위험이 있으면 optional.ofNullable로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name)) //가져온 name이 파라미터로 넘어온 name과 같은지 확인
                .findAny(); //루프 계속 돌면서 찾다가 같은 게 있으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() { //테스트 코드 할 때 위에서 만들어진 데이터를 다른 테스트 코드에 영향 주지 않기 위해 데이터 클리어 하기 위함
        store.clear();
    }
}
