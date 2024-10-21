package raisetech.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "Takumi Hamamoto" ;
	private String age = "33" ;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}
	@GetMapping("/StudentInfo")
	public String getStudentInfo(){
		return name + " " + age + "歳";
	}

	@PostMapping("/StudentInfo")
	public void setStudentInfo(String name, String age) {
		this.name = name;
		this.age = age;
	}

	@PostMapping("/StudentName")
	public void updateStudentName(String name){
		this.name = name;
	}

//	GET POST
//	GET 取得する、リクエストの結果を受け取る
//	POST 情報を与える
}
