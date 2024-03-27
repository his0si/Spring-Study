package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //없으면 null 대신 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();

    void clearStore();
}
