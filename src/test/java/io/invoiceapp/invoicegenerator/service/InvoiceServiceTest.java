package io.invoiceapp.invoicegenerator.service;

import io.invoiceapp.invoicegenerator.domain.Invoice;
import io.invoiceapp.invoicegenerator.domain.InvoiceRow;
import io.invoiceapp.invoicegenerator.domain.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InvoiceServiceTest {

  @Mock InvoiceService invoiceService;

  @BeforeEach
  void setUp() {
    invoiceService = new InvoiceService();
  }

  @Test
  void should_generate() throws FileNotFoundException {

    InvoiceRow invoiceRow1 =
        InvoiceRow.InvoiceRawBuilder.anInvoiceRaw()
            .withItemDescription("product 1 product 1 product 1 product 1")
            .withPrice("1")
            .withQuantity("1")
            .withTotal("1")
            .build();

    InvoiceRow invoiceRow2 =
        InvoiceRow.InvoiceRawBuilder.anInvoiceRaw()
            .withItemDescription("product 2 product 2 product 2 product 2")
            .withPrice("2")
            .withQuantity("2")
            .withTotal("4")
            .build();

    InvoiceRow invoiceRow3 =
        InvoiceRow.InvoiceRawBuilder.anInvoiceRaw()
            .withItemDescription("product 3 product 3 product 3 product 3")
            .withPrice("3")
            .withQuantity("3")
            .withTotal("9")
            .build();

    Invoice invoice =
        Invoice.InvoiceBuilder.anInvoice()
            .withInvoiceId("1")
            .withOrderId("1")
            .withIssueDate("02/12/2019")
            .withDueDate("31/01/2020")
            .withCustomer("Customer x")
            .withIssuer("Provider X")
            .withNote("You should pay it, a penalty will be applied after delay")
            .withStatus("ready to pay")
            .withSubTotal("14")
            .withTax("1")
            .withBalanceDue("15")
            .withCurrency("dollar")
            .withRows(List.of(invoiceRow1, invoiceRow2, invoiceRow3))
            .build();

    String built = invoiceService.build(invoice);
    assertThat(built).isNotEmpty();
  }

  @Test()
  public void whenConfigNonVoidRetunMethodToThrowEx_thenExIsThrown() throws FileNotFoundException {
    invoiceService = mock(InvoiceService.class);
    when(invoiceService.build(any())).thenThrow(FileNotFoundException.class);
    assertThrows(
        FileNotFoundException.class,
        () -> invoiceService.build(Invoice.InvoiceBuilder.anInvoice().build()));
  }
}
