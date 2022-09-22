package com.tsystems.mms.demoapp.repository;

import com.tsystems.mms.demoapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
