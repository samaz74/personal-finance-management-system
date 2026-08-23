package com.accounting.app.controller;

import com.accounting.app.dto.TransactionRequest;
import com.accounting.app.dto.TransactionResponse;
import com.accounting.app.service.ExcelService;
import com.accounting.app.service.PDFService;
import com.accounting.app.service.TransactionService;
import com.accounting.app.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;
    private final ExcelService excelService;
    private final PDFService pDFService;

    public TransactionController(TransactionService transactionService, UserService userService, ExcelService excelService, PDFService pDFService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.excelService = excelService;
        this.pDFService = pDFService;
    }

    @GetMapping("/{id}")
    public TransactionResponse getTransactionWithId(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }
    @GetMapping("/current/{accountId}")
    public List<TransactionResponse> getUserTransactions(Principal principal, @PathVariable Long accountId){
        return transactionService.getTransactionByAccountId(accountId,userService.getUserByEmailEntity(principal.getName()).getId());
    }
    @GetMapping("/category/{categoryId}")
    public List<TransactionResponse> getTransactionByCategoryId(Principal principal, @PathVariable Long categoryId){
        return transactionService.getTransactionByCategoryId(categoryId,userService.getUserByEmailEntity(principal.getName()).getId());
    }
    @PostMapping("/")
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest,Principal principal){
        return transactionService.createTransaction(transactionRequest,userService.getUserByEmailEntity(principal.getName()).getId());
    }

    @GetMapping("/report/transactions/excle/{accountId}")
    public ResponseEntity<byte[]> downloadTransactionReportExcel(
            @PathVariable Long accountId,
            Principal principal) {

        byte[] excelFile = excelService.reportOfUserTransaction(principal, accountId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelFile);
    }

    @GetMapping("/report/transactions/pdf/{accountId}")
    public ResponseEntity<byte[]> downloadTransactionReport(@PathVariable Long accountId, Principal principal) throws Exception {

        byte[] pdfFile = pDFService.transactionPdfGenerator(principal, accountId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.pdf")
                .contentType(MediaType.APPLICATION_PDF).body(pdfFile);

    }


}
