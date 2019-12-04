package io.invoiceapp.invoicegenerator.domain;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

  private String invoiceId;

  private String orderId;

  private String customer;
  private String issuer;

  private String currency;

  private String issueDate;
  private String dueDate;

  private String note;
  private String status;

  private List<InvoiceRow> rows = new ArrayList<>();

  private String subTotal;
  private String tax;
  private String balanceDue;

  public String getInvoiceId() {
    return invoiceId;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getCustomer() {
    return customer;
  }

  public String getIssuer() {
    return issuer;
  }

  public String getCurrency() {
    return currency;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public String getDueDate() {
    return dueDate;
  }

  public String getNote() {
    return note;
  }

  public String getStatus() {
    return status;
  }

  public List<InvoiceRow> getRows() {
    return rows;
  }

  public String getSubTotal() {
    return subTotal;
  }

  public String getTax() {
    return tax;
  }

  public String getBalanceDue() {
    return balanceDue;
  }

  public static final class InvoiceBuilder {
    private String invoiceId;
    private String orderId;
    private String customer;
    private String issuer;
    private String currency;
    private String issueDate;
    private String dueDate;
    private String note;
    private String status;
    private List<InvoiceRow> rows = new ArrayList<>();
    private String subTotal;
    private String tax;
    private String balanceDue;

    private InvoiceBuilder() {}

    public static InvoiceBuilder anInvoice() {
      return new InvoiceBuilder();
    }

    public InvoiceBuilder withInvoiceId(String invoiceId) {
      this.invoiceId = invoiceId;
      return this;
    }

    public InvoiceBuilder withOrderId(String orderId) {
      this.orderId = orderId;
      return this;
    }

    public InvoiceBuilder withCustomer(String customer) {
      this.customer = customer;
      return this;
    }

    public InvoiceBuilder withIssuer(String issuer) {
      this.issuer = issuer;
      return this;
    }

    public InvoiceBuilder withCurrency(String currency) {
      this.currency = currency;
      return this;
    }

    public InvoiceBuilder withIssueDate(String issueDate) {
      this.issueDate = issueDate;
      return this;
    }

    public InvoiceBuilder withDueDate(String dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public InvoiceBuilder withNote(String note) {
      this.note = note;
      return this;
    }

    public InvoiceBuilder withStatus(String status) {
      this.status = status;
      return this;
    }

    public InvoiceBuilder withRows(List<InvoiceRow> rows) {
      this.rows = rows;
      return this;
    }

    public InvoiceBuilder withSubTotal(String subTotal) {
      this.subTotal = subTotal;
      return this;
    }

    public InvoiceBuilder withTax(String tax) {
      this.tax = tax;
      return this;
    }

    public InvoiceBuilder withBalanceDue(String balanceDue) {
      this.balanceDue = balanceDue;
      return this;
    }

    public Invoice build() {
      Invoice invoice = new Invoice();
      invoice.currency = this.currency;
      invoice.status = this.status;
      invoice.note = this.note;
      invoice.orderId = this.orderId;
      invoice.dueDate = this.dueDate;
      invoice.tax = this.tax;
      invoice.issuer = this.issuer;
      invoice.invoiceId = this.invoiceId;
      invoice.subTotal = this.subTotal;
      invoice.balanceDue = this.balanceDue;
      invoice.customer = this.customer;
      invoice.rows = this.rows;
      invoice.issueDate = this.issueDate;
      return invoice;
    }
  }
}
