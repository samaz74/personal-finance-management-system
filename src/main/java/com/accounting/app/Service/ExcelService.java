package com.accounting.app.service;

import com.accounting.app.dto.TransactionResponse;
import com.ghasemkiani.util.icu.PersianCalendar;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.ZoneId;
import java.util.List;

@Service
public class ExcelService {
    private final TransactionService transactionService;
    private final UserService userService;

    public ExcelService(TransactionService transactionService, UserService userService){
        this.transactionService = transactionService;
        this.userService = userService;
    }
    public byte[] reportOfUserTransaction(Principal principal, Long accountId){
        List<TransactionResponse> transactions = transactionService.getTransactionByAccountId(accountId,userService.getUserByEmailEntity(principal.getName()).getId());
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("transactions");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("شناسه");
        header.createCell(1).setCellValue("مبلغ");
        header.createCell(2).setCellValue("نوع");
        header.createCell(3).setCellValue("دسته‌بندی");
        header.createCell(4).setCellValue("توضیحات");
        header.createCell(5).setCellValue("تاریخ");
        int rowNumber = 1;
        for(TransactionResponse t : transactions){
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(t.getId());
            row.createCell(1).setCellValue( t.getAmount().doubleValue());
            row.createCell(2).setCellValue(t.getCategoryName());
            row.createCell(3).setCellValue(t.getDescription());
            row.createCell(4).setCellValue(t.getTransactionType().ordinal());
            PersianCalendar pc = new PersianCalendar();
            pc.setTimeInMillis(t.getCreatedAt()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli());

            String shamsiDate = pc.get(PersianCalendar.YEAR) + "/" +
                    pc.get(PersianCalendar.MONTH) + "/" +
                    pc.get(PersianCalendar.DAY_OF_MONTH);
            row.createCell(5).setCellValue(shamsiDate);
        }
        try{
            ByteArrayOutputStream out =new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();
            return out.toByteArray();

        }catch (Exception e){
            new IOException("مشکل در ایجاد فایل اکسل");
            e.printStackTrace();
            return null;
        }

    }
}
