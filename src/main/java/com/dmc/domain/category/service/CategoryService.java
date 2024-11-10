package com.dmc.domain.category.service;

import com.dmc.domain.category.enttiy.Category;
import com.dmc.domain.category.repository.CategoryRepository;
import com.dmc.domain.member.enumset.MemberRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private List<Category> findByDepthAndRole (Long depth , MemberRole role) {
        return categoryRepository.findByDepthAndRole(depth, role);
    }

    public List<Category> findByParentAndRole(Category parent, MemberRole memberRole) {
        return categoryRepository.findByParentAndRole(parent , memberRole);
    }
}
