package hello.hellospringtest.repository;

import hello.hellospringtest.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));

        Assertions.assertThat(member).isEqualTo(result);
        //저장되는 도메인 값으로 비교

    }
}
