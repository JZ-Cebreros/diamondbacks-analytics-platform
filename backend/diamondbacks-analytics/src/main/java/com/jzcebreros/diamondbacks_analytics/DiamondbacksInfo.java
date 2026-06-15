package com.jzcebreros.diamondbacks_analytics;

public class DiamondbacksInfo {

    private int id;
    private String name;
    private String abbreviation;
    private String venue;
    private String division;

    public DiamondbacksInfo(int id, String name, String abbreviation, String venue, String division) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.venue = venue;
        this.division = division;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getVenue() {
        return venue;
    }

    public String getDivision() {
        return division;
    }
}