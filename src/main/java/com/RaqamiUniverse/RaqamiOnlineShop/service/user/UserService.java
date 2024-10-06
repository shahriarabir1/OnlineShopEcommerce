package com.RaqamiUniverse.RaqamiOnlineShop.service.user;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.UserDto;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.UserAlreadyExists;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.UserNotFound;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Role;
import com.RaqamiUniverse.RaqamiOnlineShop.model.User;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.UserRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.request.CreateUserRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User getUserById(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFound("user not found"));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user= Optional.of(userRepository.findByEmail(email)).orElseThrow(()->new UserNotFound("user not found"));
        return user;
    }

    @Override
    public User createUser(CreateUserRequest user,String role) {
        Optional.ofNullable(userRepository.findByEmail(user.getEmail()))
                .ifPresent(u -> { throw new UserAlreadyExists("user already exists"); });
        User newUser=new User();
        Role roles=new Role();
        if(role==null){
            roles.setRoleName("ROLE_USER");
        }else {
            roles.setRoleName(role);
        }
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setPhone(user.getPhone());
        newUser.setRoles(List.of(roles));
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(UpdateUserRequest user,Long id) {
        User users=userRepository.findById(id).orElseThrow(()->new UserNotFound("user not found"));
        User newUser=new User();
        newUser.setId(id);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(users.getAddress());
        newUser.setEmail(users.getEmail());
        newUser.setPassword(users.getPassword());
        return userRepository.save(newUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFound("user not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDto convertUsertoDto(User user) {
        return  modelMapper.map(user,UserDto.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        return userRepository.findByEmail(email);
    }
}
