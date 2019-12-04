package io.invoiceapp.invoicegenerator.domain;

public class InvoiceRow {

  private String description;
  private String quantity;
  private String price;
  private String total;

  public static final class InvoiceRawBuilder {
    private String description;
    private String quantity;
    private String price;
    private String total;

    private InvoiceRawBuilder() {}

    public static InvoiceRawBuilder anInvoiceRaw() {
      return new InvoiceRawBuilder();
    }

    public InvoiceRawBuilder withItemDescription(String itemDescription) {
      this.description = itemDescription;
      return this;
    }

    public InvoiceRawBuilder withQuantity(String quantity) {
      this.quantity = quantity;
      return this;
    }

    public InvoiceRawBuilder withPrice(String price) {
      this.price = price;
      return this;
    }

    public InvoiceRawBuilder withTotal(String total) {
      this.total = total;
      return this;
    }

    public InvoiceRow build() {
      InvoiceRow invoiceRow = new InvoiceRow();
      invoiceRow.price = this.price;
      invoiceRow.total = this.total;
      invoiceRow.description = this.description;
      invoiceRow.quantity = this.quantity;
      return invoiceRow;
    }
  }

  public String getDescription() {
    return description;
  }

  public String getQuantity() {
    return quantity;
  }

  public String getPrice() {
    return price;
  }

  public String getTotal() {
    return total;
  }
}
