package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor //모든 멤버변수를 받는 생성자
@NoArgsConstructor // 기본 생성자
@Data // getter , setter 생성 , toString 오버라이딩까지 자동으로 해줌
@Builder
public class MemoVO {
    private Long mno;
    @NotBlank(message = "메모는 필수 입력 사항입니다.")
    @Size(min = 5, message = "메모는 최소 5글자 이상이어야 합니다.")
    private String memo;

    @Pattern(regexp = "^(\\d{3}-\\d{3,4}-\\d{4})$", message = "전화번호 형식이 잘못되었습니다.")
    private String phone;

    @Pattern(regexp = "[0-9]{4}", message = "비밀번호는 숫자 4자리여야 합니다.")
    private String pw;

    @NotBlank(message = "비밀글 여부를 선택해주세요.")
    private String secret = "n";

}
