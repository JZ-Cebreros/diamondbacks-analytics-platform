package com.jzcebreros.diamondbacks_analytics;

import java.util.List;
import java.util.ArrayList;

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

                    // Skip future games that don't have scores yet
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
}