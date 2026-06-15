package com.jzcebreros.diamondbacks_analytics;

public class GameResult {

    private String date;
    private String opponent;
    private String homeAway;
    private int runsScored;
    private int runsAllowed;
    private String result;

    public GameResult(String date, String opponent, String homeAway, int runsScored, int runsAllowed, String result) {
        this.date = date;
        this.opponent = opponent;
        this.homeAway = homeAway;
        this.runsScored = runsScored;
        this.runsAllowed = runsAllowed;
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getHomeAway() {
        return homeAway;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getRunsAllowed() {
        return runsAllowed;
    }

    public String getResult() {
        return result;
    }
}