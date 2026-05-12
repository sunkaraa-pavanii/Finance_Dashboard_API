package com.pavani.finance_dashboard.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FinancialRecordRequest {

    private Double amount;
    private String type; // INCOME / EXPENSE
    private String category;
    private LocalDate date;
    private String notes;
    private Long userId;
}
