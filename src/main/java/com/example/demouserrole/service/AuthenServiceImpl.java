package com.example.demouserrole.service;

import com.example.demouserrole.entity.User;
import com.example.demouserrole.model.UserDetailsImpl;
import com.example.demouserrole.repository.RoleRepository;
import com.example.demouserrole.repository.UserRepository;
import com.example.demouserrole.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenServiceImpl implements AuthenService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtUtils;

    @Autowired
    public AuthenServiceImpl(AuthenticationManager authenticationManager,
                             UserRepository userRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder encoder,
                             JwtTokenProvider jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public LoginResponse authenticateUser(String userName, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateToken(authentication);

        //other option generate jwt token with role
        String jwt = jwtUtils.generateTokenWithAuthorities(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        return new LoginResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public void registerUser(String userName, String password, String email) {
        if (userRepository.existsAllByUserName(userName)) {
            throw new RuntimeException("Tài khoản đã tồn tại");
        }

        if (userRepository.existsAllByEmail(email)) {
            throw new RuntimeException("Email đã tồn tại trong hệ thống!");
        }
        // Create new user's account
        User user = User.builder()
                .userName(userName)
                .email(email)
                .password(encoder.encode(password))
                .build();

//        Set<Role> roles = new HashSet<>();
//        if (role == null) {
//            Role userRole = roleRepository.findByName(RoleEnum.USER)
//                    .orElseThrow(() -> new RuntimeException("Vai trò không tìm thấy trong hệ thống."));
//            roles.add(userRole);
//        } else {
//            role.forEach(roleItem -> {
//                try {
//                    RoleEnum value = RoleEnum.valueOf(roleItem);
//                    Role roleValue = roleRepository.findByName(value).orElseThrow(RuntimeException::new);
//                    roles.add(roleValue);
//                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
//                }
////                switch (roleItem) {
////                    case RoleEnum.ADMIN.va:
////                        Role adminRole = roleRepository.findByName(RoleEnum.ADMIN)
////                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
////                        roles.add(adminRole);
////
////                        break;
////                    case "mod":
////                        Role modRole = roleRepository.findByName(RoleEnum.MOD)
////                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
////                        roles.add(modRole);
////
////                        break;
////                    default:
////                        Role userRole = roleRepository.findByName(RoleEnum.USER)
////                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
////                        roles.add(userRole);
////                }
////            });
//            });
//            user.setRoles(roles);
        userRepository.save(user);
    }

}
