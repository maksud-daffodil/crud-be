package com.example.crud.controllers;

import com.example.crud.dto.request.UserCreationRequestDTO;
import com.example.crud.dto.request.UserUpdateRequestDTO;
import com.example.crud.dto.response.BaseResponse;
import com.example.crud.dto.response.PageResponseDTO;
import com.example.crud.dto.response.UserResponseDTO;
import com.example.crud.exceptions.BusinessException;
import com.example.crud.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse<?>> createUser(@RequestBody UserCreationRequestDTO request) throws BusinessException {
        UserResponseDTO response = userService.createUserService(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse<>(201, response, null, null));
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<?>> listUsers(
            @RequestParam(required = false) String search,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable paging
    ) {
        PageResponseDTO<List<UserResponseDTO>> response = userService.listActiveUsersService(search, paging);
        return ResponseEntity.ok(BaseResponse.ok(HttpStatus.OK.value(), response));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<?>> getUserDetails(@PathVariable("userId") UUID userId) throws BusinessException {
        UserResponseDTO response = userService.getUserDetailsService(userId);
        return ResponseEntity.ok(BaseResponse.ok(HttpStatus.OK.value(), response));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<BaseResponse<?>> updateUser(
            @RequestBody UserUpdateRequestDTO request,
            @PathVariable("userId") UUID userId
    ) throws BusinessException {
        UserResponseDTO response = userService.updateUserService(request, userId);
        return ResponseEntity.ok(BaseResponse.ok(HttpStatus.OK.value(), response));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<BaseResponse<?>> deleteUser(@PathVariable("userId") UUID userId) throws BusinessException {
        Boolean response = userService.deleteUserService(userId);
        return ResponseEntity.noContent().build();
    }

//    @PatchMapping("/restore/{userId}")
//    public ResponseEntity<BaseResponse<?>> restoreUser(@PathVariable("userId") UUID userId) throws BusinessException {
//        Boolean response = userService.restoreUserService(userId);
//        return ResponseEntity.noContent().build();
//    }

    @PatchMapping("/toggle-active-status/{userId}")
    public ResponseEntity<BaseResponse<?>> toggleActiveStatus(@PathVariable("userId") UUID userId) throws BusinessException {
        Map response = userService.toggleActiveStatusService(userId);
        return ResponseEntity.ok(BaseResponse.ok(HttpStatus.OK.value(), response));
    }

}
