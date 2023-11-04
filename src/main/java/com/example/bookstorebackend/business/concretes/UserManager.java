package com.example.bookstorebackend.business.concretes;

import com.example.bookstorebackend.business.abstracts.UserService;
import com.example.bookstorebackend.core.security.entities.CustomUserDetails;
import com.example.bookstorebackend.core.security.jwt.JwtUtils;
import com.example.bookstorebackend.core.utilities.emailSendings.abstracts.EmailService;
import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.*;
import com.example.bookstorebackend.core.utilities.results.*;
import com.example.bookstorebackend.dataAccess.abstracts.RoleDAO;
import com.example.bookstorebackend.dataAccess.abstracts.UserDAO;
import com.example.bookstorebackend.dataAccess.abstracts.VerificationDAO;
import com.example.bookstorebackend.entities.ERole;
import com.example.bookstorebackend.entities.Role;
import com.example.bookstorebackend.entities.User;
import com.example.bookstorebackend.entities.Verification;
import com.example.bookstorebackend.entities.dtos.request.LoginRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.SignUpRequestDTO;
import com.example.bookstorebackend.entities.dtos.response.UserLoginResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private VerificationDAO verificationDAO;

    private PasswordEncoder passwordEncoder;
    private EmailService emailService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Override
    public Result signUp(SignUpRequestDTO signUpRequestDTO) {
        if (!Objects.equals(signUpRequestDTO.getPassword(), signUpRequestDTO.getCpassword())) {
            throw new PasswordIsNotSameException("Passwords must be same.");
        }

        if (this.userDAO.existsUserByUsername(signUpRequestDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        if (this.userDAO.existsUserByEmail(signUpRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        Set<String> strRoles = signUpRequestDTO.getRoles();
        Set<Role> roles = new HashSet<>();


        strRoles.forEach(role -> {
            switch (role.toUpperCase()) {
                case "STUDENT":
                    Role adminRole = this.roleDAO.findRoleByRoleName(ERole.STUDENT);
                    roles.add(adminRole);
                    break;

                case "AUTHOR":
                    Role userRole = this.roleDAO.findRoleByRoleName(ERole.AUTHOR);
                    roles.add(userRole);
                    break;

            }
        });


        User user = new User(
                signUpRequestDTO.getUsername(),
                signUpRequestDTO.getEmail(),
                passwordEncoder.encode(signUpRequestDTO.getPassword()),
                roles

        );

        this.userDAO.save(user);

        // Email Verification
        Verification emailVerification = new Verification(user);
        this.verificationDAO.save(emailVerification);
        this.emailService.sendVerificationEmailHtml(
                user.getUsername(),
                user.getEmail(),
                verificationLinkGenerator(
                        user.getId(),
                        emailVerification.getToken()
                )
        );

        return new SuccessResult("User was successfully saved.");
    }

    private String verificationLinkGenerator(int id, String token) {
        return null;
    }

    @Override
    public DataResult<UserLoginResponseDTO> logIn(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new SuccessDataResult<>(new UserLoginResponseDTO(
                userDetails.getId(),
                userDetails.getUsername(),
                jwt,
                roles), "Successfully logged in.");

    }

    @Override
    public Result verifyEmailWithLink(int userId, String token) {
        Verification emailVerification = this.verificationDAO.findVerificationByUser_Id(userId);

        if (emailVerification == null) {
            throw new NotFoundException("Cannot find verification with userId: " + userId);
        }

        if (!Objects.equals(emailVerification.getToken(), token)) {
            return new ErrorResult("Token is incorrect: " + token);
        }

        if (emailVerification.getCreatedAt().plusMinutes(30).isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("Token is expired.");
        }

        User user = this.userDAO.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException("Cannot find user with given userId: "+userId);
        }

        user.setVerified(true);
        this.userDAO.save(user);
        this.verificationDAO.delete(emailVerification);
        return new SuccessResult("Successfully verified.");
    }
}
