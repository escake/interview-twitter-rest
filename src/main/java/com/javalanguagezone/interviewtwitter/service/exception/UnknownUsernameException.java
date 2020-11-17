package com.javalanguagezone.interviewtwitter.service.exception;

import lombok.Getter;

public class UnknownUsernameException extends RuntimeException {
  @Getter
  private String username;

  public UnknownUsernameException(String username) {
    super(username);
    this.username = username;
  }
}
