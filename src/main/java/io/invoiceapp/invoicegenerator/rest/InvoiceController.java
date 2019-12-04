package io.invoiceapp.invoicegenerator.rest;

import io.invoiceapp.invoicegenerator.domain.Invoice;
import io.invoiceapp.invoicegenerator.domain.InvoiceRow;
import io.invoiceapp.invoicegenerator.domain.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

  InvoiceService pdfService;

  @Autowired
  public InvoiceController(InvoiceService pdfService) {
    this.pdfService = pdfService;
  }

  @PostMapping("/post")
  @ResponseBody
  public ResponseEntity<InvoiceResponse> post(@RequestBody InvoiceRequest invoiceRequest) {
    try {
      String name = pdfService.build(toInvoice(invoiceRequest));
      InvoiceResponse body = new InvoiceResponse();
      body.setPath(name);
      return ResponseEntity.ok(body);
    } catch (FileNotFoundException e) {
      throw new PdfFactoryException("FileNotFoundException : can not create file.");
    }
  }

  Invoice toInvoice(InvoiceRequest ir) {
    return Invoice.InvoiceBuilder.anInvoice()
        .withInvoiceId(ir.getInvoiceId())
        .withOrderId(ir.getOrderId())
        .withIssueDate(ir.getIssueDate())
        .withDueDate(ir.getDueDate())
        .withCustomer(ir.getCustomer())
        .withIssuer(ir.getIssuer())
        .withNote(ir.getNote())
        .withStatus(ir.getStatus())
        .withSubTotal(ir.getSubTotal())
        .withTax(ir.getTax())
        .withBalanceDue(ir.getBalanceDue())
        .withCurrency(ir.getCurrency())
        .withRows(ir.getRows().stream().map(this::toRow).collect(Collectors.toList()))
        .build();
  }

  InvoiceRow toRow(InvoiceRowRequest irr) {
    return InvoiceRow.InvoiceRawBuilder.anInvoiceRaw()
        .withPrice(irr.getPrice())
        .withItemDescription(irr.getDescription())
        .withQuantity(irr.getQuantity())
        .withTotal(irr.getTotal())
        .build();
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public class PdfFactoryException extends RuntimeException {

    public PdfFactoryException() {
      super();
    }

    public PdfFactoryException(String message, Throwable cause) {
      super(message, cause);
    }

    public PdfFactoryException(String message) {
      super(message);
    }

    public PdfFactoryException(Throwable cause) {
      super(cause);
    }
  }
}
