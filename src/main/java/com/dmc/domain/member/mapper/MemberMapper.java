package com.dmc.domain.member.mapper;

import com.dmc.common.YN;
import com.dmc.domain.member.dto.*;
import com.dmc.domain.member.entity.Member;
import com.dmc.domain.member.enumset.*;

import java.time.LocalDateTime;

public class MemberMapper {

    /**
     * 사용자 -> 회원 가입 -> 기업 회원 -> 저장정보 매핑
     *
     * @param saveDto {@link MemberEnterPriceSaveDto} 기업  회원 정보
     * @author Dong-Joon Oh
     */
    public static Member mapToEnterPriceSave(MemberEnterPriceSaveDto saveDto) {
         // 생성
            return Member.builder()
                    .role(MemberRole.ofCode(saveDto.getRole()))
                    .loginId(saveDto.getLoginId())
                    .password(saveDto.getPassword())
                    .firstLoginYn(YN.Y)
                    .build();
    }

    private MemberMapper() {
        throw new UnsupportedOperationException("새로운 인스턴스 방지");
    }

}
