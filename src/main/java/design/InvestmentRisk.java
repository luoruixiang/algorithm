package design;

public enum InvestmentRisk {

    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private String riskLevel;

    InvestmentRisk(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}
