package com.auth.security.Repository;

import com.auth.security.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface UserDAO extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);




}
