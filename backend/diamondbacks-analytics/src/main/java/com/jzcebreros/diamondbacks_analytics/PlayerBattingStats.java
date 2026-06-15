package com.jzcebreros.diamondbacks_analytics;

public class PlayerBattingStats {
    private String playerName;
    private int gamesPlayed;
    private int atBats;
    private int hits;
    private int homeRuns;
    private int rbi;
    private double battingAverage;
    private double obp;
    private double slg;
    private double ops;

    public PlayerBattingStats(String playerName, int gamesPlayed, int atBats, int hits,
                              int homeRuns, int rbi, double battingAverage,
                              double obp, double slg, double ops) {
        this.playerName = playerName;
        this.gamesPlayed = gamesPlayed;
        this.atBats = atBats;
        this.hits = hits;
        this.homeRuns = homeRuns;
        this.rbi = rbi;
        this.battingAverage = battingAverage;
        this.obp = obp;
        this.slg = slg;
        this.ops = ops;
    }

    public String getPlayerName() { return playerName; }
    public int getGamesPlayed() { return gamesPlayed; }
    public int getAtBats() { return atBats; }
    public int getHits() { return hits; }
    public int getHomeRuns() { return homeRuns; }
    public int getRbi() { return rbi; }
    public double getBattingAverage() { return battingAverage; }
    public double getObp() { return obp; }
    public double getSlg() { return slg; }
    public double getOps() { return ops; }
}