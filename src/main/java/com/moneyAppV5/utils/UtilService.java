package com.moneyAppV5.utils;

import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UtilService
{
    public int getActualMonthValue()
    {
        return LocalDate.now().getMonthValue();
    }

    public int getActualYear()
    {
        return LocalDate.now().getYear();
    }

    public int[] checkMonthValue(int month, int year) {
        switch (month) {
            case 0 -> {
                month = 12;
                year = year - 1;
            }
            case -1 -> {
                month = 11;
                year = year - 1;
            }
        }

        return new int[]{month, year};
    }

    public double sumByListAndTypeAndMonth(List<TransactionDTO> transactions, Type type, int month, int year)
    {
        var date = checkMonthValue(month,year);

        double sum = 0;

        for (TransactionDTO t : transactions)
            if ((t.getCategory().getType()).equals(type) && t.getMonth() == date[0] && t.getYear() == date[1])
                sum += t.getAmount();

        return sum;
    }

    public double sumByListAndTypeAndYear(List<TransactionDTO> transactions, Type type, int year)
    {
        double sum = 0;

        for (TransactionDTO t : transactions)
            if ((t.getCategory().getType()).equals(type) && t.getYear() == year)
                sum += t.getAmount();

        return sum;
    }

    public double sumByListAndType(List<TransactionDTO> transactions, Type type)
    {
        double sum = 0;

        for (TransactionDTO t : transactions)
            if ((t.getCategory().getType()).equals(type))
                sum += t.getAmount();

        return sum;
    }

    public double sumByListAndMonth(List<TransactionDTO> transactions, int month, int year)
    {
        var date = checkMonthValue(month, year);

        double sum = 0;

        for (TransactionDTO t : transactions)
            if (t.getMonth() == date[0] && t.getYear() == date[1])
                sum += t.getAmount();

        return sum;
    }

    public double sumByListAndYear(List<TransactionDTO> transactions, int year)
    {
        double sum = 0;

        for (TransactionDTO t : transactions)
            if (t.getYear() == year)
                sum += t.getAmount();

        return sum;
    }

    public double sumByList(List<TransactionDTO> transactions)
    {
        double sum = 0;

        for (TransactionDTO t : transactions)
                sum += t.getAmount();

        return sum;
    }

    public int countByListAndMonth(List<TransactionDTO> transactions, int month, int year)
    {
        var date = checkMonthValue(month, year);

        int count = 0;

        for (TransactionDTO t : transactions)
            if (t.getMonth() == date[0] && t.getYear() == date[1])
                count++;

        return count;
    }

    public int countByListAndYear(List<TransactionDTO> transactions, int year)
    {
        int count = 0;

        for (TransactionDTO t : transactions)
            if (t.getYear() == year)
                count++;

        return count;
    }

    public int countByList(List<TransactionDTO> transactions)
    {
        return transactions.size();
    }
}
