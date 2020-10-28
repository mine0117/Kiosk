
package com.web.blog.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.model.user.User;

public interface UserDao extends JpaRepository<User, Integer> {
    User getUserByEmail(String email);
    Optional<User> findUserByUid(int uid);
//    void saveUser(User signupReq);
    // Optional<User> findUserByEmailAndPassword(String email, String password);

}
