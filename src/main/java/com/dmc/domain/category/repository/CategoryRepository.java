package com.dmc.domain.category.repository;

import com.dmc.domain.category.enttiy.Category;
import com.dmc.domain.member.enumset.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.subCategories")
//    List<Category> findAllWithSubCategories();

    Optional<Category> findByIdAndRole(long l, MemberRole memberRole);

    List<Category> findByDepthAndRole(Long depth, MemberRole memberRole);

    Category findByCategoryTitle(String categoryName);

    List<Category> findByParentAndRole(Category parent, MemberRole role);
}
