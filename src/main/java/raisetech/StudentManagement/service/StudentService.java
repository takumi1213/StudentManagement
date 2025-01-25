package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList(){
    return repository.search();
  }

  public List<StudentsCourses> searchStudentCourseList(){
    return repository.searchStudentsCourses();
  }

  public List<Student> searchOver30StudentList(){
    List<Student> studentList = repository.search(); // すべての学生情報を取得

    List<Student> over30 = studentList.stream()
        .filter(student -> student.getAge() >= 30).toList();// 年齢が30歳以上の学生をフィルタリング

    return over30;
  }

  public List<StudentsCourses> searchJavaCourseList(){
    List<StudentsCourses> studentsCoursesList = repository.searchStudentsCourses(); // すべてのコースリストを取得

    List<StudentsCourses> courseNameList = studentsCoursesList.stream()
        .filter(studentsCourses -> studentsCourses.getCourseName().equals ("Java")).toList();
    // Java受講学生をフィルタリング

    return courseNameList;
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail){
    repository.registerStudent(studentDetail.getStudent());
//    TODO:コース情報登録も行う
  }

}
