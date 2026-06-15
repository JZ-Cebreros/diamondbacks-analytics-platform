# Diamondbacks Analytics Platform

A Spring Boot application that collects and analyzes Arizona Diamondbacks baseball data using the MLB Stats API.

## Project Goal

This project explores which baseball statistics are most closely associated with Diamondbacks success throughout a season.

The focus is intentionally narrow: rather than analyzing every MLB team, the application concentrates on the Arizona Diamondbacks and builds analytics around their games, team performance, and player statistics.

## Technologies Used

* Java 21+
* Spring Boot
* Maven
* Jackson JSON Parser
* REST APIs
* Git / GitHub

## Current Endpoints

### Team Information

```text
/diamondbacks
```

Returns basic information about the Arizona Diamondbacks.

### Game Results

```text
/diamondbacks/games
```

Returns completed games including:

* Date
* Opponent
* Home/Away
* Runs Scored
* Runs Allowed
* Win/Loss Result

### Team Statistics

```text
/diamondbacks/stats
```

Returns:

* Wins
* Losses
* Win Percentage
* Runs Scored
* Runs Allowed
* Run Differential

### Player Batting Statistics

```text
/diamondbacks/players/batting
```

Returns batting statistics for active Diamondbacks players including:

* Games Played
* At Bats
* Hits
* Home Runs
* RBI
* Batting Average
* OBP
* SLG
* OPS

## Planned Features

* Detailed game-level statistics
* Pitching analytics
* Correlation analysis
* Success-factor rankings
* Interactive dashboard
* Data persistence

## Author

Juan Cebreros

