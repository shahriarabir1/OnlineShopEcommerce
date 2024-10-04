package com.RaqamiUniverse.RaqamiOnlineShop.controller.user;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.UserDto;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.UserAlreadyExists;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.UserNotFound;
import com.RaqamiUniverse.RaqamiOnlineShop.model.User;
import com.RaqamiUniverse.RaqamiOnlineShop.request.CreateUserRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.request.UpdateUserRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.response.ApiResponse;
import com.RaqamiUniverse.RaqamiOnlineShop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        try{
            User user=userService.getUserById(id);
            UserDto dto=userService.convertUsertoDto(user);
            return ResponseEntity.ok(new ApiResponse("User Found",dto));
        }catch (UserNotFound e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email) {
        try{
            User user=userService.getUserByEmail(email);
            UserDto dto=userService.convertUsertoDto(user);
            return ResponseEntity.ok(new ApiResponse("User Found",dto));
        }catch (UserNotFound e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/user/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest user,@RequestParam String role) {
        try{
            User user1=userService.createUser(user,role);
            UserDto dto=userService.convertUsertoDto(user1);
            return ResponseEntity.ok(new ApiResponse("User Created",dto));
        }catch (UserNotFound e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest user, @PathVariable Long id) {
        try{
            User user1=userService.updateUser(user,id);
            UserDto dto=userService.convertUsertoDto(user1);
            return ResponseEntity.ok(new ApiResponse("User Updated",dto));
        }catch (UserAlreadyExists e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }

    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id) {
        try{
           userService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse("User Deleted",null));
        }catch (UserNotFound e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }

    }

    @GetMapping("/user/auth")
    public ResponseEntity<ApiResponse> getUserAuth() {
        try{
            User user=userService.getAuthenticatedUser();
            return ResponseEntity.ok(new ApiResponse("User Found",user));
        }catch (UserNotFound e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }


}
