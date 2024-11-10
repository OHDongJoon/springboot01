package com.dmc.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 모든 Entity 상속받는 공통 추상 클래스<br />
 * 직렬화 저장이 필요할 수도 있으므로 {@link Serializable} 인터페이스가 추가됨<br />
 *
 * @since 2023-05-24
 * @author TAEROK HWANG
 * @version 1.0.0
 */
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    /** 삭제 여부 */
    @Builder.Default
    @Comment("삭제 여부")
    @Column(name = "del_yn", nullable = false, columnDefinition = "VARCHAR(1) DEFAULT 'N'")
    protected YN delYn = YN.N;

    /** 등록자 아이디 */
    @CreatedBy
    @Comment("등록자 아이디")
    @Column(nullable = false, updatable = false)
    protected String createdId;

    /** 등록 일시 */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Comment("등록 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime createdAt;

    /** 수정자 아이디 */
    @LastModifiedBy
    @Comment("수정자 아이디")
    @Column(insertable = false)
    protected String updatedId;

    /** 수정 일시 */
    @LastModifiedDate
    @Column(insertable = false)
    @Comment("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime updatedAt;

}
