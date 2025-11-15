// src/main/java/hello/hello_spring/controller/dto/SignupStep1Request.java
package hello.hello_spring.controller.dto;

public class SignupStep1Request {
    private String name;
    private String email;
    private String loginId;
    private String phone;
    private String password;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
