package com.manager.meetingManager.service;

import com.manager.meetingManager.domain.User;
import com.manager.meetingManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(final Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User saveOrUpdate(@NotNull final User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

}
