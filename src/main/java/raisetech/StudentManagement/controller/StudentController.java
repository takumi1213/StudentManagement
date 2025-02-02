//24 24:30~ controller 作成

package raisetech.StudentManagement.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

//受講生の検索や登録、更新などを行うREST APIとして受け付けるcontrollerです。
@Validated
@RestController
public class StudentController {

  // 受講生サービス
  private StudentService service;

//  コンストラクタ
//  @param service 受講生サービス
//  @param converter 受講生コンバータ
  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

//  受講生詳細の検索です。全件検索を行うので、条件指定は行わないものになります。
//  @return 受講生の詳細一覧（全件）
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList(){
    return service.searchStudentList();
  }

//  受講生詳細の検索です。idに紐付く任意の受講生の情報を取得します。
//  @param id　受講生ID
//  @return 受講生情報
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable @Size(min = 1, max = 3) @Pattern(regexp = "^\\d+$") String id){
    return service.searchStudent(id);
  }

  @GetMapping("/studentListOver30")
  public List<Student> getStudentListOver30(){
    return service.searchOver30StudentList();
  }

  @GetMapping("/studentJavaCourseList")
  public List<StudentCourse> getJavaCourseList(){
    return service.searchJavaCourseList();
  }

//  受講生詳細の登録を行います。
//  @param studentDetail 受講生詳細
//　@return　実行結果
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail){

//    課題28
//    1新規受講生情報を登録する処理を実装する。
//    2コース情報も一緒に登録できるように実装する。コースは単体で良い。
    StudentDetail responsStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responsStudentDetail);
  }

//  受講生詳細の更新を行います
//  キャンセルフラグの更新もここで行います（論理削除）
//  @param　studentDetail 受講生詳細
//  @return 実行結果

  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail){
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました！！！");
  }


}
