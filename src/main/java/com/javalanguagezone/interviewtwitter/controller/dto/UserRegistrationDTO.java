package com.javalanguagezone.interviewtwitter.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
  private String username;
  private String fullName;
  private String password;
}
