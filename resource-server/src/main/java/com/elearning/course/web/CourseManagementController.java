package com.elearning.course.web;

import com.elearning.course.application.*;
import com.elearning.course.application.dto.CourseDTO;
import com.elearning.course.application.dto.CourseSectionDTO;
import com.elearning.course.application.dto.CourseUpdateDTO;
import com.elearning.course.application.dto.LessonDTO;
import com.elearning.course.domain.Course;
import com.elearning.course.web.dto.ApplyDiscountDTO;
import com.elearning.course.web.dto.AssignTeacherDTO;
import com.elearning.course.web.dto.UpdatePriceDTO;
import com.elearning.course.web.dto.UpdateSectionDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/courses")
public class CourseManagementController {

    private final CourseService courseService;
    private final CourseQueryService courseQueryService;

    public CourseManagementController(CourseService courseService, CourseQueryService courseQueryService) {
        this.courseService = courseService;
        this.courseQueryService = courseQueryService;
    }

    @GetMapping
    public ResponseEntity<Page<Course>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseQueryService.findAllCourses(pageable));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseQueryService.findCourseById(courseId));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid CourseDTO courseDTO
    ) {
        String teacherId = jwt.getSubject();
        Course createdCourse = courseService.createCourse(teacherId, courseDTO);
        URI location = URI.create("/courses/" + createdCourse.getId());
        return ResponseEntity.created(location).body(createdCourse);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId,
                                               @RequestBody @Valid CourseUpdateDTO courseUpdateDTO) {
        Course updatedCourse = courseService.updateCourse(courseId, courseUpdateDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{courseId}/restore")
    public ResponseEntity<Course> restoreCourse(@PathVariable Long courseId) {
        courseService.restoreCourse(courseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{courseId}/apply-discount")
    public ResponseEntity<Course> applyDiscount(@PathVariable Long courseId,
                                                @Valid @RequestBody ApplyDiscountDTO applyDiscountDTO) {
        Course updatedCourse = courseService.applyDiscount(courseId, applyDiscountDTO.code());
        return ResponseEntity.ok(updatedCourse);
    }

    @PutMapping("/{courseId}/publish")
    public ResponseEntity<Course> publishCourse(@AuthenticationPrincipal Jwt jwt, @PathVariable Long courseId) {
        Course updatedCourse = courseService.publishCourse(courseId, jwt.getSubject());
        return ResponseEntity.ok(updatedCourse);
    }

    @PutMapping("/{courseId}/update-price")
    public ResponseEntity<Course> changePrice(@PathVariable Long courseId,
                                              @Valid @RequestBody UpdatePriceDTO priceDTO) {
        Course updatedCourse = courseService.updatePrice(courseId, priceDTO.price());
        return ResponseEntity.ok(updatedCourse);
    }

    @PutMapping("/{courseId}/assign-teacher")
    public ResponseEntity<Course> assignTeacher(@PathVariable Long courseId,
                                                @Valid @RequestBody AssignTeacherDTO assignTeacherDTO) {
        return ResponseEntity.ok(courseService.assignTeacher(courseId, assignTeacherDTO.teacherId()));
    }

    @PostMapping("/{courseId}/sections")
    public ResponseEntity<Course> addSection(@PathVariable Long courseId,
                                             @RequestBody @Valid CourseSectionDTO courseSectionDTO) {
        Course updatedCourse = courseService.addSection(courseId, courseSectionDTO);
        return ResponseEntity.created(URI.create("/courses/" + courseId)).body(updatedCourse);
    }

    @PutMapping("/{courseId}/sections/{sectionId}")
    public ResponseEntity<Course> updateSectionInfo(@PathVariable Long courseId,
                                                    @PathVariable Long sectionId,
                                                    @RequestBody @Valid UpdateSectionDTO updateSectionDTO) {
        Course updatedCourse = courseService.updateSectionInfo(courseId, sectionId, updateSectionDTO.title());
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}/sections/{sectionId}")
    public ResponseEntity<Course> deleteSection(@PathVariable Long courseId, @PathVariable Long sectionId) {
        Course updatedCourse = courseService.removeSection(courseId, sectionId);
        return ResponseEntity.ok(updatedCourse);
    }

    @PostMapping("/{courseId}/sections/{sectionId}/lessons")
    public ResponseEntity<Course> addLesson(@PathVariable Long courseId,
                                            @PathVariable Long sectionId,
                                            @RequestBody @Valid LessonDTO lessonDTO) {
        Course updatedCourse = courseService.addLesson(courseId, sectionId, lessonDTO.toLesson());
        return ResponseEntity.ok(updatedCourse);
    }

    @PutMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}")
    public ResponseEntity<Course> updateLesson(@PathVariable Long courseId,
                                               @PathVariable Long sectionId,
                                               @PathVariable Long lessonId,
                                               @RequestBody @Valid LessonDTO lessonDTO) {
        Course updatedCourse = courseService.updateLesson(courseId, sectionId, lessonId, lessonDTO.toLesson());
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}")
    public ResponseEntity<Course> deleteLesson(@PathVariable Long courseId,
                                               @PathVariable Long sectionId,
                                               @PathVariable Long lessonId) {
        Course updatedCourse = courseService.removeLesson(courseId, sectionId, lessonId);
        return ResponseEntity.ok(updatedCourse);
    }

}