package com.javalanguagezone.interviewtwitter.service.dto;

import com.javalanguagezone.interviewtwitter.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
public class UserOverviewDTO extends UserDTO{

  private int numberOfTweets;
  private int numberOfFollowers;
  private int numberOfFollowing;

  public UserOverviewDTO(User user, int numberOfTweets, int numberOfFollowers, int numberOfFollowing) {
    super(user);
    this.numberOfTweets = numberOfTweets;
    this.numberOfFollowers = numberOfFollowers;
    this.numberOfFollowing = numberOfFollowing;
  }
}
