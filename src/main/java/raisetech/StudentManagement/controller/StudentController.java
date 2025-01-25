//24 24:30~ controller 作成

package raisetech.StudentManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model){
    List<Student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentCourseList();

    model.addAttribute("studentList" , converter.convertStudentDetails(students, studentsCourses));
    return "studentList";
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

  @GetMapping("/newStudent")
  public String newStudent(Model model){
    model.addAttribute("studentDetail" , new StudentDetail());
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result){
    if (result.hasErrors()){
      return "registerStudent";
    }
//    System.out.println(studentDetail.getStudent().getName() + "さんが新規受講生として登録しました。");

//    課題28
//    1新規受講生情報を登録する処理を実装する。
//    2コース情報も一緒に登録できるように実装する。コースは単体で良い。
    service.registerStudent(studentDetail);

    return "redirect:/studentList";
  }
}
