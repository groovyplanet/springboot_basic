package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor //모든 멤버변수를 받는 생성자
@NoArgsConstructor // 기본 생성자
@Data // getter , setter 생성 , toString 오버라이딩까지 자동으로 해줌
@Builder
public class QuizVO {

    @Pattern(regexp = "[a-zA-Z0-9]{8,}")
    private String id;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String pw;

}
