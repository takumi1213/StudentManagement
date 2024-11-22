package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList(){
    List<Student> studentList = repository.search(); // すべての学生情報を取得

    List<Student> upper30 = studentList.stream()
        .filter(student -> student.getAge() >= 30).toList();// 年齢が30歳以上の学生をフィルタリング

    return upper30;
  }

  public List<StudentsCourses> searchStudentCourseList(){
    List<StudentsCourses> studentsCoursesList = repository.searchStudentsCourses(); // すべてのコースリストを取得

    List<StudentsCourses> courseNameList = studentsCoursesList.stream()
        .filter(studentsCourses -> studentsCourses.getCourseName().equals ("Java")).toList();// 年齢が30歳以上の学生をフィルタリング

    return courseNameList;
  }

}
