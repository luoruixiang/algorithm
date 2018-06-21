package design;

public enum InvestmentType {

    GIC("GIC"),
    MUTUAL_FUND("MF"),
    SEGREGATED_FUND("SF"),
    STOCK("STOCK");

    private String investmentType;

    InvestmentType (String investmentType) {
        this.investmentType = investmentType;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }
}
