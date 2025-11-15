// src/main/java/hello/hello_spring/controller/dto/LoginResponse.java
package hello.hello_spring.controller.dto;

public class LoginResponse {
    private boolean success;
    private String message;
    private Long memberId;
    private String name;

    public LoginResponse(boolean success, String message, Long memberId, String name) {
        this.success = success;
        this.message = message;
        this.memberId = memberId;
        this.name = name;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Long getMemberId() { return memberId; }
    public String getName() { return name; }
}
