package com.moneyAppV5.budget.dto;

import java.util.List;

public class BudgetPositionsWrapperDTO
{
    private List<BudgetPositionDTO> list;

    public List<BudgetPositionDTO> getList() {
        return list;
    }

    public void setList(List<BudgetPositionDTO> list) {
        this.list = list;
    }
}
