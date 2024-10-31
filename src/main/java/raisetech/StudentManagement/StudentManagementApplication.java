package raisetech.StudentManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

//	20 35:30~ Autowired
	@Autowired
	private StudentRepository repository;

//	20 50:30~
//	private String name = "Takumi Hamamoto" ;
//	private String age = "33" ;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}
	@GetMapping("/studentList")
	public List<Student> getStudentList(){
		return repository.search();
	}

//	23 課題
	@GetMapping("/studentCourseList")
	public List<StudentCourse> getStudentCourseList(){
		return repository.searchcourselist();
	}

//	GET POST
//	GET 取得する、リクエストの結果を受け取る
//	POST 情報を与える
}
