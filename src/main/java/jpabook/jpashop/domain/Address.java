package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
        // public 보다 protected 로 설정하는 것이 그나마 더 안전함
    }

    // JPA가 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때
    // 리플렉션 같은 기술을 사용할 수 있도록 지원해야 하기 때문
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
