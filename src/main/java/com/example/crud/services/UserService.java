package com.example.crud.services;

import com.example.crud.dto.request.UserCreationRequestDTO;
import com.example.crud.dto.request.UserUpdateRequestDTO;
import com.example.crud.dto.response.PageResponseDTO;
import com.example.crud.dto.response.UserResponseDTO;
import com.example.crud.exceptions.BusinessException;
import com.example.crud.models.User;
import com.example.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUserService(UserCreationRequestDTO request) throws BusinessException {
        Optional<User> userOptional = userRepository.findByEmailAndIsActiveAndIsDeleted(request.getEmail(), true, false);
        if (userOptional.isPresent()) {
            throw new BusinessException("USER_ALREADY_EXISTS", "User with the same email id already exists");
        }
        User user = User.builder()
                .id(UUID.randomUUID())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
        userRepository.save(user);
        return user.toDTO();
    }

    public PageResponseDTO<List<UserResponseDTO>> listActiveUsersService(String search, Pageable paging) {
        Page<User> pagedUserData = userRepository.findByNameLikeAndIsActiveAndIsDeleted(search, true, false, paging);
        List<UserResponseDTO> userResponseDTOList = pagedUserData.getContent().stream().map(User::toDTO).toList();
        return PageResponseDTO.<List<UserResponseDTO>>builder()
                .currentPage(pagedUserData.getNumber())
                .totalItems(pagedUserData.getTotalElements())
                .totalPages(pagedUserData.getTotalPages())
                .data(userResponseDTOList)
                .build();
    }

    public UserResponseDTO getUserDetailsService(UUID userId) throws BusinessException {
        Optional<User> userOptional = userRepository.findByIdAndIsDeleted(userId, false);
        if (userOptional.isEmpty()) {
            throw new BusinessException("USER_DOES_NOT_EXIST", "The user you are looking for does not exist!");
        }
        return userOptional.get().toDTO();
    }

    public UserResponseDTO updateUserService(UserUpdateRequestDTO request, UUID userId) throws BusinessException {
        Optional<User> userOptional = userRepository.findByIdAndIsDeleted(userId, false);
        if (userOptional.isEmpty()) {
            throw new BusinessException("USER_DOES_NOT_EXIST", "The user you are looking for does not exist!");
        }
        userOptional.get().setFirstName(request.getFirstName());
        userOptional.get().setLastName(request.getLastName());
        userOptional.get().setDateOfBirth(request.getDateOfBirth());
        userOptional.get().setEmail(request.getEmail());
        userOptional.get().setPhone(request.getPhone());
        userOptional.get().setIsActive(request.getIsActive());
        userRepository.save(userOptional.get());
        return userOptional.get().toDTO();
    }

    public Boolean deleteUserService(UUID userId) throws BusinessException {
        Optional<User> userOptional = userRepository.findByIdAndIsDeleted(userId, false);
        if (userOptional.isEmpty()) {
            throw new BusinessException("USER_DOES_NOT_EXIST", "The user you are looking for does not exist!");
        }
        userOptional.get().setIsDeleted(true);
        userRepository.save(userOptional.get());
        return true;
    }

//    public Boolean restoreUserService(UUID userId) throws BusinessException {
//        Optional<User> userOptional = userRepository.findByIdAndIsDeleted(userId, true);
//        if (userOptional.isEmpty()) {
//            throw new BusinessException("USER_DOES_NOT_EXIST", "No deleted user of given ID exists!");
//        }
//        userOptional.get().setIsDeleted(false);
//        userRepository.save(userOptional.get());
//        return true;
//    }

    public Map toggleActiveStatusService(UUID userId) throws BusinessException {
        Optional<User> userOptional = userRepository.findByIdAndIsDeleted(userId, false);
        if (userOptional.isEmpty()) {
            throw new BusinessException("USER_DOES_NOT_EXIST", "The user you are looking for does not exist!");
        }
        Boolean isActive = !userOptional.get().getIsActive();
        userOptional.get().setIsActive(isActive);
        userRepository.save(userOptional.get());
        return Map.of("isActive", isActive);
    }

}
