package raisetech.StudentManagement.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

//受講生情報を取り扱うサービスです
//受講生の検索や登録・更新処理を行います。

@Service
public class StudentService {

  private StudentRepository repository;
  //受講生コンバータ
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

//  受講生の一覧検索を行う
//  全件検索を行うので、条件指定は行いません。
//  @return 受講生一覧

  public List<StudentDetail> searchStudentList(){
    List<Student> studentList = repository.search();
    List<StudentsCourses> studentsCoursesList = repository.searchStudentsCoursesList();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

//  受講生検索です。
//  IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します。
//  @param id 受講生ID
//  @return 受講生

  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getId());
    return new StudentDetail(student, studentsCourses);
  }

// 講義34回
//  public List<StudentsCourses> searchStudentCourseList(){
//    return repository.searchStudentsCoursesList();
//  }

  public List<Student> searchOver30StudentList(){
    List<Student> studentList = repository.search(); // すべての学生情報を取得

    List<Student> over30 = studentList.stream()
        .filter(student -> student.getAge() >= 30).toList();// 年齢が30歳以上の学生をフィルタリング

    return over30;
  }

  public List<StudentsCourses> searchJavaCourseList(){
    List<StudentsCourses> studentsCoursesList = repository.searchStudentsCoursesList(); // すべてのコースリストを取得

    List<StudentsCourses> courseNameList = studentsCoursesList.stream()
        .filter(studentsCourses -> studentsCourses.getCourseName().equals ("Java")).toList();
    // Java受講学生をフィルタリング

    return courseNameList;
  }

  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail){
    repository.registerStudent(studentDetail.getStudent());
//    TODO:コース情報登録も行う
    for(StudentsCourses studentsCourse:studentDetail.getStudentsCourses()) {
      studentsCourse.setStudentId(studentDetail.getStudent().getId());
      studentsCourse.setCourseStartAt(LocalDateTime.now());
      studentsCourse.setCourseEndAt(LocalDateTime.now().plusYears(1));
      repository.registerStudentsCourses(studentsCourse);
    }
    return studentDetail;
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail){
    repository.updateStudent(studentDetail.getStudent());
//    TODO:コース情報登録も行う
    for(StudentsCourses studentsCourse:studentDetail.getStudentsCourses()) {
      repository.updateStudentsCourses(studentsCourse);
    }
  }


}
