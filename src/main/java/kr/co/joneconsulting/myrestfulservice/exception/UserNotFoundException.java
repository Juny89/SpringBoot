package kr.co.joneconsulting.myrestfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//이 예외 클래스를 사용할때 500이 아니라 404번 오류를 발생하도록 하는 어노테이션(보안을 위해 404은 데이터(리소스)가 없다는 뜻)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
