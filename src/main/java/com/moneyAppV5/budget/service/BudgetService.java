package com.moneyAppV5.budget.service;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.budget.repository.BudgetPositionRepository;
import com.moneyAppV5.budget.repository.BudgetRepository;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.Transaction;
import org.apache.catalina.LifecycleState;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
class BudgetService
{
    private BudgetRepository repository;
    private BudgetPositionRepository budgetPositionRepository;

    List<BudgetPosition> budgetPositions;
    List<Transaction> transactions;

    BudgetService(BudgetRepository repository, BudgetPositionRepository budgetPositionRepository)
    {
        this.repository = repository;
        this.budgetPositionRepository = budgetPositionRepository;
    }

    //    TODO czy w medotdzie powinno być categoryId czy category?

//    List<BudgetPosition> getBudgetPositionsByBudget(Budget budget)
//    {
//        this.budgetPositions = this.repository.getBudgetPositionsByBudget(budget);
//
//        return this.budgetPositions;
//    }

    List<BudgetPosition> getBudgetPositionsByTypeFromBudget(Type type)
    {
        List<BudgetPosition> positions = new ArrayList<>();

        for (BudgetPosition p : this.budgetPositions)
            if ((p.getCategory().getType()).equals(type))
                positions.add(p);

        return positions;
    }

//    List<Transaction> getTransactionsByTypeFromBudget(Budget budget, Type type)
//    {
//        this.transactions = this.repository.getTransactionsByTypeAndBudget(budget, type);
//
//        return this.transactions;
//    }

    double sumActualTransactionsByCategoryAndMonth(Category category)
    {
        double sum = 0;

        for (Transaction t : getActualTransactionsByCategoryAndMonth(category))
            sum+=t.getAmount();

        return sum;
    }

    double getActualAmountByCategory(Category category)
    {
        BudgetPosition pos = new BudgetPosition();

        for (BudgetPosition b : this.budgetPositions)
            if ((b.getCategory()).equals(category))
                pos = b;

        return pos.getActualAmount();
    }

    double getPlannedAmountByCategory(Category category)
    {
        BudgetPosition pos = new BudgetPosition();

        for (BudgetPosition b : this.budgetPositions)
            if ((b.getCategory()).equals(category))
                pos = b;

        return pos.getPlannedAmount();
    }

    double balancePlannedAndActualAmountByCategoryByMonth(Category category)
    {
        return getPlannedAmountByCategory(category) - getActualAmountByCategory(category);
    }

    double sumActualAmountsByMonth()
    {
        double sum = 0;

        for (BudgetPosition b : this.budgetPositions)
            sum += b.getActualAmount();

        return sum;
    }

    double sumPlannedAmountsByMonth()
    {
        double sum = 0;

        for (BudgetPosition b : this.budgetPositions)
            sum += b.getPlannedAmount();

        return sum;
    }

    double balanceActualAndPlannedAmountsByMonth()
    {
        return sumPlannedAmountsByMonth() - sumActualAmountsByMonth();
    }

    List<Transaction> getActualTransactionsByCategoryAndMonth(Category category)
    {
        List<Transaction> actualTransactions = new ArrayList<>();

        for (Transaction t : this.transactions)
            if ((t.getCategory()).equals(category))
                actualTransactions.add(t);

        return actualTransactions;
    }

}
