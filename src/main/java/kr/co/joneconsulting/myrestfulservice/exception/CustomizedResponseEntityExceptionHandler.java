package kr.co.joneconsulting.myrestfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice//모든 컨트롤러가 실행될때 이 클래스가 실행되도록 하는 어노테이션
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)//예외 발생시킬 때 어떤 예외만 처리해줄 것인지(ExceptionHandler는 사실 모든 예외 클래스의 부모격이기 때문에 모든 걸 처리한다고 볼 수 있음)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){//예외객체:ex , 서비스 상태에서 발생할 수 있는 Request정보 : request

        //클라이언트에게 전달할 예외처리 객체 exceptionResponse를 생성
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
                //Date(), ex.getMessage()를 통해 기본적인 시점과 메세지 정도만 클라이언트에게 전달
                //request.getDescription(false) : 클라이언트에게 상세정보는 false 보여주지 않음


        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //UserNotFOundException이 발생 했을 때에는 아래의 메소드가 실행, 그외의 Exception의 경우 위의 메소드가 실행
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
