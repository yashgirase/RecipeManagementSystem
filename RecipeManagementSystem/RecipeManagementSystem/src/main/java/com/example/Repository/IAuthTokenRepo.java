package com.example.Repository;

import com.example.Entity.AuthenticationToken;
import com.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // not needed as those classes which implemented jpa repository already has @Repository on them so here it is optional
public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken , Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
