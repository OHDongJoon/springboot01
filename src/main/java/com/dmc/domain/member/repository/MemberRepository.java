package com.dmc.domain.member.repository;

import com.dmc.common.YN;
import com.dmc.domain.member.entity.Member;
import com.dmc.domain.member.enumset.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.EnumSet;
import java.util.Optional;

public interface MemberRepository extends
        JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member>, MemberRepositoryCustom {

    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    @Query("SELECT m FROM Member m " +
            "JOIN FETCH m.category c " +            // 회원이 속한 3차 카테고리()
            "LEFT JOIN FETCH c.parent p " +         // 2차 카테고리()
            "LEFT JOIN FETCH p.parent pp " +        // 1차 카테고리()
            "WHERE m.id = :memberId")
    Member findMemberWithCategoriesById(@Param("memberId") Long memberId);

}
