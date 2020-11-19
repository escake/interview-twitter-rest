package com.javalanguagezone.interviewtwitter.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginSucessDTO {
  private String token;
  private String username;
}
