package com.pavani.finance_dashboard.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
private class FinancialRecordRequest {

    public Double amount;
    public String type; // INCOME / EXPENSE
    public String category;
    public LocalDate date;
    public String notes;
    public Long userId;
}
