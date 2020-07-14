package com.bf.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChineseDisplay {
    public static void main(String[] args) throws DocumentException, IOException {
        // 生成File所在文件夹
        String filename = "D:/iTextDemo/ChineseDisplay.pdf";
        File file = new File(filename);
        file.getParentFile().mkdirs();

        // 中文字体
        BaseFont fontChinese = BaseFont.createFont("C:/Windows/Fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        document.add(new Paragraph("你好，世界！", new Font(fontChinese, 12)));
        document.close();
    }
}
