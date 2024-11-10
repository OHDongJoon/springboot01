package com.dmc.domain.member.entity;

import com.dmc.common.BaseEntity;
import com.dmc.common.YN;
import com.dmc.domain.category.enttiy.Category;
import com.dmc.domain.member.enumset.MemberRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "member")
public class Member extends BaseEntity implements UserDetails {
    /**
     * 회원 시퀀스
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name="role")
    @Convert(converter = MemberRole.MemberRoleConverter.class)
    private MemberRole role;

    @Column(name="login_id")
    private String loginId;

    /**
     * 위의 경우, password 필드는 JSON을 객체로 변환할 때는 사용되지만,
     * 객체를 JSON으로 변환할 때는 포함되지 않게 됩니다. 이를 통해 클라이언트로 전달되는
     * JSON에 민감한 정보가 포함되지 않도록 할 수 있습니다.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="password")
    private String password;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name="first_login_yn")
    @Comment("첫번째 로그인 여부")
    private YN firstLoginYn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(AuthorityUtils.createAuthorityList(this.role.getCode()));
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public boolean isFirstLoginYn() {
        return firstLoginYn == YN.Y;
    }

}
