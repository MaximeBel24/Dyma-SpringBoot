package com.dyma.tennis.service;

import com.dyma.tennis.Player;
import com.dyma.tennis.PlayerList;
import com.dyma.tennis.PlayerToSave;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PlayerService {

    public List<Player> getAllPlayers() {
        return PlayerList.ALL.stream()
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .toList();
    }

    public Player getByLastName(String lastName) {
        return PlayerList.ALL.stream()
                .filter(player -> player.lastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(lastName));
    }

    public Player create(PlayerToSave playerToRegister){
        RankingCalculator rankingCalculator = new RankingCalculator(PlayerList.ALL, playerToRegister);

        List<Player> players = rankingCalculator.getNewPlayersRanking();
        return players.stream()
                .filter(player -> player.lastName().equals(playerToRegister.lastName()))
                .findFirst().get();
    }
}
