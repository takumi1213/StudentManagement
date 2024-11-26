//24 24:30~ controller 作成

package raisetech.StudentManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList(){
    List<Student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentCourseList();

    return converter.convertStudentDetails(students, studentsCourses);
  }

  @GetMapping("/studentCourseList")
  public List<StudentsCourses> getStudentCourseList(){
    return service.searchStudentCourseList();
  }

  @GetMapping("/studentListOver30")
  public List<Student> getStudentListOver30(){
    return service.searchOver30StudentList();
  }

  @GetMapping("/studentJavaCourseList")
  public List<StudentsCourses> getJavaCourseList(){
    return service.searchJavaCourseList();
  }

}
