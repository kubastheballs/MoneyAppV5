package com.moneyAppV5.bill.repository;

import com.moneyAppV5.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlBillRepository extends BillRepository, JpaRepository<Bill, Integer>
{
}
