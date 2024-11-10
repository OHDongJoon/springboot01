package com.dmc.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name="MemberEnterPriceSaveDto" ,description = " 기업 회원 저장 정보 CLASS")
public class MemberEnterPriceSaveDto {

    @Comment("회원 시퀀스")
    private Long id;

    @Comment("회원 타입")
    @NotEmpty(message = "11회원타입이 없습니다 관리자에게 문의해주세요.")
    private String role;

    @Comment("로그인 아이디")
    @NotEmpty(message = "12사업자 번호 아이디를 입력해주세요")
    private String loginId;

    @Comment("비밀번호")
    @NotEmpty(message = "13비밀번호를 입력해주세요")
    @Size(min = 8, message = "14비밀번호는 최소 8자 이상이어야 합니다")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[a-z\\d@#$%^&+=!]{8,20}$",
            message = "15비밀번호는 8자리 이상, 대문자, 소문자, 숫자 및 특수 문자를 포함해야 합니다")
    private String password;
    @NotEmpty(message = "16확인 비밀번호를 입력해주세요")
    private String passwordConfirm;

    @Comment("1차업종")
    @NotNull(message = "17.1차업종을 선택해주세요.")
    private Long enterpriseIndustryCategoryId_1;

    @Comment("2차업종")
    @NotNull(message = "18.2차업종을 선택해주세요.")
    private Long enterpriseIndustryCategoryId_2;

    @Comment("3차업종")
    @NotNull(message = "19.3차업종을 선택해주세요.")
    private Long enterpriseIndustryCategoryId_3;

}
