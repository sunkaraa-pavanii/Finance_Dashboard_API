package com.pavani.finance_dashboard.controller;

import com.pavani.finance_dashboard.dto.FinancialRecordRequest;
import com.pavani.finance_dashboard.entity.FinancialRecord;
import com.pavani.finance_dashboard.service.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class FinancialRecordController {

    private final FinancialRecordService recordService;

    //CREATE
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public FinancialRecord create(@RequestBody FinancialRecordRequest request) {
        return recordService.createRecord(request);
    }

    //GET ALL
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','USER')")
    public List<FinancialRecord> getAll() {
        return recordService.getAllRecords();
    }

    //UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FinancialRecord update(@PathVariable Long id,
                                  @RequestBody FinancialRecordRequest request) {
        return recordService.updateRecord(id, request);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        return recordService.deleteRecord(id);
    }

    //FILTER BY TYPE
    @GetMapping("/type/{type}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public List<FinancialRecord> getByType(@PathVariable String type) {
        return recordService.getByType(type);
    }

    //FILTER BY CATEGORY
    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public List<FinancialRecord> getByCategory(@PathVariable String category) {
        return recordService.getByCategory(category);
    }

    //FILTER BY DATE
    @GetMapping("/date/{date}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public List<FinancialRecord> getByDate(@PathVariable String date) {
        return recordService.getByDate(LocalDate.parse(date));
    }
}

