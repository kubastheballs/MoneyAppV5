package com.moneyAppV5.bill.repository;

import com.moneyAppV5.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlBillRepository extends BillRepository, JpaRepository<Bill, Integer>
{

}
