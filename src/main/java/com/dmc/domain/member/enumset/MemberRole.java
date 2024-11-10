package com.dmc.domain.member.enumset;

import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
public enum MemberRole {

    NONE(null, "", 0),
    ENTERPRISE("ROLE_ENTERPRISE", "기업", 1),
    ORGAN("ROLE_ORGAN" , "기관" , 2),
    USER("ROLE_USER", "개인", 3);

    private final String code;
    private final String detail;
    private final int value;

    public static MemberRole ofCode(String code) {
        return EnumSet.allOf(MemberRole.class).stream()
                .filter(v -> v != NONE && v.getCode().equalsIgnoreCase(code))
                .findAny()
                .orElse(NONE);
    }

    @Override
    public String toString() {
        return this.name();
    }


    @Converter(autoApply = true)
    public static class MemberRoleConverter extends JsonEnumConverter<MemberRole> {}

}
