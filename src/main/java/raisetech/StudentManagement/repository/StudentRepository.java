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
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchStudentCourseList();

//  受講生IDに紐づく受講生コース情報を検索します。
//  @param studentId 受講生ID
//  @return 受講生IDに紐づく受講生コース情報

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourse> searchStudentCourse(String studentId);

//  28回　課題
//  INSERT INTO 最初の値はDBの値。VALUESの値はStudent.javaファイル記載の値
//  記載間違えないようにDBとファイルからそれぞれコピペする

//  受講生を新規登録します。
//  IDに関しては自動採番を行う
//  @param student 受講生

  @Insert(
      "INSERT INTO students(name, kana_name, nickname, email, area, age, sex, remark, is_deleted)"
      + " VALUES(#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

//  受講生コース情報を新規登録します。
//  IDに関しては自動採番を行う
//  @param students_courses 受講生コース情報

  @Insert("INSERT INTO students_courses(student_id, course_name, course_start_at, course_end_at)"
      + "VALUES(#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentCourse(StudentCourse studentCourse);

//  29回　課題
//  受講生を更新します。
//  @param student 受講生
  @Update(
      "UPDATE students SET name = #{name}, kana_name = #{kanaName}, nickname = #{nickname}, "
          + "email = #{email}, area = #{area}, age = #{age}, sex = #{sex}, remark = #{remark}, "
          + "is_deleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

//  受講生コース情報のコース名を更新します。
//  @param studentCourse 受講生コース情報

  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
  void updateStudentCourse(StudentCourse studentCourses);

}
