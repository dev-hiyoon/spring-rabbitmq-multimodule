package com.hiyoon.moduleserver;

import com.hiyoon.modulecommon.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @GetMapping
    public String getStudentName() {
        return StringUtils.getName();
    }
}
