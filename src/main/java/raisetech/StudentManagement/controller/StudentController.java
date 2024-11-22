//24 24:30~ controller 作成

package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList(){
    return service.searchStudentList();
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
