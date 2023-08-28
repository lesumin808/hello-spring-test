package hello.hellospringtest.service;

import hello.hellospringtest.domain.Member;
import hello.hellospringtest.repository.MemberRepository;
import hello.hellospringtest.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*회원가입*/
    public long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        //코드 축약 가능..
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }
    /*중복회원 검색*/
    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID){
        return memberRepository.findById(memberID);
    }
}
