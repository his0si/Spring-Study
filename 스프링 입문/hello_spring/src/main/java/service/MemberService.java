package service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member){ //회원 가입
        // 같은 이름이 있는 중복 회원x
        /**Optional<Member> result = memberRepository.findByName(member.getName());
        Optional 안에 멤버 객체가 있음
        한번 감싸서 반환해주고 메소드 사용
        result.get(); 이렇게 꺼낼 수도 있음
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });**/

        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member); //통과하면 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); // 클린 코드
    }

    public List <Member> findMembers(){ //전체 회원 조회
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
