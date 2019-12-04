package io.invoiceapp.invoicegenerator.domain;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

  private Cell getHeaderCell(String text) {
    Cell cell = new Cell();
    cell.add(new Paragraph(text).setTextAlignment(TextAlignment.CENTER));
    return cell;
  }

  public String build(Invoice i) throws FileNotFoundException {

    String filename = UUID.randomUUID().toString() + ".pdf";
    PdfWriter writer = new PdfWriter(filename);
    Document doc = new Document(new PdfDocument(writer), PageSize.A4);

    doc.add(
        new Paragraph("Invoice")
            .setTextAlignment(TextAlignment.RIGHT)
            .setFontSize(34)
            .setFontColor(ColorConstants.LIGHT_GRAY));

    Text issueDate1 =
        new Text("date".toUpperCase()).setFontColor(ColorConstants.GRAY).setBold().setItalic();
    Text issueDate2 = new Text(i.getIssueDate()).setFontColor(ColorConstants.GRAY);
    doc.add(
        new Paragraph()
            .add(issueDate1)
            .add(" ")
            .add(issueDate2)
            .setTextAlignment(TextAlignment.RIGHT)
            .setFixedLeading(0));

    Text invoiceId1 =
        new Text("invoice".toUpperCase()).setFontColor(ColorConstants.GRAY).setBold().setItalic();
    Text invoiceId2 = new Text(i.getInvoiceId()).setFontColor(ColorConstants.GRAY);
    Text orderId1 =
        new Text("order".toUpperCase()).setFontColor(ColorConstants.GRAY).setBold().setItalic();
    Text orderId2 = new Text(i.getInvoiceId()).setFontColor(ColorConstants.GRAY);
    doc.add(
        new Paragraph()
            .add(invoiceId1)
            .add(" #")
            .add(invoiceId2)
            .add(" - ")
            .add(orderId1)
            .add(" #")
            .add(orderId2)
            .setTextAlignment(TextAlignment.RIGHT));

    doc.add(new Paragraph("\n"));

    Table contactLines = new Table(new float[] {1, 1});
    contactLines.setWidth(UnitValue.createPercentValue(100));
    contactLines.addCell(
        new Paragraph().add(new Text(i.getIssuer())).setTextAlignment(TextAlignment.LEFT));
    contactLines.addCell(
        new Paragraph().add(new Text(i.getCustomer())).setTextAlignment(TextAlignment.RIGHT));
    doc.add(contactLines);

    doc.add(new Paragraph("\n"));

    Table productLines = new Table(4);
    productLines.setWidth(UnitValue.createPercentValue(100));

    productLines.addHeaderCell(getHeaderCell("Desc"));
    productLines.addHeaderCell(getHeaderCell("Qty"));
    productLines.addHeaderCell(getHeaderCell("Price"));
    productLines.addHeaderCell(getHeaderCell("Total"));

    List<InvoiceRow> lines = i.getRows();
    lines.forEach(
        aLine -> {
          productLines.addCell(
              new Paragraph()
                  .add(new Text(aLine.getDescription()))
                  .setTextAlignment(TextAlignment.LEFT));
          productLines.addCell(
              new Paragraph()
                  .add(new Text(aLine.getQuantity()))
                  .setTextAlignment(TextAlignment.RIGHT));
          productLines.addCell(
              new Paragraph()
                  .add(new Text(aLine.getPrice()))
                  .setTextAlignment(TextAlignment.RIGHT));
          productLines.addCell(
              new Paragraph()
                  .add(new Text(aLine.getTotal()))
                  .setTextAlignment(TextAlignment.RIGHT));
        });
    doc.add(productLines);

    doc.add(new Paragraph("\n"));

    Table balanceLines = new Table(2);
    balanceLines.setWidth(200);
    balanceLines.setHorizontalAlignment(HorizontalAlignment.RIGHT);

    balanceLines.addCell(
        new Paragraph("subtotal".toUpperCase())
            .setTextAlignment(TextAlignment.RIGHT)
            .setFontColor(ColorConstants.DARK_GRAY));

    balanceLines.addCell(
        new Paragraph()
            .add(new Text(i.getSubTotal()))
            .add(" ")
            .add(i.getCurrency())
            .setTextAlignment(TextAlignment.RIGHT));

    balanceLines.addCell(
        new Paragraph("tax".toUpperCase())
            .setTextAlignment(TextAlignment.RIGHT)
            .setFontColor(ColorConstants.DARK_GRAY));

    balanceLines.addCell(
        new Paragraph()
            .add(new Text(i.getTax()))
            .add(" ")
            .add(i.getCurrency())
            .setTextAlignment(TextAlignment.RIGHT));

    balanceLines.addCell(
        new Paragraph("balance due".toUpperCase())
            .setTextAlignment(TextAlignment.RIGHT)
            .setBold()
            .setFontColor(ColorConstants.DARK_GRAY));

    balanceLines.addCell(
        new Paragraph()
            .add(new Text(i.getBalanceDue()))
            .add(" ")
            .add(i.getCurrency())
            .setTextAlignment(TextAlignment.RIGHT));
    doc.add(balanceLines);

    doc.add(new Paragraph("\n"));

    Text dueDate1 =
        new Text("due date".toUpperCase()).setFontColor(ColorConstants.GRAY).setBold().setItalic();
    Text dueDate2 = new Text(i.getDueDate()).setFontColor(ColorConstants.GRAY);
    doc.add(
        new Paragraph().add(dueDate1).add(" ").add(dueDate2).setTextAlignment(TextAlignment.LEFT));

    Text note1 =
        new Text("note".toUpperCase()).setFontColor(ColorConstants.GRAY).setBold().setItalic();
    Text note2 = new Text(i.getNote().toUpperCase()).setFontColor(ColorConstants.GRAY);
    doc.add(new Paragraph().add(note1).add(" ").add(note2));

    Text status1 =
        new Text("status".toUpperCase()).setFontColor(ColorConstants.GRAY).setBold().setItalic();
    Text status2 = new Text(i.getStatus().toUpperCase()).setFontColor(ColorConstants.GRAY);
    doc.add(new Paragraph().add(status1).add(" ").add(status2).setFixedLeading(0));

    doc.close();

    return filename;
  }
}
