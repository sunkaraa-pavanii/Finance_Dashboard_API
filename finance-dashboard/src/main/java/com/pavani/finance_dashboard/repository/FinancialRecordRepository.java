package com.pavani.finance_dashboard.repository;

import com.pavani.finance_dashboard.entity.FinancialRecord;
import com.pavani.finance_dashboard.entity.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(RecordType type);
    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByDate(LocalDate date);

    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'INCOME'")
    Double getTotalIncome();

    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'EXPENSE'")
    Double getTotalExpense();
}



