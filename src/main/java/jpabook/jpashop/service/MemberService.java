package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
// @AllArgsConstructor // 필드를 갖고 생성자를 만들어주는 것
@RequiredArgsConstructor // final이 있는 필드만 갖고 생성자를 만들어줌
public class MemberService {

    // Field Injection
    // @Autowired
    private final MemberRepository memberRepository;

    // Setter Injection: Spring이 바로 Inject 해주는 것이 아님
    // Test code 작성 시, 직접 주입해줄 수 있음
    // runtime 시에 이후 변경될 일이 있나?
    /* @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } */

    // 생성자 Injection 권장
    /* @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } */

    // Autowired 하지 않아도, 생성자가 하나만 있는 경우 자동으로 Injection 해줌
    /* public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } */

    /**
     * 회원 가입
     * */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증 비즈니스 로직
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 하나 조회
    public Member findOne(Long memberId) {
        return memberRepository.find(memberId);
    }

}
