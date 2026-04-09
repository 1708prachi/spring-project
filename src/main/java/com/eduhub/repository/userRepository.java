package com.eduhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eduhub.entity.user;

public interface userRepository extends JpaRepository<user, Long>{

}
