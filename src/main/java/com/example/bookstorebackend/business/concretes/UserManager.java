package com.example.bookstorebackend.business.concretes;

import com.example.bookstorebackend.business.abstracts.UserService;
import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.ErrorDataResult;
import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.dataAccess.abstracts.UserDAO;
import com.example.bookstorebackend.entities.User;
import com.example.bookstorebackend.entities.Verification;
import com.example.bookstorebackend.entities.dtos.request.LoginRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.RestorePasswordRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.SignUpRequestDTO;
import com.example.bookstorebackend.entities.dtos.response.UserLoginResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private UserDAO userDAO;

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

        User user = new User(
                signUpRequestDTO.getUsername(),
                signUpRequestDTO.getEmail(),
                signUpRequestDTO.getPassword()
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

    @Override
    public DataResult<UserLoginResponseDTO> logIn(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Override
    public Result verifyEmailWithLink(int userId, String token) {
        return null;
    }

    @Override
    public Result sendForgotPasswordEmail(String email) {
        return null;
    }

    @Override
    public Result restorePassword(RestorePasswordRequestDTO restoreRequest) {
        return null;
    }
}
