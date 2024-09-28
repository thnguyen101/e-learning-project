package com.elearning.course.application.dto;

import com.elearning.course.domain.Lesson;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LessonDTO(
        @NotBlank(message = "Lesson title is required")
        String title,
        @NotNull(message = "Lesson description is required")
        Lesson.Type type,
        @NotBlank(message = "Lesson link is required")
        String link,
        @NotNull(message = "Lesson quiz is required")
        Long quiz
) {
    public Lesson toLesson() {
        return new Lesson(title, type, link, quiz);
    }
}