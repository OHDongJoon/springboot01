package com.dmc.domain.member.service;

import com.dmc.domain.member.dto.*;
import com.dmc.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IMemberService {
    void createEnterPriceMember(MemberEnterPriceSaveDto saveDto);

}
