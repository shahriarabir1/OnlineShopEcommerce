package com.RaqamiUniverse.RaqamiOnlineShop.service.user;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.UserDto;
import com.RaqamiUniverse.RaqamiOnlineShop.model.User;
import com.RaqamiUniverse.RaqamiOnlineShop.request.CreateUserRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.request.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long id);
    User getUserByEmail(String email);
    User createUser(CreateUserRequest user,String role);
    User updateUser(UpdateUserRequest user,Long id);
    void deleteUser(Long id);
    UserDto convertUsertoDto(User user);
    User getAuthenticatedUser();
}
