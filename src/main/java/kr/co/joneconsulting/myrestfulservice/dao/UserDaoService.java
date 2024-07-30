package kr.co.joneconsulting.myrestfulservice.dao;

import kr.co.joneconsulting.myrestfulservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static{
        users.add(new User(1, "Kenneth", new Date()));
        users.add(new User(2, "Alice", new Date()));
        users.add(new User(3, "Elean", new Date()));
    }

    public List<User> findAll(){//전체 데이터 가져오는 함수
        return users;
    }

    public User save(User user){
        if (user.getId() == null){
            user.setId(++usersCount);
        }

        if (user.getJoinDate() == null){
            user.setJoinDate(new Date());
        }

        users.add(user);

        return user;
    }

    public User findOne(int id){
        for (User user : users){
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User deletById(int id) {
        Iterator<User> iterator = users.iterator(); //iterator로 데이터 값 변환

        while (iterator.hasNext()){ //전체 iterator값을 반복문을 통해서 하나씩 접근
            User user = iterator.next();

            if (user.getId() == id) { //막 검색한 id값과 매개변수 id값을 비교해서 같으면 실행
                iterator.remove(); //찾은 값을 삭제하는 메소드
                return user;
            }
        }

        return null; //찾은 값이 없다면 null값 반환
    }
}

