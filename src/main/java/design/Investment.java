package design;

public class Investment {

    private String investmentId;
    private String planId;
    private String planCode;
    private String clientId;
    private PlanType planType;
    private InvestmentType investmentType;
    private InvestmentRisk investmentRisk;

    public Investment(String investmentId,
                      String planId,
                      String planCode,
                      String clientId,
                      PlanType planType,
                      InvestmentType investmentType,
                      InvestmentRisk investmentRisk) {
        this.investmentId = investmentId;
        this.planId = planId;
        this.planCode = planCode;
        this.clientId = clientId;
        this.planType = planType;
        this.investmentType = investmentType;
        this.investmentRisk = investmentRisk;
    }

    public String getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(String investmentId) {
        this.investmentId = investmentId;
    }

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
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }

    public InvestmentRisk getInvestmentRisk() {
        return investmentRisk;
    }

    public void setInvestmentRisk(InvestmentRisk investmentRisk) {
        this.investmentRisk = investmentRisk;
    }
}
