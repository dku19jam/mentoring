package com.dku.mentoring.register.service;

import com.dku.mentoring.global.auth.JwtProvider;
import com.dku.mentoring.mission.exception.MissionAlreadyInProgress;
import com.dku.mentoring.mission.exception.MissionNotFoundException;
import com.dku.mentoring.mission.model.dto.request.MissionBonusRequestDto;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import com.dku.mentoring.mission.repository.MissionBonusRepository;
import com.dku.mentoring.mission.repository.MissionRepository;
import com.dku.mentoring.register.model.dto.list.SummarizedRegisterDto;
import com.dku.mentoring.register.model.dto.request.RegisterRequestDto;
import com.dku.mentoring.register.model.dto.response.SingleRegisterResponseDto;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterFile;
import com.dku.mentoring.register.repository.RegisterRepository;
import com.dku.mentoring.user.entity.User;
import com.dku.mentoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegisterService {
    private final RegisterRepository registerRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final MissionBonusRepository missionBonusRepository;
    private final JwtProvider jwtProvider;
    private final FileUploadService fileUploadService;

    /**
     * 미션 인증 글 등록
     *
     * @param dto    등록할 글 dto
     */
    @Transactional
    public Long createRegister(User user, Long missionId, RegisterRequestDto dto) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new MissionNotFoundException("해당 미션이 없습니다."));

        for(Register register : user.getRegisters()) {
            if(register.getMission().getId().equals(mission.getId())) {
                throw new MissionAlreadyInProgress("이미 등록한 미션입니다.");
            }
        }
        //TODO 추가 미션 인증을 체크 박스로 인증하고 싶음
        Register register = dto.toEntity(user, mission);

        List<MissionBonusRequestDto> missionList = dto.getMissionList();
        List<MissionBonus> bonusList = missionBonusRepository.findAllByMissionId(missionId);
        if(bonusList != null) {
            for (MissionBonusRequestDto missionBonusRequestDto : missionList) {
                for (MissionBonus missionBonus : bonusList) {
                    if (missionBonusRequestDto.getPlusMission().equals(missionBonus.getPlusMission())) {
                        register.getMission().addRegister(register);
                    }
                }

            }
        }

        Register savedPost = registerRepository.save(register);

        List<RegisterFile> registerFiles = fileUploadService.uploadImage(dto.getFiles(), user, savedPost.getId());
        savedPost.setFiles(registerFiles);
        return savedPost.getId();
    }

    /**
     * 미션 인증 글 상세 조회
     *
     * @param registerId 조회할 글 id
     */
    public SingleRegisterResponseDto findOne(Long registerId) {
        Register register = registerRepository.findById(registerId).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        return new SingleRegisterResponseDto(register);
    }

    /**
     * 전체 등록 글 조회
     *
     * @param pageable 페이징 방법
     *
     * @return 페이징된 목록
     */
    public Page<SummarizedRegisterDto> getRegisters(Pageable pageable) {
        Page<Register> registers = registerRepository.findAllRegisters(pageable);
        return registers.map(SummarizedRegisterDto::new);
    }

    /**
     * 사용자가 등록 글 전체 조회
     *
     */
    public Page<SummarizedRegisterDto> getRegistersByUser(HttpServletRequest request, Pageable pageable) {
        User user = getMemberFromRequest(request);
        Long userId = user.getId();

        Page<Register> registers = registerRepository.findByUserId(userId, pageable);
        return registers.map(SummarizedRegisterDto::new);
    }

    /**
     * 등록 글 수정
     *
     * @param registerId 수정할 글 id
     */
    @Transactional
    public Long updateRegister(Long registerId, HttpServletRequest request, RegisterRequestDto dto) {
        Register register = registerRepository.findById(registerId).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        User user = getMemberFromRequest(request);
        if(!register.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 권한이 없습니다.");
        }
        register.update(dto);
        return register.getId();
    }

    /**
     * 등록 글 삭제
     *
     * @param registerId 삭제할 글 id
     */
    @Transactional
    public void deleteRegister(Long registerId, HttpServletRequest request) {
        User user = getMemberFromRequest(request);
        Register register = registerRepository.findById(registerId).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        if(!register.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 권한이 없습니다.");
        }
        registerRepository.delete(register);
    }

    /**
     * 등록 글 승인
     *
     * @param registerId 승인할 글 id
     */
    @Transactional
    public void approveRegister(Long registerId, HttpServletRequest request) {
        Register register = registerRepository.findById(registerId).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        User user = getMemberFromRequest(request);
        if(user.getRoles().stream().noneMatch(role -> role.getRolename().equals("ROLE_ADMIN"))) {
            throw new IllegalArgumentException("해당 권한이 없습니다.");
        }
        register.approve();
    }

    /**
     * 요청 헤더로부터 토큰 및 유저 추출
     * @param request
     * @return user 객체
     */
    public User getMemberFromRequest(HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String studentId = jwtProvider.getStudentId(token);
        User user = userRepository.findByStudentId(studentId).orElseThrow(IllegalAccessError::new);

        return user;
    }
}
