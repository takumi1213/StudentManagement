package raisetech.StudentManagement.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
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

//  受講生詳細の一覧検索を行です。全件検索を行うので、条件指定は行いません。
//  @return 受講生一覧

  public List<StudentDetail> searchStudentList(){
    List<Student> studentList = repository.search();
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

//  受講生詳細検索です。
//  IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します。
//  @param id 受講生ID
//  @return 受講生詳細

  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentCourse = repository.searchStudentCourse(student.getId());
    return new StudentDetail(student, studentCourse);
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

  public List<StudentCourse> searchJavaCourseList(){
    List<StudentCourse> studentCoursesList = repository.searchStudentCourseList(); // すべてのコースリストを取得

    List<StudentCourse> courseNameList = studentCoursesList.stream()
        .filter(studentsCourses -> studentsCourses.getCourseName().equals ("Java")).toList();
    // Java受講学生をフィルタリング

    return courseNameList;
  }

//  受講生詳細の登録を行います。
//  受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐付ける値とコース開始日、コース終了日を設定します。
//  @param studentDetail　受講生詳細
//  @return　登録情報を付与した受講生詳細

  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail){
    Student student = studentDetail.getStudent();
//    準備
    repository.registerStudent(student);
//    TODO:コース情報登録も行う
//    やりたいことをやる
    studentDetail.getStudentCourseList().forEach(studentsCourse -> {
      initStudentsCourse(studentsCourse, student);
      repository.registerStudentCourse(studentsCourse);
    });
//    結果
    return studentDetail;
  }

//　受講生コース情報を登録する際の初期設定を設定する。
//　@param studentsCourse　受講生コース情報
//　@param student　受講生

  private void initStudentsCourse(StudentCourse studentCourse, Student student) {
    LocalDateTime now = LocalDateTime.now();
    studentCourse.setStudentId(student.getId());

    studentCourse.setCourseStartAt(now);
    studentCourse.setCourseEndAt(now.plusYears(1));
  }

//  受講生詳細の更新を行います。
//  受講生と受講生コース情報をそれぞれ更新します。
//  @param studentDetail　受講生詳細

  @Transactional
  public void updateStudent(StudentDetail studentDetail){
    repository.updateStudent(studentDetail.getStudent());
//    TODO:コース情報登録も行う
    studentDetail.getStudentCourseList()
        .forEach(studentCourse -> repository.updateStudentCourse(studentCourse));
  }


}
