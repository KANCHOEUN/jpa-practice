package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest // Spring과 Integeration을 해서 Test
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    // @Rollback(false)
    public void 회원_가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("KKUL");

        //when
        Long saveId = memberService.join(member);

        //then
        // 각 JPA에서 각 transaction 내에서 id가 같으면 같은 영속성 컨텍스트에서 관리됨을 의미
        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("KKUL");

        Member member2 = new Member();
        member2.setName("KKUL");

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야 한다!!

        /* try {
            memberService.join(member2); // 예외가 발생해야 한다!!
        } catch (IllegalStateException e) {
            return;
        } */

        //then
        fail("예외가 발생해야 한다.");
    }

}