package com.moneyAppV5.account.service;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.repository.AccountRepository;
import com.moneyAppV5.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.dsig.TransformService;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class
AccountServiceTest
{
    @Test
    void readAccountByHashShouldReturnAccount()
    {
//        given
        var mockTransactionService = mock(TransactionService.class);
        var mockRepository = mock(AccountRepository.class);
        var mockAccount = mock(Account.class);
        when(mockRepository.findByHash(anyInt())).thenReturn(Optional.of(mockAccount));
//        system under test
        var toTest = new AccountService(mockRepository, mockTransactionService);
//        when
        toTest.readAccountByHash(1);
//        then

    }
//TODO
    @Test
    void readAccountByHashShouldThrowException()
    {
//        given
        var mockTransactionService = mock(TransactionService.class);
        var mockRepository = mock(AccountRepository.class);
        var mockAccount = mock(Account.class);
        when(mockRepository.findByHash(anyInt())).thenReturn(Optional.of(mockAccount));
//        system under test
        var toTest = new AccountService(mockRepository, mockTransactionService);
//        when
        toTest.readAccountByHash(1);
//        then

    }

}