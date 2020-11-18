package com.javalanguagezone.interviewtwitter.service;

import com.javalanguagezone.interviewtwitter.controller.dto.UserRegistrationDTO;
import com.javalanguagezone.interviewtwitter.domain.User;
import com.javalanguagezone.interviewtwitter.repository.UserRepository;
import com.javalanguagezone.interviewtwitter.service.dto.UserDTO;
import com.javalanguagezone.interviewtwitter.service.dto.UserOverviewDTO;
import com.javalanguagezone.interviewtwitter.service.exception.UnknownUsernameException;
import com.javalanguagezone.interviewtwitter.service.exception.UsernameTakenException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class UserService implements UserDetailsService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUser(username);
    if(user == null)
      throw new UsernameNotFoundException(username);
    return user;
  }

  @Transactional
  public Collection<UserDTO> getUsersFollowing(Principal principal) {
    User user = getUser(principal.getName());
    return convertUsersToDTOs(user.getFollowing());
  }

  @Transactional
  public Collection<UserDTO> getUsersFollowers(Principal principal) {
    User user = getUser(principal.getName());
    return convertUsersToDTOs(user.getFollowers());
  }

  @Transactional
  public UserOverviewDTO getUserOverview(String username) {
    User user = getUser(username);
    if(user == null)
      throw new UnknownUsernameException(username);
    return new UserOverviewDTO(user);
  }

  @Transactional
  public void register(UserRegistrationDTO userRegistrationDTO) {
    if(getUser(userRegistrationDTO.getUsername()) != null)
      throw new UsernameTakenException(userRegistrationDTO.getUsername());
    User user = new User(userRegistrationDTO.getUsername(), userRegistrationDTO.getFullName(), userRegistrationDTO.getPassword());
    if(!user.isValid())
      throw new RuntimeException(); //todo InvalidUserException
    userRepository.save(user);
  }

  private User getUser(String username) {
    return userRepository.findOneByUsername(username);
  }

  private List<UserDTO> convertUsersToDTOs(Set<User> users) {
    return users.stream().map(UserDTO::new).collect(toList());
  }

}
