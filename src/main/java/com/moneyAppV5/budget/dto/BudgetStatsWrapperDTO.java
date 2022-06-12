package com.moneyAppV5.budget.dto;

public class BudgetStatsWrapperDTO
{
    private final BudgetsCountsDTO counts;
    private final BudgetsSumsDTO sums;


    public BudgetStatsWrapperDTO(BudgetsCountsDTO counts, BudgetsSumsDTO sums) {
        this.counts = counts;
        this.sums = sums;
    }

    public BudgetsCountsDTO getCounts() {
        return counts;
    }

    public BudgetsSumsDTO getSums() {
        return sums;
    }
}
