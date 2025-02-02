// 20回　29:30~
// 新規　インターフェイス　Mybatis のルールで必要
//　SQLを操作する場所　？
// @Mapper を入れるだけで　Mybatis が管理しないと認識する

package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

// 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。

@Mapper
public interface StudentRepository {

//  受講生の全件検索を行います。
// @return 受講生一覧（全件）
  List<Student> search();

//  受講生の検索を行います。
//  @param id 受講生ID
//  @return 受講生
  Student searchStudent(String id);

//  23回　課題
//  受講生のコースの全件検索を行います。
//  @return 受講生のコース情報（全件）
  List<StudentCourse> searchStudentCourseList();

//  受講生IDに紐づく受講生コース情報を検索します。
//  @param studentId 受講生ID
//  @return 受講生IDに紐づく受講生コース情報
  List<StudentCourse> searchStudentCourse(String studentId);

//  28回　課題
//  INSERT INTO 最初の値はDBの値。VALUESの値はStudent.javaファイル記載の値
//  記載間違えないようにDBとファイルからそれぞれコピペする

//  受講生を新規登録します。
//  IDに関しては自動採番を行う
//  @param student 受講生
  void registerStudent(Student student);

//  受講生コース情報を新規登録します。
//  IDに関しては自動採番を行う
//  @param students_courses 受講生コース情報
  void registerStudentCourse(StudentCourse studentCourse);

//  29回　課題
//  受講生を更新します。
//  @param student 受講生
  void updateStudent(Student student);

//  受講生コース情報のコース名を更新します。
//  @param studentCourse 受講生コース情報
  void updateStudentCourse(StudentCourse studentCourses);

}
