package com.yadirichukwu.schoolmanagementsystem.repository;

import com.yadirichukwu.schoolmanagementsystem.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
