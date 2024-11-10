package com.dmc.domain.category.enttiy;

import com.dmc.common.YN;
import com.dmc.domain.member.enumset.MemberRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "category")
@Schema(
        name="Category" ,
        description = "기관(업종) 기업(대표분야) 카테고리"
)
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(name="role")
    @Convert(converter = MemberRole.MemberRoleConverter.class)
    @Comment("회원 타입(기업 , 기관)")
    private MemberRole role;

    @Column(name="depth")
    @Comment("카테고리 깊이")
    private Long depth;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Comment("부모 시퀀스 (1차 카테고리는 null)")
    private Category parent;


    @Column(name="category_title")
    @Comment("카테고리 제목")
    private String categoryTitle;

    @Column(name="statistics_category_title")
    @Comment("기업 통계에 사용할 카테고리 제목")
    private String statisticsCategoryTitle;

    @Column(name="is_view")
    @Comment("노출 여부")
    private YN isView;

    @Column(name="is_del")
    @Comment("삭제 여부")
    private YN isDel;

    public Category getGrandparent() {
        return (parent != null) ? parent.getParent() : null;
    }

    public String getParentTitle() {
        return (parent != null) ? parent.getCategoryTitle() : null;
    }

    public String getGrandparentTitle() {
        Category grandparent = getGrandparent();
        return (grandparent != null) ? grandparent.getCategoryTitle() : null;
    }
}
