package flory.FloryServer.controller;

import flory.FloryServer.domain.User;
import flory.FloryServer.dto.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import flory.FloryServer.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/member")
    public ResponseEntity<String> saveNickname(@RequestBody UserUpdateRequest request) {
        try {
            Optional<User> userOptional = userRepository.findById(request.getId());
            if(userOptional.isPresent()){
                User user = userOptional.get();
                user.updateProfile(request.getNickname(), request.getGender());
                userRepository.save(user);
                return ResponseEntity.ok("Profile updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save nickname");
        }
    }
}
