package com.bf.pdf;

    import com.itextpdf.text.*;
    import com.itextpdf.text.pdf.BaseFont;
    import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
// 空行Demo
public class BlackDemo {
    /** Path to the vietnamese font. */
    public static final String FONT = "SIMSUN.ttf";
    public static void main(String args[]) {
        try {
            // create a new document
            Document document = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream("D:/iTextDemo/BlankDemo.pdf"));

            document.open();
            BaseFont font = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font normalFont = new Font(font, 12);
            document.add(new Paragraph("你好, World!", normalFont));
            document.add(new Paragraph("Hello, World!"));

            // add a couple of blank lines
            document.add(Chunk.NEWLINE); // 分割段落好使
            document.add(Chunk.NEWLINE);

            // add one more line with text
            document.add(new Paragraph("Hello, World!"));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
