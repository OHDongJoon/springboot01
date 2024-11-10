package com.dmc.domain.category.dto;

import com.dmc.domain.category.enttiy.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
public class CategoryListDto {

    private Long id;

    private Long parentId;

    private Long depth;

    @Comment("카테고리 제목")
    private String categoryTitle;

    private String fullSequencePath;   // 상위 시퀀스 경로 (콤마 구분)
    private String fullCategoryPath;   // 상위 카테고리 이름 경로 (콤마 구분)

    // 생성자, getters, setters
    public CategoryListDto(Long sequenceId, Long parentSequenceId, String categoryName, String fullSequencePath, String fullCategoryPath) {
        this.id = sequenceId;
        this.parentId = parentSequenceId;
        this.categoryTitle = categoryName;
        this.fullSequencePath = fullSequencePath;
        this.fullCategoryPath = fullCategoryPath;
    }

    public CategoryListDto(Category category) {
        this.id = category.getId();
        this.depth = category.getDepth();
        this.parentId = category.getParent() != null ? category.getParent().getId() : null;
        this.categoryTitle = category.getCategoryTitle();
    }
}
