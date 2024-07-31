//package flory.FloryServer.web.controller;
//
//import flory.FloryServer.apiPayload.ApiResponse;
//import flory.FloryServer.service.DiaryService.DiaryCreateService;
//import flory.FloryServer.web.dto.DiaryCreateRequestDTO;
//import flory.FloryServer.web.dto.UserUpdateResponseDTO;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1")
//@RequiredArgsConstructor
//public class DiaryCreateController {
//    private final DiaryCreateService diaryCreateService;
//
//    @PatchMapping("/diary")
//    public ApiResponse<UserUpdateResponseDTO.UpdateResultDTO> userUpdate(@RequestHeader String token, @RequestBody @Valid DiaryCreateRequestDTO.CreateDTO requestDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String userId = userDetails.getUsername();
//
//
//        // 사용자 정보 업데이트
//        UserUpdateResponseDTO.UpdateResultDTO resultDTO = diaryCreateService.createDiary(userId, requestDTO);
//
//        // 성공 응답 생성
//        return ApiResponse.onSuccess(resultDTO);
//    }
//}
