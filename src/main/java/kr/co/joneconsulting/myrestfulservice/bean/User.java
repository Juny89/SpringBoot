package kr.co.joneconsulting.myrestfulservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor//생성자를 자동으로 만들어주는 어노테이션
public class User {
    private Integer id;
    private String name;
    private Date joinDate;
}
