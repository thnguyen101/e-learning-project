package com.el.enrollment.web;

import com.el.enrollment.application.EnrolmentStatisticService;
import com.el.enrollment.application.dto.CourseInfoWithEnrolmentStatisticDTO;
import com.el.enrollment.application.dto.CourseInfoWithEnrolmentsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "enrollments/statistics", produces = "application/json")
public class EnrolmentStatisticController {

    private final EnrolmentStatisticService enrolmentStatisticService;

    public EnrolmentStatisticController(EnrolmentStatisticService enrolmentStatisticService) {
        this.enrolmentStatisticService = enrolmentStatisticService;
    }

    @GetMapping
    public ResponseEntity<Page<CourseInfoWithEnrolmentStatisticDTO>> minInfoWithEnrolmentStatistic(Pageable pageable) {
        log.info("Request to get courses min info and enrolment statistics");
        List<CourseInfoWithEnrolmentStatisticDTO> result = enrolmentStatisticService.getCourseMinInfoWithEnrolmentStatistics(pageable);
        return ResponseEntity.ok(new PageImpl<>(result, pageable, result.size()));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseInfoWithEnrolmentsDTO> courseWithEnrolmentStatistic(@PathVariable Long courseId) {
        log.info("Request to get course with enrolment statistics");
        CourseInfoWithEnrolmentsDTO result = enrolmentStatisticService.getCourseWithEnrolmentStatistics(courseId);
        return ResponseEntity.ok(result);
    }


}