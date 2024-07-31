package kr.co.joneconsulting.myrestfulservice.controller;

import jakarta.validation.Valid;
import kr.co.joneconsulting.myrestfulservice.bean.User;
import kr.co.joneconsulting.myrestfulservice.dao.UserDaoService;
import kr.co.joneconsulting.myrestfulservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @RequestMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

    // CREATED
    // input - details of user
    // output - CREATED & Return the created URI
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) { //@Valid 어노테이션 등록을 통해 들어오는 User상태의 user객체가 밸리데이션 체크를 한다는것을 명시
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}") //user/사용자 아이디
    public ResponseEntity deleteUser(@PathVariable int id) { //PathVariable로 가변데이터로 전달된 값을 id라는 변수에 받음
        User deletedUser = service.deletById(id);

        //데이터 검색을 하지 못하였을때 예외처리
        if (deletedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        //삭제 상태코드를 noContent 204로 바꾸기 위해 추가
        return ResponseEntity.noContent().build();

    }

}
