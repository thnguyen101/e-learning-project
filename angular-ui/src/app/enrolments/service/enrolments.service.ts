import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PageWrapper} from "../../common/dto/page-wrapper";
import {EnrolmentDTO} from "../model/enrolment-dto";
import {EnrolmentWithCourseDto} from "../model/enrolment-with-course-dto";
import {Enrolment} from "../model/enrolment";

@Injectable({
  providedIn: 'root'
})
export class EnrolmentsService {

  http = inject(HttpClient);
  resourcePath = '/bff/api/enrollments' // fix typo later

  getAllEnrollments(pageNumber: number = 0, pageSize: number = 10) {
    const url = `${this.resourcePath}?page=${pageNumber}&size=${pageSize}`;
    return this.http.get<PageWrapper<EnrolmentDTO>>(url)
  }

  getEnrolmentWithCourseByEnrollmentId(enrollmentId: number) {
    return this.http.get<EnrolmentWithCourseDto>(`${this.resourcePath}/${enrollmentId}/content`);
  }

  getEnrollmentById(enrollmentId: string) {
    return this.http.get<EnrolmentDTO>(`${this.resourcePath}/${enrollmentId}`);
  }

  getEnrolmentByCourseId(courseId: number) {
    return this.http.get<Enrolment>(`${this.resourcePath}/course/${courseId}`);
  }

  countEnrolmentsByCourseId(courseId: number) {
    return this.http.get<number>(`${this.resourcePath}/count`, {params: {courseId: courseId.toString()}});
  }

  markLessonAsCompleted(enrollmentId: string, lessonId: string) {
    return this.http.put(`${this.resourcePath}/${enrollmentId}/lessons/${lessonId}mark?completed`, null);
  }

  markLessonAsIncomplete(enrollmentId: string, lessonId: string) {
    return this.http.put(`${this.resourcePath}/${enrollmentId}/lessons/${lessonId}mark?incomplete`, null);
  }

}