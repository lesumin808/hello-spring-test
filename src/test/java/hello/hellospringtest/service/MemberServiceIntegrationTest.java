package hello.hellospringtest.service;

import hello.hellospringtest.domain.Member;
import hello.hellospringtest.repository.MemberRepository;
import hello.hellospringtest.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //테스트는 한글로 변경이 가능 -> 직관적이게
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {//join
        
        //given when then 패턴으로 테스트 케이스 작성
        //given : 무언가가 주어졌을때
        Member member = new Member();
        member.setName("spring");


        //when : 이러한 상황에서
        Long saveId = memberService.join(member);

        //then : 이러한 결과가 나와야된다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예약(){
        //given
        //동일한값?
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        //조인할때
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
        //에러가 터져야된다
    }

}