package com.yadirichukwu.schoolmanagementsystem.service;

import com.yadirichukwu.schoolmanagementsystem.dto.SchoolDto;
import com.yadirichukwu.schoolmanagementsystem.mappers.SchoolMapper;
import com.yadirichukwu.schoolmanagementsystem.repository.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper mapper;

    public SchoolService(SchoolRepository repository, SchoolMapper mapper) {
        this.schoolRepository = repository;
        this.mapper = mapper;
    }
    public SchoolDto createSchool(@RequestBody SchoolDto dto) {
        var school = mapper.toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }
    public List<SchoolDto> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(mapper::toSchoolDto)
                .collect(Collectors.toList());
    }


}
