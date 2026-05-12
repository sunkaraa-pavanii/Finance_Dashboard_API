package com.pavani.finance_dashboard.service;

import com.pavani.finance_dashboard.dto.FinancialRecordRequest;
import com.pavani.finance_dashboard.entity.FinancialRecord;
import com.pavani.finance_dashboard.entity.RecordType;
import com.pavani.finance_dashboard.entity.User;
import com.pavani.finance_dashboard.repository.FinancialRecordRepository;
import com.pavani.finance_dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    private final FinancialRecordRepository recordRepository;
    private final UserRepository userRepository;

    //CREATE RECORD
    public FinancialRecord createRecord(FinancialRecordRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        FinancialRecord record = new FinancialRecord();
        record.setAmount(request.getAmount());

        //String → Enum conversion
        record.setType(convertToRecordType(request.getType()));

        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setNotes(request.getNotes());
        record.setUser(user);

        return recordRepository.save(record);
    }

    //GET ALL RECORDS
    public List<FinancialRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    //UPDATE RECORD
    public FinancialRecord updateRecord(Long id, FinancialRecordRequest request) {

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setAmount(request.getAmount());

        //String → Enum conversion
        record.setType(convertToRecordType(request.getType()));

        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setNotes(request.getNotes());

        return recordRepository.save(record);
    }

    //DELETE RECORD
    public String deleteRecord(Long id) {
        recordRepository.deleteById(id);
        return "Record deleted successfully!";
    }

    //FILTER BY TYPE
    public List<FinancialRecord> getByType(String type) {
        return recordRepository.findByType(convertToRecordType(type));
    }

    //FILTER BY CATEGORY
    public List<FinancialRecord> getByCategory(String category) {
        return recordRepository.findByCategory(category);
    }

    //FILTER BY DATE
    public List<FinancialRecord> getByDate(LocalDate date) {
        return recordRepository.findByDate(date);
    }

    //ANALYTICS
    public Double getTotalIncome() {
        Double income = recordRepository.getTotalIncome();
        return income != null ? income : 0.0;
    }

    public Double getTotalExpense() {
        Double expense = recordRepository.getTotalExpense();
        return expense != null ? expense : 0.0;
    }

    private RecordType convertToRecordType(String type) {
        try {
            return RecordType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid type. Use INCOME or EXPENSE");
        }
    }
}




