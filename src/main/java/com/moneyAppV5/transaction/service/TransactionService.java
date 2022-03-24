package com.moneyAppV5.transaction.service;

import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.GainerDTO;
import com.moneyAppV5.transaction.dto.PayeeDTO;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import com.moneyAppV5.transaction.repository.GainerRepository;
import com.moneyAppV5.transaction.repository.PayeeRepository;
import com.moneyAppV5.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService
{
    private TransactionRepository repository;
    private GainerRepository gainerRepository;
    private PayeeRepository payeeRepository;
//    private BudgetService budgetService;

    TransactionService(TransactionRepository repository, GainerRepository gainerRepository, PayeeRepository payeeRepository)
//    TransactionService(TransactionRepository repository, GainerRepository gainerRepository, PayeeRepository payeeRepository, BudgetService budgetService)
    {
        this.repository = repository;
        this.gainerRepository = gainerRepository;
        this.payeeRepository = payeeRepository;
//        this.budgetService = budgetService;
    }

    public boolean existsById(int id)
    {
        return this.repository.existsById(id);
    }

    public Transaction createTransaction(final TransactionDTO toSave)
    {
//        TODO przy tworzeniu transakcji należy sprawdzić czy istnieje budgetPos dla daty i kategorii - jesli tak to dodać a jeśli nie to utworzyć nowy

        Payee isPaid;
//TODO czy w isPaid jest potrzebny optional? wpierw występuje sprawdzenie exists więc jeśli wyjdzie ok to musi być w bazie
//        TODO czy da się to zredukować do jednej linijki?
            if (this.payeeRepository.existsByPayee(toSave.getIsPaid().getName()))
                isPaid = this.payeeRepository.findByName(toSave.getIsPaid().getName()).get();
            else
                isPaid = this.payeeRepository.save(toSave.getIsPaid());

        Payee forWhom;

            if (this.gainerRepository.existsByGainer(toSave.getForWhom().getName()))
//                TODO
                forWhom = this.payeeRepository.findByNameAndRole(toSave.getForWhom().getName(), Role.IS_FOR);
            else
                forWhom = createPayee(new PayeeDTO(toSave.getForWhom().getName(), Role.IS_FOR));

//        double amount = Double.parseDouble(toSave.getAmount());

            if ((toSave.getCategory().getType()).equals(Type.EXPENSE))
                toSave.setAmount(-toSave.getAmount());

        var result = new TransactionDTO(toSave.getDay(), toSave.getMonth(), toSave.getYear(), toSave.getAccount(), toSave.getAmount(), toSave.getCategory(), isPaid, forWhom, toSave.getDescription());

//  TODO tutaj sprawdzenie czy jest budgetPos i jeśli jest to przekazanie do transaction lub utworzenie nowego
//  TODO czy konstruktor typu Transaction(Transaction, BudgetPosition) ma rację bytu?
//        TODO existByData? dla budgetPos
//        np
//        if (existsByData)
//            return id
//            getById()
//        ALTERNATYWNIE
//            getByData()
//        else
//            create()
//        TODO to wywołuje zapętlenie wywłań budgetService -> transactionService
//        Budget b = null;
//
//        if (this.budgetService.existsByMonthAndYear(toSave.getMonth(), toSave.getYear()))
//             b = this.budgetService.readBudgetByMonthAndYear(toSave.getMonth(), toSave.getYear());
//
//        var t = this.repository.save(result.toTransaction());
//        t.setBudget(b);

        return this.repository.save(result.toTransaction());
    }

    public Gainer createGainer(final GainerDTO toSave)
    {
        return this.gainerRepository.save(toSave.toGainer());
    }

    public Payee addPayee(final String name, final Role role)
    {
        return this.payeeRepository.findByName(name).orElse(createPayee(new PayeeDTO(name, role)));
    }

    public Payee createPayee(final PayeeDTO toSave)
    {
        return this.payeeRepository.save(toSave.toPayee());
    }

    public List<Transaction> readAllTransactions()
    {
        return this.repository.findAll();
    }

    public List<Gainer> readAllGainers()
    {
        return this.gainerRepository.findAll();
    }

    public List<Payee> readAllPayees()
    {
        return this.payeeRepository.findAll();
    }

    public Optional<Transaction> readTransactionById(int id)
    {
        return this.repository.findById(id);
    }

    public Optional<Gainer> readGainerById(int id)
    {
        return this.gainerRepository.findById(id);
    }

    public Optional<Payee> readPayeeById(int id)
    {
        return this.payeeRepository.findById(id);
    }

    public List<TransactionDTO> readAllTransactionsDTO()
    {
        List<TransactionDTO> dtos = new ArrayList<>();

        for(Transaction tran : readAllTransactions())
            dtos.add(new TransactionDTO(tran));

        return dtos;
    }

    public List<PayeeDTO> readAllPayeesDto()
    {
        List<PayeeDTO> dtos = new ArrayList<>();

        for(Payee pay : readAllPayees())
            dtos.add(new PayeeDTO(pay));

        return dtos;
    }

    public List<GainerDTO> readAllGainersDto()
    {
        List<GainerDTO> dtos = new ArrayList<>();

        for(Gainer gai : readAllGainers())
            dtos.add(new GainerDTO(gai));

        return dtos;
    }

    public List<Transaction> readTransactionsByPositionId(Integer id)
    {
        return this.repository.findTransactionsByPositionId(id);
    }

    public List<Transaction> readTransactionsByMonthAndYear(int month, int year)
    {
        return this.repository.findTransactionsByMonthAndYear(month,year);
    }

    public List<Transaction> readTransactionsByBudgetId(int id)
    {
        return this.repository.findTransactionsByBudgetId(id);
    }

    public void updateBudgetDataInTransaction(int id, BudgetPosition pos)
    {
        this.repository.updateBudgetDetailsInTransaction(id, pos.getId(), pos.getBudget().getId());
    }

//    public List<Transaction> getTransactionsByMonthAndYear(Month month, Year year)
//    {
//        List<Transaction> transactions = new ArrayList<>();
//
//        for(Transaction t : readAllTransactions())
//        {
//
//        }
////        if (t.getDate().getYear() == year && t.getDate().getMonth().equals(month))
//
//
//     return null;
//    }
}
