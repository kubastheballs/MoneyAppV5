package com.moneyAppV5.bill.service;

import com.moneyAppV5.bill.Bill;
import com.moneyAppV5.bill.dto.BillDTO;
import com.moneyAppV5.bill.dto.BillWriteModel;
import com.moneyAppV5.bill.repository.BillRepository;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService
{
    private final BillRepository repository;
    private final TransactionService transactionService;

    public BillService(BillRepository repository, TransactionService transactionService)
    {
        this.repository = repository;
        this.transactionService = transactionService;
    }

    public Bill createBill(BillDTO current)
    {
        return this.repository.save(current.toBill());
    }

    public void createBill(BillWriteModel current)
    {
        var bill = this.repository.save(current.toBill());

        this.transactionService.createTransactionsFromBill(current.getTransactions(), bill);
    }

    public List<Integer> readCategoriesIdsByBillId(int billId)
    {
        return this.transactionService.readCategoriesIdsByBillId(billId);
    }
}
