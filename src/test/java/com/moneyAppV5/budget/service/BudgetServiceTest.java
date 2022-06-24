package com.moneyAppV5.budget.service;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.repository.BudgetPositionRepository;
import com.moneyAppV5.budget.repository.BudgetRepository;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.service.TransactionService;
import com.moneyAppV5.utils.UtilService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BudgetServiceTest
{
    @Test
    void readBudgetByMonthAndYearShouldReturnBudgetIfInDataBase()
    {
//        given
        var mockBudgetRepository = mock(BudgetRepository.class);
        var mockPositionRepository = mock(BudgetPositionRepository.class);
        var mockTransactionService = mock(TransactionService.class);
        var mockCategoryService = mock(CategoryService.class);
        var mockUtilService = mock(UtilService.class);
        var mockBudget = mock(Budget.class);
        when(mockBudgetRepository.findByMonthAndYear(anyInt(), anyInt())).thenReturn(Optional.of(mockBudget));
//        system under test
        var toTest = new BudgetService(mockBudgetRepository, mockPositionRepository, mockTransactionService, mockCategoryService,
                mockUtilService);
//        when
        var budget = toTest.readBudgetByMonthAndYear(5,2022);
//        then
//        TODO jak dać mu dane do porównania/wyszukania?
        assertThat(budget.getMonth()).isEqualTo(5);
        assertThat(budget.getYear()).isEqualTo(2022);
    }

    @Test
    public void listToMap()
    {
        //    TODO nazwa itd - za baeldung
    }

}