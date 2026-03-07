package com.redcatdev86.ui.model;

import java.util.List;

public class GeneratedTeamResult {

    private final TeamBuilderRow sponsor;
    private final String teamType;
    private final int rosterSize;
    private final List<CyclistRow> cyclists;

    public GeneratedTeamResult(TeamBuilderRow sponsor,
                               String teamType,
                               int rosterSize,
                               List<CyclistRow> cyclists) {
        this.sponsor = sponsor;
        this.teamType = teamType;
        this.rosterSize = rosterSize;
        this.cyclists = cyclists;
    }

    public TeamBuilderRow getSponsor() {
        return sponsor;
    }

    public String getTeamType() {
        return teamType;
    }

    public int getRosterSize() {
        return rosterSize;
    }

    public List<CyclistRow> getCyclists() {
        return cyclists;
    }
}