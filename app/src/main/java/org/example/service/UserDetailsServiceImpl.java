package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entities.UserInfo;
import org.example.model.UserInfoDto;
import org.example.repository.UserRepository;
import org.example.utils.ValidationUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;

@Component
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

//        log.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userRepository.findByUserName(username);
        if(user == null){
//            log.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        log.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto){
        return userRepository.findByUserName(userInfoDto.getUserName());
    }

    public Boolean signupUser(UserInfoDto userInfoDto){
        ValidationUtil.validateUserAttributes(userInfoDto);
        UserInfo existingUser = checkIfUserAlreadyExist(userInfoDto);

        if (Objects.nonNull(existingUser)) {
            if (passwordEncoder.matches(userInfoDto.getPassword(), existingUser.getPassword())) {
                log.info("User already exists and password matches. Skipping signup.");
                return false;
            } else {
                log.warn("User already exists but password does not match.");
                throw new IllegalArgumentException("User already exists with different password.");
            }
        }

        String encodedPassword = passwordEncoder.encode(userInfoDto.getPassword());
        String userId = UUID.randomUUID().toString();
        UserInfo newUser = new UserInfo(userId, userInfoDto.getUserName(), encodedPassword, new HashSet<>());
        userRepository.save(newUser);
        log.info("User registered successfully.");
        return true;
    }
}
