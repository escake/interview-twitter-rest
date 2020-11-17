package com.javalanguagezone.interviewtwitter.service.exception;

public class InvalidTweetException extends RuntimeException {

  public InvalidTweetException(String tweet) {
    super("'" +  tweet + "'");
  }
}
