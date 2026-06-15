package com.jzcebreros.diamondbacks_analytics;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final MlbApiService mlbApiService;

    public HomeController(MlbApiService mlbApiService) {
        this.mlbApiService = mlbApiService;
    }

    @GetMapping("/")
    public String home() {
        return "Diamondbacks Analytics Platform is running!";
    }

    @GetMapping("/diamondbacks")
    public Team diamondbacks() {
        return new Team(109, "Arizona Diamondbacks");
    }

    @GetMapping("/teams")
    public List<Team> teams() {
        return List.of(
                new Team(109, "Arizona Diamondbacks"),
                new Team(119, "Los Angeles Dodgers"),
                new Team(144, "Atlanta Braves")
        );
    }

    @GetMapping("/diamondbacks/live")
    public String getLiveDiamondbacksData() {
        return mlbApiService.getDiamondbacksTeamData();
    }

    @GetMapping("/diamondbacks/info")
    public DiamondbacksInfo getDiamondbacksInfo() {
    return mlbApiService.getDiamondbacksInfo();
    }

    @GetMapping("/diamondbacks/games")
    public List<GameResult> getDiamondbacksGames() {
    return mlbApiService.getDiamondbacksGames();
    }

    @GetMapping("/diamondbacks/schedule/raw")
    public String getRawDiamondbacksSchedule() {
    return mlbApiService.getRawDiamondbacksSchedule();
    }

   @GetMapping("/diamondbacks/stats")
    public TeamStats getDiamondbacksStats() {
    return mlbApiService.getDiamondbacksTeamStats();
    }

    @GetMapping("/diamondbacks/players/batting")
    public List<PlayerBattingStats> getDiamondbacksPlayerBattingStats() {
    return mlbApiService.getDiamondbacksPlayerBattingStats();
    }
}