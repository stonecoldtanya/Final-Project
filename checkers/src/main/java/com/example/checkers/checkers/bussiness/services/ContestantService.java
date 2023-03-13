package com.example.checkers.checkers.bussiness.services;

import com.example.checkers.checkers.repositories.ContestantsRepository;
import com.example.checkers.checkers.exceptions.PlayerNotFoundException;
import com.example.checkers.checkers.models.dto.ContestantDTO;
import com.example.checkers.checkers.models.dto.UserLoginDTO;
import com.example.checkers.checkers.models.dto.UserRegistrationDTO;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ContestantService {
    private final ContestantsRepository userRepository;
    private LoggedUser userSession;
    private final ModelMapper modelMapper;

    public ContestantService(ContestantsRepository userRepository, LoggedUser userSession, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }

    public void register(UserRegistrationDTO registrationDTO) {
        Contestant user = modelMapper.map(registrationDTO, Contestant.class);
        userRepository.save(user);
    }

    public boolean login(UserLoginDTO loginDTO) {
        Optional<Contestant> user = userRepository.findByUsername(loginDTO.getUsername());
        if (user.isEmpty()) return false;
        if (!user.get().getPassword().equals(loginDTO.getPassword())) return false;
        userSession.login(user.get());
        return true;
    }
    public Contestant getLoggedUser(){
        return userRepository.findById(userSession.getId()).get();
    }
    public void logout() {
        userSession.logout();
    }


    public Optional<Contestant> findById(long id) {
        return this.userRepository.findById(id);
    }

    public List<ContestantDTO> getPlayers() {
        return this.userRepository.findAll().stream().map(ContestantDTO::fromEntity).collect(Collectors.toList());
    }

    public Optional<ContestantDTO> findContestantById(Long id) {
        Optional<Contestant> gamer = userRepository.findById(id);
        if (gamer.isEmpty()){
            throw new PlayerNotFoundException("Player" + "not found");
        }
        return gamer.map(ContestantDTO::fromEntity);
    }
}
