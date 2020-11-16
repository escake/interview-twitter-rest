package com.javalanguagezone.interviewtwitter.service.dto;

import com.javalanguagezone.interviewtwitter.domain.User;
import lombok.Getter;

@Getter
public class UserOverviewDTO extends UserDTO{

  private int numberOfTweets;
  private int numberOfFollowers;
  private int numberOfFollowing;

  public UserOverviewDTO(User user) {
    super(user);
    this.numberOfTweets = user.getTweets().size();
    this.numberOfFollowers = user.getFollowers().size();
    this.numberOfFollowing = user.getFollowing().size();
  }
}
