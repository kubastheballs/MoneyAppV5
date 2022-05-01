package com.moneyAppV5.account.service;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class AccountServiceTest
{
    @Test
    void readAccountByHashShouldReturnAccount()
    {
//        given
        var mockRepository = mock(AccountRepository.class);
        var mockAccount = mock(Account.class);
        when(mockRepository.findByHash(anyInt())).thenReturn(mockAccount);
//        when
//        then
    }

}