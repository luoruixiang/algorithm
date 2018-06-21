package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Portfolio Grouping Level
 *
 */
public class GroupLevel {

    private String portfolioGroupCode;
    private List<Investment> investments = new ArrayList<>();
    private Map<String, GroupLevel> childGroups = new HashMap<>();
    private Boolean isLeaf = Boolean.FALSE;

    public String getPortfolioGroupCode() {
        return portfolioGroupCode;
    }

    public void setPortfolioGroupCode(String portfolioGroupCode) {
        this.portfolioGroupCode = portfolioGroupCode;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public Map<String, GroupLevel> getChildGroups() {
        return childGroups;
    }

    public void setChildGroups(Map<String, GroupLevel> childGroups) {
        this.childGroups = childGroups;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }
}
