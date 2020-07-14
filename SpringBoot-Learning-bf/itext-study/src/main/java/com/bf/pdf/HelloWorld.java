package com.bf.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class HelloWorld {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        // 生成File所在文件夹
        String filename = "D:/iTextDemo/HelloWorld.pdf";
        File file = new File(filename);
        file.getParentFile().mkdirs();

        // 生成PDF文档
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        document.add(new Paragraph("Hello World!"));
        document.close();

    }
}
