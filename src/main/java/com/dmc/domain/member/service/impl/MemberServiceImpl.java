package com.dmc.domain.member.service.impl;


import com.dmc.domain.category.enttiy.Category;
import com.dmc.domain.category.repository.CategoryRepository;
import com.dmc.domain.member.dto.MemberEnterPriceSaveDto;
import com.dmc.domain.member.entity.Member;
import com.dmc.domain.member.mapper.MemberMapper;
import com.dmc.domain.member.repository.MemberRepository;
import com.dmc.domain.member.service.IMemberService;
import com.dmc.exception.ApiNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements IMemberService {


    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final CategoryRepository categoryRepository;

    /**
     * 사용자 -> 회원 가입 -> 기업 회원 -> 저장
     * @param saveDto {@link MemberEnterPriceSaveDto} 기업  회원 정보
     * @author Dong-Joon Oh
     */
    @Override
    public void createEnterPriceMember(MemberEnterPriceSaveDto saveDto) {

        checkPasswordMatch(saveDto.getPassword() , saveDto.getPasswordConfirm());

        saveDto.setPassword(passwordEncoder.encode(saveDto.getPassword()));
        
        Member member = MemberMapper.mapToEnterPriceSave(saveDto);

        Category category = categoryRepository.findById(saveDto.getEnterpriseIndustryCategoryId_3())
                .orElseThrow(() -> new ApiNotFoundException("기업 회원가입" , "업종 카테고리 id" , saveDto.getEnterpriseIndustryCategoryId_3().toString()));

        member.setCategory(category);

        memberRepository.save(member);
    }


    /**
     * 사용자 -> 회원가입 -> 비밀번호 , 확인비밀번호 체크
     * @param password 비밀번호
     * @param passwordConfirm 확인 비밀번호
     * @author Dong-Joon Oh
     */
    public void checkPasswordMatch(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new ApiNotFoundException("비밀번호가 일치하지 않습니다.");
        }
    }

}
