package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //jpa가 관리하는 엔티티구나~
public class Member {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //자동으로 넣어주는 거
    private Long id; //데이타 구분 위한 아이디
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
