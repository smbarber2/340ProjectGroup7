package com._Project.Tbay.Admin;
import com._Project.Tbay.User.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<User, Long>{

}
