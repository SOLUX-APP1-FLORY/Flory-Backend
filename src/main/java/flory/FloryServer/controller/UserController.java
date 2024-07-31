package flory.FloryServer.controller;

import flory.FloryServer.domain.User;
import flory.FloryServer.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import flory.FloryServer.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/member")
    public ResponseEntity<Map<String, Object>> saveNickname(@RequestBody UserUpdateRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> userOptional = userRepository.findById(request.getId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.updateProfile(request.getNickname(), request.getGender());
                userRepository.save(user);

                Map<String, Object> result = new HashMap<>();
                result.put("id", user.getId());
                result.put("nickname", user.getNickname());
                result.put("email", user.getEmail());
                result.put("gender", user.getGender());
                result.put("createdAt", user.getCreatedAt());
                result.put("updatedAt", user.getUpdatedAt());

                response.put("isSuccess", true);
                response.put("code", "COMMON200");
                response.put("message", "성공입니다.");
                response.put("result", result);

                return ResponseEntity.ok(response);
            } else {
                response.put("isSuccess", false);
                response.put("code", "COMMON404");
                response.put("message", "사용자를 찾을 수 없습니다");
                response.put("result", null);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("isSuccess", false);
            response.put("code", "COMMON500");
            response.put("message", "닉네임 저장에 실패했습니다");
            response.put("result", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
