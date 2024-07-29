package com.yadirichukwu.schoolmanagementsystem.mappers;

import com.yadirichukwu.schoolmanagementsystem.domain.School;
import com.yadirichukwu.schoolmanagementsystem.dto.SchoolDto;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    public School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    public SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }
}
