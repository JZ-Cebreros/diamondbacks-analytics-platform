package com.jzcebreros.diamondbacks_analytics;

public class TeamStats {
    private int season;
    private String teamName;
    private int wins;
    private int losses;
    private double winPercentage;
    private int runsScored;
    private int runsAllowed;
    private int runDifferential;

    public TeamStats(int season, String teamName, int wins, int losses,
                     int runsScored, int runsAllowed) {
        this.season = season;
        this.teamName = teamName;
        this.wins = wins;
        this.losses = losses;
        this.runsScored = runsScored;
        this.runsAllowed = runsAllowed;
        this.runDifferential = runsScored - runsAllowed;

        int totalGames = wins + losses;
        this.winPercentage = totalGames > 0 ? (double) wins / totalGames : 0;
    }

    public int getSeason() {
        return season;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getRunsAllowed() {
        return runsAllowed;
    }

    public int getRunDifferential() {
        return runDifferential;
    }
}