package com.accounting.app.service;

import com.accounting.app.dto.TransactionResponse;
import com.ghasemkiani.util.icu.PersianCalendar;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.ZoneId;
import java.util.List;

@Service
public class PDFService {
    private final TransactionService transactionService;
    private final UserService userService;

    public PDFService(TransactionService transactionService, UserService userService){
        this.transactionService = transactionService;
        this.userService = userService;
    }

    public byte[] transactionPdfGenerator(Principal principal, Long accountId) throws Exception,IOException {
        List<TransactionResponse> transactions = transactionService.getTransactionByAccountId(accountId, userService.getUserByEmailEntity(principal.getName()).getId());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String fontPath = getClass().getClassLoader()
                .getResource("fonts/Vazir.ttf")
                .getPath();

        BaseFont baseFont = BaseFont.createFont(
                fontPath,
                BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED
        );
        Font font = new Font(baseFont, 12);
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();
        document.addTitle("Transactions");
        PdfPTable table = new PdfPTable(6);
        table.addCell(new PdfPCell(new Phrase("شناسه", font)));
        table.addCell(new PdfPCell(new Phrase("مبلغ", font)));
        table.addCell(new PdfPCell(new Phrase("نوع", font)));
        table.addCell(new PdfPCell(new Phrase("دسته‌بندی", font)));
        table.addCell(new PdfPCell(new Phrase("توضیحات", font)));
        table.addCell(new PdfPCell(new Phrase("تاریخ", font)));
        int rowNumber = 1;
        for (TransactionResponse t : transactions) {
            table.addCell(t.getId().toString());
            table.addCell(t.getAmount().toString());
            table.addCell(t.getCategoryName());
            table.addCell(t.getDescription());
            table.addCell(t.getTransactionType().toString());
            PersianCalendar pc = new PersianCalendar();
            pc.setTimeInMillis(t.getCreatedAt()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli());

            String shamsiDate = pc.get(PersianCalendar.YEAR) + "/" +
                    pc.get(PersianCalendar.MONTH) + "/" +
                    pc.get(PersianCalendar.DAY_OF_MONTH);
            table.addCell(shamsiDate);
        }
        document.add(table);
        document.close();
        return out.toByteArray();
    }
}
