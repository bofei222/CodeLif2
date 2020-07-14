package com.bf.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfTable {
    public static void main(String[] args) throws IOException, DocumentException {


        // 中文字体
        BaseFont bfChinese  = BaseFont.createFont("C:/Windows/Fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontChinese = new Font(bfChinese , 12);

        // 生成File所在文件夹
        String filename = "D:/iTextDemo/Table.pdf";
        File file = new File(filename);
        file.getParentFile().mkdirs();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        PdfPTable title = new PdfPTable(3);
        title.addCell(new Paragraph("编号", fontChinese));
        title.addCell(new Paragraph("名称", fontChinese));
        title.addCell(new Paragraph("种类", fontChinese));
        document.add(title);

        PdfPTable tr1 = new PdfPTable(3);
        tr1.addCell("1");
        tr1.addCell("a");
        tr1.addCell("b");
        document.add(tr1);

//        document.add(Chunk.NEWLINE);  在table中不好时
//        document.add(Chunk.NEWLINE);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        PdfPTable title2 = new PdfPTable(2);
        title2.addCell(new Paragraph("编号", fontChinese));
        title2.addCell(new Paragraph("名称", fontChinese));
        document.add(title2);

        document.close();
    }
}
