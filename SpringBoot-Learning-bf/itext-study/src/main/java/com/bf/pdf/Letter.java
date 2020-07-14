package com.bf.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Letter {
    public static void main(String[] args) throws DocumentException, IOException {
        // 生成File所在文件夹
        String filename = "D:/iTextDemo/Letter.pdf";
        File file = new File(filename);
        file.getParentFile().mkdirs();
        // 中文字体
        BaseFont fontChinese = BaseFont.createFont("C:/Windows/Fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        Font normalFont = new Font(fontChinese, 12);

        Paragraph to = new Paragraph("亲爱的李雷：", normalFont);
        document.add(to);

        Paragraph hello = new Paragraph("你好！", normalFont);
        hello.setIndentationLeft(24); // 整体缩进
        document.add(hello);

        Paragraph content = new Paragraph("我现在正在学习iText，正好写一封信给你，为了整点内容凑个换行，我这个不怎么擅长写作文的人也是拼了。", normalFont);
        content.setFirstLineIndent(24); // 首行缩进
        document.add(content);

        Paragraph from = new Paragraph("韩梅梅\n2017年11月29日", normalFont);
        from.setAlignment(Element.ALIGN_RIGHT); // 居右显示
        document.add(from);

        document.close();
    }

}
