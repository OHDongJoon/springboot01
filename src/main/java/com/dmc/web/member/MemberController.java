package com.dmc.web.member;

import com.dmc.config.util.JwtUtils;
import com.dmc.domain.category.enttiy.Category;
import com.dmc.domain.category.repository.CategoryRepository;
import com.dmc.domain.category.service.CategoryService;
import com.dmc.domain.member.dto.MemberEnterPriceSaveDto;
import com.dmc.domain.member.enumset.MemberRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.List;

@Tag(
        name = "사용자 -> 회원 가입 ",
        description = "사용자 -> 회원 가입 경로"
)
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('USER', 'ENTERPRISE', 'ORGAN')")
public class MemberController {


    private final JwtUtils jwtUtils;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @Operation(
            summary = "사용자 -> 회원가입",
            description = "step1"
    )
    @GetMapping("/join/step1")
    public String joinStep1(@ModelAttribute("saveDto") MemberEnterPriceSaveDto saveDto , Model model) {

        List<Category> depth01 = categoryService.findByParentAndRole(null, MemberRole.ENTERPRISE);
        List<Category> depth02 = categoryService.
                findByParentAndRole(categoryRepository.findById(1L).orElseThrow(null), MemberRole.ENTERPRISE);
        List<Category> depth03 = categoryService.
                findByParentAndRole(categoryRepository.findById(7L).orElseThrow(null), MemberRole.ENTERPRISE);

        saveDto.setRole(MemberRole.ENTERPRISE.getCode());

        model.addAttribute("depth01" , depth01);
        model.addAttribute("depth02" , depth02);
        model.addAttribute("depth03" , depth03);
        return "member/join_write_enterprise";
    }
}
