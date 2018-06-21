package design;

public class Investment {

    private String planId;
    private String planCode;
    private String ClientId;
    private PlanType planType;
    private InvestmentType InvestmentType;
    private InvestmentRisk InvestmentRisk;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public InvestmentType getInvestmentType() {
        return InvestmentType;
    }

    public void setInvestmentType(InvestmentType investmentType) {
        InvestmentType = investmentType;
    }

    public InvestmentRisk getInvestmentRisk() {
        return InvestmentRisk;
    }

    public void setInvestmentRisk(InvestmentRisk investmentRisk) {
        InvestmentRisk = investmentRisk;
    }
}
