package com.jzcebreros.diamondbacks_analytics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MlbApiService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MlbApiService() {
        this.restClient = RestClient.create();
    }

    public String getDiamondbacksTeamData() {
        String url = "https://statsapi.mlb.com/api/v1/teams/109";

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }

    public DiamondbacksInfo getDiamondbacksInfo() {
        return new DiamondbacksInfo(
                109,
                "Arizona Diamondbacks",
                "AZ",
                "Chase Field",
                "National League West"
        );
    }

    public List<GameResult> getDiamondbacksGames() {
        String url =
                "https://statsapi.mlb.com/api/v1/schedule?teamId=109&sportId=1&season=2026";

        String json = restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);

        List<GameResult> gameResults = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode dates = root.get("dates");

            for (JsonNode dateNode : dates) {
                String date = dateNode.get("date").asText();
                JsonNode games = dateNode.get("games");

                for (JsonNode game : games) {
                    JsonNode teams = game.get("teams");

                    JsonNode awayTeam = teams.get("away").get("team");
                    JsonNode homeTeam = teams.get("home").get("team");

                    boolean diamondbacksAreHome =
                            homeTeam.get("id").asInt() == 109;

                    String opponent = diamondbacksAreHome
                            ? awayTeam.get("name").asText()
                            : homeTeam.get("name").asText();

                    String homeAway =
                            diamondbacksAreHome ? "Home" : "Away";

                    if (!teams.get("home").has("score")
                            || !teams.get("away").has("score")) {
                        continue;
                    }

                    int runsScored = diamondbacksAreHome
                            ? teams.get("home").get("score").asInt()
                            : teams.get("away").get("score").asInt();

                    int runsAllowed = diamondbacksAreHome
                            ? teams.get("away").get("score").asInt()
                            : teams.get("home").get("score").asInt();

                    String result =
                            runsScored > runsAllowed ? "W" : "L";

                    gameResults.add(
                            new GameResult(
                                    date,
                                    opponent,
                                    homeAway,
                                    runsScored,
                                    runsAllowed,
                                    result
                            )
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse Diamondbacks schedule",
                    e
            );
        }

        return gameResults;
    }

    public String getRawDiamondbacksSchedule() {
        String url =
                "https://statsapi.mlb.com/api/v1/schedule?teamId=109&sportId=1&season=2026";

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }

    public TeamStats getDiamondbacksTeamStats() {
        List<GameResult> games = getDiamondbacksGames();

        int wins = 0;
        int losses = 0;
        int runsScored = 0;
        int runsAllowed = 0;

        for (GameResult game : games) {
            runsScored += game.getRunsScored();
            runsAllowed += game.getRunsAllowed();

            if ("W".equals(game.getResult())) {
                wins++;
            } else if ("L".equals(game.getResult())) {
                losses++;
            }
        }

        return new TeamStats(
                2026,
                "Arizona Diamondbacks",
                wins,
                losses,
                runsScored,
                runsAllowed
        );
    }

    public List<PlayerBattingStats> getDiamondbacksPlayerBattingStats() {
        String url =
                "https://statsapi.mlb.com/api/v1/teams/109/roster?rosterType=active&hydrate=person(stats(type=season,group=hitting,season=2026))";

        String json = restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);

        List<PlayerBattingStats> playerStats = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode roster = root.get("roster");

            for (JsonNode rosterEntry : roster) {

                JsonNode person = rosterEntry.get("person");

                if (person == null) {
                    continue;
                }

                String playerName = person.get("fullName").asText();

                JsonNode stats = person.get("stats");

                if (stats == null || stats.isEmpty()) {
                    continue;
                }

                JsonNode splits = stats.get(0).get("splits");

                if (splits == null || splits.isEmpty()) {
                    continue;
                }

                JsonNode stat = splits.get(0).get("stat");

                int gamesPlayed = stat.path("gamesPlayed").asInt();
                int atBats = stat.path("atBats").asInt();
                int hits = stat.path("hits").asInt();
                int homeRuns = stat.path("homeRuns").asInt();
                int rbi = stat.path("rbi").asInt();

                double battingAverage =
                        Double.parseDouble(stat.path("avg").asText("0"));
                double obp =
                        Double.parseDouble(stat.path("obp").asText("0"));
                double slg =
                        Double.parseDouble(stat.path("slg").asText("0"));
                double ops =
                        Double.parseDouble(stat.path("ops").asText("0"));

                playerStats.add(
                        new PlayerBattingStats(
                                playerName,
                                gamesPlayed,
                                atBats,
                                hits,
                                homeRuns,
                                rbi,
                                battingAverage,
                                obp,
                                slg,
                                ops
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse Diamondbacks player batting stats",
                    e
            );
        }

        return playerStats;
    }
}