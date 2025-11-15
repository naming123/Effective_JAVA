// src/main/java/hello/hello_spring/controller/dto/SignupStep2Request.java
package hello.hello_spring.controller.dto;

public class SignupStep2Request {
    private String birth;
    private String studentId;
    private String major;
    private String graduationTerm;

    public String getBirth() { return birth; }
    public void setBirth(String birth) { this.birth = birth; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getGraduationTerm() { return graduationTerm; }
    public void setGraduationTerm(String graduationTerm) { this.graduationTerm = graduationTerm; }
}
