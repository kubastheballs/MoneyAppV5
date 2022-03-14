package com.moneyAppV5.transaction.service;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.category.dto.CategoryDTO;
import com.moneyAppV5.category.dto.MainCategoryDTO;
import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.GainerDTO;
import com.moneyAppV5.transaction.dto.PayeeDTO;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import com.moneyAppV5.transaction.repository.GainerRepository;
import com.moneyAppV5.transaction.repository.PayeeRepository;
import com.moneyAppV5.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService
{
    private TransactionRepository repository;
    private GainerRepository gainerRepository;
    private PayeeRepository payeeRepository;

    TransactionService(TransactionRepository repository, GainerRepository gainerRepository, PayeeRepository payeeRepository)
    {
        this.repository = repository;
        this.gainerRepository = gainerRepository;
        this.payeeRepository = payeeRepository;
    }

    public boolean existsById(int id)
    {
        return this.repository.existsById(id);
    }

    public Transaction createTransaction(final TransactionDTO toSave)
    {
        Payee payee = null;
        boolean isPayee = false;

        for (Payee p : this.payeeRepository.findAll())
        {
            if ((toSave.getPayee().getPayee()).equals(p.getPayee()))
            {
                payee = p;
                isPayee = true;
                break;
            }
        }

        if (!isPayee)
            payee = createPayee(new PayeeDTO(toSave.getPayee().getPayee()));

        Gainer gainer = null;
        boolean isGainer = false;

        for (Gainer g : this.gainerRepository.findAll())
        {
            if ((toSave.getGainer().getGainer()).equals(g.getGainer()))
            {
                gainer = g;
                isGainer = true;
                break;
            }
        }

        if (!isGainer)
            gainer = createGainer(new GainerDTO(toSave.getGainer().getGainer()));

        double amount = Double.parseDouble(toSave.getAmount());

        if ((toSave.getCategory().getType()).equals(Type.EXPENSE))
            amount = -amount;

        var result = new TransactionDTO(toSave.getDate(), toSave.getAccount(), amount, toSave.getCategory(), payee, gainer, toSave.getDescription());

        return this.repository.save(result.toTransaction());
    }

    public Gainer createGainer(final GainerDTO toSave)
    {
        return this.gainerRepository.save(toSave.toGainer());
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

    public List<Transaction> getTransactionsByMonthAndYear(Month month, Year year)
    {
        List<Transaction> transactions = new ArrayList<>();

        for(Transaction t : readAllTransactions())
        if (t.getDate().getYear() == year && t.getDate().getMonth().equals(month))


        return null;
    }
}
