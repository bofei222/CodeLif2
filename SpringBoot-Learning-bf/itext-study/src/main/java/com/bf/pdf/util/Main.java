package com.bf.pdf.util;


import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> tableHeader = new ArrayList<>();
        List<String> tableBody = new ArrayList<>();

        // 获取pdf注解的属性
        Field[] declaredFields = User.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            MyPDF myPDFAnnotation = declaredField.getDeclaredAnnotation(MyPDF.class);

            if (null != myPDFAnnotation) {

                tableHeader.add(myPDFAnnotation.value());
                tableBody.add(declaredField.getName());
            }
        }

        System.out.println(tableHeader);
        System.out.println(tableBody);

        List<User> users = new ArrayList() {{
            add(new User(1, "啊啊", "a", "北京"));
            add(new User(2, "哔哔", "b", "北京"));
            add(new User(3, "此此", "cc", "北京"));
        }};


        // 中文字体
        BaseFont bfChinese = BaseFont.createFont("C:/Windows/Fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12);

        // 生成File所在文件夹
        String filename = "D:/iTextDemo/Table.pdf";
        File file = new File(filename);
        file.getParentFile().mkdirs();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();

        PdfPTable title = new PdfPTable(tableHeader.size());
        for (String s : tableHeader) {
            title.addCell(new Paragraph(s, fontChinese));
        }
        document.add(title);

        PdfPTable tr = null;
        for (User user : users) {
            tr = new PdfPTable(tableHeader.size());
            for (String field : tableBody) {
                Field declaredField = User.class.getDeclaredField(field);
                declaredField.setAccessible(true);
                Object o = declaredField.get(user);
                tr.addCell(new Paragraph(null != o ? o.toString() : "", fontChinese));
            }
            document.add(tr);
        }



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

    public static void test() throws Exception {
        // 中文字体
        BaseFont bfChinese = BaseFont.createFont("C:/Windows/Fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12);

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
