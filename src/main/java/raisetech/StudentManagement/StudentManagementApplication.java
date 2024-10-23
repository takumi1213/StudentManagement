package raisetech.StudentManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@GetMapping("/student")
	public String getStudent(@RequestParam String name){
		Student student = repository.searchByName(name);
		return student.getName() + " " + student.getAge() + "歳";
	}

	@PostMapping("/student")
	public void registerStudent(String name, int age) {
		repository.registerStudent(name,age);
	}

	@PatchMapping("/student")
	public void updateStudentName(String name, int age){
		repository.updateStudent(name, age);
	}

	@DeleteMapping("/student")
	public void deleteStudent(String name){
		repository.deleteStudent(name);
	}

//	GET POST
//	GET 取得する、リクエストの結果を受け取る
//	POST 情報を与える
}
