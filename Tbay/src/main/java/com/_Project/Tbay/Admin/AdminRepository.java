package com._Project.Tbay.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository extends JpaRepository<Admin, Long> {
}
