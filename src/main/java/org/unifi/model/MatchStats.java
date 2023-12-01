package org.unifi.model;

public class MatchStats {

    public Integer timePassed;
    public String homeTeamName;
    public String awayTeamName;
    public String homeTeamLogo;
    public String awayTeamLogo;
    public Integer scoreH;
    public Integer scoreA;
    public Integer ballPossessionH;
    public Integer ballPossessionA;
    public Integer shotsOnTargetH;
    public Integer shotsOnTargetA;
    public Integer foulsH;
    public Integer foulsA;
    public Integer yellowCardsH;
    public Integer yellowCardsA;
    public Integer totalPassesH;
    public Integer totalPassesA;
    public Integer completedPassesH;
    public Integer completedPassesA;

    public MatchStats(String homeTeamName, String awayTeamName, String homeTeamLogo, String awayTeamLogo) {
        // Initialize fields to default values
        this.timePassed = 0;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamLogo = homeTeamLogo;
        this.awayTeamLogo = awayTeamLogo;
        this.scoreH = 0;
        this.scoreA = 0;
        this.ballPossessionH = 50;
        this.ballPossessionA = 50;
        this.shotsOnTargetH = 0;
        this.shotsOnTargetA = 0;
        this.foulsH = 0;
        this.foulsA = 0;
        this.yellowCardsH = 0;
        this.yellowCardsA = 0;
        this.totalPassesH = 0;
        this.totalPassesA = 0;
        this.completedPassesH = 0;
        this.completedPassesA = 0;
    }
    
}
