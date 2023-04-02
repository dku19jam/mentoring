package com.dku.mentoring.register.service;

import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.repository.MissionRepository;
import com.dku.mentoring.register.model.dto.list.SummarizedRegisterDto;
import com.dku.mentoring.register.model.dto.request.RegisterRequestDto;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.repository.RegisterRepository;
import com.dku.mentoring.user.entity.User;
import com.dku.mentoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegisterService {
    private final RegisterRepository registerRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public Long createRegister(Long userId, RegisterRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Mission mission = missionRepository.findById(dto.getMissionId()).orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다."));

        Register register = Register.builder()
                .user(user)
                .title(dto.getTitle())
                .body(dto.getBody())
                .mission(mission)
                .build();

        Register savedPost = registerRepository.save(register);
        return savedPost.getId();
        //TODO 코드 수정
    }

    public Page<SummarizedRegisterDto> getRegisters(Pageable pageable) {
        Page<Register> registers = registerRepository.findAll(pageable);
        return registers.map(SummarizedRegisterDto::new);
    }
}
