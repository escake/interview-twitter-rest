package com.javalanguagezone.interviewtwitter.service.exception;

import lombok.Getter;

public class UsernameTakenException extends RuntimeException {
  @Getter
  private String username;

  public UsernameTakenException(String username) {
    super(username);
    this.username = username;
  }
}
