package design;

public enum GroupOnCode {

    GROUP_BY_PLAN("GROUP_BY_PLAN"),
    GROUP_BY_PLAN_CODE("GROUP_BY_PLAN_CODE"),
    GROUP_BY_PLAN_TYPE("GROUP_BY_RISK_TYPE"),
    GROUP_BY_RISK_TYPE("GROUP_BY_PLAN_TYPE"),
    GROUP_BY_INVESTMENT_TYPE("GROUP_BY_INVESTMENT_TYPE"),
    GROUP_BY_CLIENT_ID("GROUP_BY_CLIENT_ID");

    private String groupOnCode;

    GroupOnCode(String groupOnCode) {
        this.groupOnCode = groupOnCode;
    }

    public String getGroupOnCode() {
        return groupOnCode;
    }

    public void setGroupOnCode(String groupOnCode) {
        this.groupOnCode = groupOnCode;
    }
}
