package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    // @PersistenceContext // Spring Data JPA가 지원하므로 @Autowired로 변경 가능
    private final EntityManager em; // Spring이 EntityManager를 만들어서 주입해줌

    /* public MemberRepository(EntityManager em) {
        this.em = em;
    } */

    public void save(Member member) {
        em.persist(member);
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    // JPQL은 기능적으로 거의 동일함
    // SQL은 테이블을 대상으로 하는데, JPQL은 Entity를 대상으로 쿼리함
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
