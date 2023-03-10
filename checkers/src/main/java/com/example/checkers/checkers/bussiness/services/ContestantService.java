package com.example.checkers.checkers.bussiness.services;
import com.example.checkers.checkers.exceptions.PlayerNotFoundException;
import com.example.checkers.checkers.models.dto.ContestantDTO;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.repositories.ContestantsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContestantService {
    private ContestantsRepository contestantsRepository;

    public ContestantService() {
    }

    public List<ContestantDTO> getPlayers() {
        return this.contestantsRepository.findAll().stream().map(ContestantDTO::fromEntity).collect(Collectors.toList());
    }

    public Optional<ContestantDTO> findContestantById(Long id) {
        Optional<Contestant> gamer = contestantsRepository.findById(id);
        if (gamer.isEmpty()){
            throw new PlayerNotFoundException("Player" + "not found");
        }
        return gamer.map(ContestantDTO::fromEntity);
    }
}
