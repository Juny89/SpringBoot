package kr.co.joneconsulting.myrestfulservice.bean;

import jakarta.validation.constraints.Past; //스프링부트 3 버전대 사용한다면 jakarta 에서 임포트 @Size @Past 어노테이션 사용하기 위함
import jakarta.validation.constraints.Size; //스프링부트 3 버전대 사용한다면 jakarta 에서 임포트 @Size @Past 어노테이션 사용하기 위함
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor//생성자를 자동으로 만들어주는 어노테이션
public class User {
    private Integer id;

    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
    protected String name;

    @Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다.")
    private Date joinDate;
}
