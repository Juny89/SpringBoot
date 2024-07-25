package kr.co.joneconsulting.myrestfulservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //생성자를 자동으로 생성해주는 어노테이션
public class HelloWorldBean {
    private String message;

//    AllArgsConstructor 어노테이션을 사용 하였기에 아래의 생성자는 필요 없다.
//    public HelloWorldBean(String message) {
//        this.message = message;
//    }
}
