package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "member")  // DB 테이블 이름이 member면 그대로 OK
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "login_id")
    private String loginId;

    private String email;
    private String phone;
    private String password;

    private String birth;

    @Column(name = "student_id")
    private String studentId;

    private String major;

    @Column(name = "graduation_term")
    private String graduationTerm;

    // === getter / setter ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getBirth() { return birth; }
    public void setBirth(String birth) { this.birth = birth; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getGraduationTerm() { return graduationTerm; }
    public void setGraduationTerm(String graduationTerm) { this.graduationTerm = graduationTerm; }
}
