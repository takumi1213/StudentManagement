// 20回　29:30~
// 新規　インターフェイス　Mybatis のルールで必要
//　SQLを操作する場所　？
// @Mapper を入れるだけで　Mybatis が管理しないと認識する

package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

//  23回　課題
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCourses();

}
