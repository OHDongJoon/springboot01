package com.dmc.web.member.api;

import com.dmc.constants.DmcConstants;
import com.dmc.domain.member.dto.MemberEnterPriceSaveDto;
import com.dmc.domain.member.service.IMemberService;
import com.dmc.exception.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "사용자 -> 회원가입",
        description = "사용자 회원가입 CRUD API"
)
@RestController
@RequestMapping(path = "/member/api/join",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class MemberJoinRestController {

    private final IMemberService memberService;

    @Operation(
            summary = "사용자 -> 회원가입 -> 기업 회원 저장",
            description = "사용자 -> 회원가입 -> 기업 회원 저장"
    )
    @PostMapping("/enter-price/save")
    public ResponseEntity<ResponseDto<Void>> createEnterPriceMember(
            @Valid @ModelAttribute MemberEnterPriceSaveDto saveDto) {

        memberService.createEnterPriceMember(saveDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(DmcConstants.STATUS_201, DmcConstants.MESSAGE_201));
    }
}
