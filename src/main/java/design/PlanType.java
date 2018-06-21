package design;

public enum PlanType {

    REGISTERED("REGISTERED_PLAN"),
    NON_REGISTERED("NON_REGISTERED_PLAN");

    private String planType;

    PlanType (String planType) {
        this.planType = planType;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }
}
