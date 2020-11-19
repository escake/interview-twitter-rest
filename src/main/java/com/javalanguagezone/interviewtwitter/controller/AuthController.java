package com.javalanguagezone.interviewtwitter.controller;

import com.javalanguagezone.interviewtwitter.controller.dto.ErrorMessage;
import com.javalanguagezone.interviewtwitter.controller.dto.LoginSucessDTO;
import com.javalanguagezone.interviewtwitter.controller.dto.UserLoginDTO;
import com.javalanguagezone.interviewtwitter.controller.dto.UserRegistrationDTO;
import com.javalanguagezone.interviewtwitter.service.UserService;
import com.javalanguagezone.interviewtwitter.service.exception.UnknownUsernameException;
import com.javalanguagezone.interviewtwitter.service.exception.UsernameTakenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {

  private UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  @ResponseStatus(CREATED)
  public void register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
    userService.register(userRegistrationDTO);
  }

  @PostMapping("/login")
  public LoginSucessDTO login(@RequestBody UserLoginDTO userLoginDTO) {
    return userService.login(userLoginDTO);
  }

  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public ErrorMessage handleUsernameTakenException(UsernameTakenException e){
    log.warn("", e);
    return new ErrorMessage(String.format("Username '%s' is taken.", e.getUsername()));
  }

  //todo extract duplicate code
  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public ErrorMessage handleUnknownUsernameException(UnknownUsernameException e){
    log.warn("", e);
    return new ErrorMessage(String.format("Unknown user '%s'", e.getUsername()));
  }

}
