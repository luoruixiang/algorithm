package algo.design;

import design.*;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main (String[] args) throws Exception {
        List<String> groupOnCodes = new ArrayList<>();
        groupOnCodes.add("GROUP_BY_CLIENT_ID");
        groupOnCodes.add("GROUP_BY_PLAN_TYPE");
        groupOnCodes.add("GROUP_BY_PLAN");
        List<Investment> investments = new ArrayList<>();
        investments.add(new Investment("1", "plan3", "100", "Client1", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("2", "plan3", "100", "Client1", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("3", "plan3", "100", "Client1", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("4", "plan4", "100", "Client1", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("5", "plan4", "100", "Client1", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("6", "plan5", "100", "Client1", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("7", "plan5", "100", "Client1", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("8", "plan6", "100", "Client1", PlanType.NON_REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("16", "plan6", "100", "Client1", PlanType.NON_REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("9", "plan7", "100", "Client1", PlanType.NON_REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("10", "plan8", "100", "Client1", PlanType.NON_REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("11", "plan9", "100", "Client2", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("12", "plan10", "100", "Client2", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("17", "plan10", "100", "Client2", PlanType.REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("13", "plan11", "100", "Client2", PlanType.NON_REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("14", "plan11", "100", "Client2", PlanType.NON_REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));
        investments.add(new Investment("15", "plan12", "100", "Client2", PlanType.NON_REGISTERED, InvestmentType.GIC, InvestmentRisk.LOW));

        GroupLevel groupLevel = PortfolioService.getPortfolioGrouping(groupOnCodes, investments);
        System.out.println();
    }

}
