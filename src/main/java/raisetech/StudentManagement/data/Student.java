// 20回　27:30~
// 右クリック　生成　→ getter と　setter

package raisetech.StudentManagement.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  @Pattern(regexp = "^\\d+$")
  private String id;
  private String name;
  private String kanaName;
  private String nickname;

  @Email
  private String email;
  private String area;
  private int age;
  private String sex;
  private String remark;
  private boolean isDeleted;

}
