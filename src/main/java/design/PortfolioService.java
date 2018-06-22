package design;

import java.util.List;

public class PortfolioService {

    public static GroupLevel getPortfolioGrouping(List<String> groupOnCodes, List<Investment> investments) throws Exception{
        GroupLevel root = new GroupLevel();
        root.setPortfolioGroupCode("root");
        root.setInvestments(investments);

        if (groupOnCodes == null || investments == null ||
                investments.size() == 0 || groupOnCodes.size() == 0) {
            root.setLeaf(Boolean.TRUE);
            return root;
        }
        GroupLevel parent = root;
        int count = 0;
        for (Investment investment : investments) {
            count = 0;
            for (String groupCode : groupOnCodes) {
                count++;
                String portfolioGroupCode = getPortfolioGroupCodeByGroupOnCode(groupCode, investment);
                if (parent.getChildGroups().containsKey(portfolioGroupCode)) {
                    GroupLevel groupLevel = parent.getChildGroups().get(portfolioGroupCode);
                    groupLevel.getInvestments().add(investment);
                }
                else {
                    GroupLevel groupLevel = new GroupLevel();
                    groupLevel.setPortfolioGroupCode(portfolioGroupCode);
                    groupLevel.getInvestments().add(investment);
                    parent.getChildGroups().put(portfolioGroupCode, groupLevel);
                }
                parent = parent.getChildGroups().get(portfolioGroupCode);
                if (count == groupOnCodes.size()) {
                    parent.setLeaf(true);
                }
            }
            parent = root;
        }
        return root;
    }

    private static String getPortfolioGroupCodeByGroupOnCode(String groupCode, Investment investment) throws Exception{
        switch (groupCode) {
            case "GROUP_BY_CLIENT_ID":
                return investment.getClientId();
            case "GROUP_BY_INVESTMENT_TYPE":
                return investment.getInvestmentType().getInvestmentType();
            case "GROUP_BY_PLAN_TYPE":
                return investment.getPlanType().getPlanType();
            case  "GROUP_BY_RISK_TYPE":
                return investment.getInvestmentRisk().getRiskLevel();
            case  "GROUP_BY_PLAN_CODE":
                return investment.getPlanCode();
            case "GROUP_BY_PLAN":
                return investment.getPlanId();
            default: throw new Exception("groupCode is not supported....");
        }
    }


}
