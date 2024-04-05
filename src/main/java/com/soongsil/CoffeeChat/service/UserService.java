package com.soongsil.CoffeeChat.service;

import com.soongsil.CoffeeChat.dto.CreateMenteeRequest;
import com.soongsil.CoffeeChat.dto.CreateMentorRequest;
import com.soongsil.CoffeeChat.entity.Mentee;
import com.soongsil.CoffeeChat.entity.Mentor;
import com.soongsil.CoffeeChat.entity.User;
import com.soongsil.CoffeeChat.repository.MenteeRepository;
import com.soongsil.CoffeeChat.repository.MentorRepository;
import com.soongsil.CoffeeChat.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;
    private final UserRepository userRepository;
    public UserService(MentorRepository mentorRepository, MenteeRepository menteeRepository, UserRepository userRepository){
        this.menteeRepository=menteeRepository;
        this.mentorRepository=mentorRepository;
        this.userRepository=userRepository;
    }
    @Transactional
    public Mentor saveMentorInformation(String username, CreateMentorRequest dto){
        User user= userRepository.findByUsername(username);
        user.setRole("ROLE_MENTEE");
        Mentor mentor=Mentor.from(dto);
        return mentorRepository.save(mentor);
    }

    @Transactional
    public Mentee saveMenteeInformation(String username, CreateMenteeRequest dto){
        User user= userRepository.findByUsername(username);
        user.setRole("ROLE_MENTOR");
        Mentee mentee=Mentee.from(dto);
        return menteeRepository.save(mentee);
    }
}