//package flory.FloryServer.service.DiaryService;
//
//
//import flory.FloryServer.domain.User;
//import flory.FloryServer.repository.DiaryRepository;
//import flory.FloryServer.repository.FlowerRepository;
//import flory.FloryServer.repository.UserRepository;
//import flory.FloryServer.web.dto.DiaryCreateRequestDTO;
//import flory.FloryServer.web.dto.UserUpdateResponseDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class DiaryCreateService {
//    @Autowired
//    private DiaryRepository diaryRepository;
//
//    @Autowired
//    private FlowerRepository flowerRepository;
//
//    @Transactional
//    public UserUpdateResponseDTO.UpdateResultDTO createDiary(String userId, DiaryCreateRequestDTO.CreateDTO requestDTO) {
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setNickname(requestDTO.getNickname());
//            user.setGender(requestDTO.getGender());
//
//            userRepository.save(user);
//
//            return UserUpdateResponseDTO.UpdateResultDTO.builder()
//                    .uid(user.getUid())
//                    .nickname(user.getNickname())
//                    .email(user.getEmail())
//                    .gender(user.getGender())
//                    .createdAt(user.getCreatedAt())
//                    .updatedAt(user.getUpdatedAt())
//                    .build();
//        } else {
//            throw new RuntimeException("User not found");
//        }
//    }
//}
