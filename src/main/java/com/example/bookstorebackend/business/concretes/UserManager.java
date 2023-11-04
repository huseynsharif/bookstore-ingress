package com.example.bookstorebackend.business.concretes;

import com.example.bookstorebackend.business.abstracts.UserService;
import com.example.bookstorebackend.core.utilities.emailSendings.abstracts.EmailService;
import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.ErrorDataResult;
import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.core.utilities.results.SuccessResult;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private VerificationDAO verificationDAO;

    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    @Override
    public Result signUp(SignUpRequestDTO signUpRequestDTO) {
        if (!Objects.equals(signUpRequestDTO.getPassword(), signUpRequestDTO.getCpassword())) {
            return new ErrorDataResult<>("Passwords must be same.");
        }

        if (this.userDAO.existsUserByUsername(signUpRequestDTO.getUsername())) {
            return new ErrorDataResult<>("Username was already taken.");
        }

        if (this.userDAO.existsUserByEmail(signUpRequestDTO.getEmail())) {
            return new ErrorDataResult<>("Email was already taken.");
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
        return null;
    }

    @Override
    public Result verifyEmailWithLink(int userId, String token) {
        return null;
    }
}
