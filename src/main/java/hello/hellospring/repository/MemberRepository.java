package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //멤버 저장
    Optional<Member> findById(Long id); //아이디로 회원 찾기
    Optional<Member> findByName(String name); //이름으로 찾기
    List<Member> findAll(); //전체 리스트 출력
}
