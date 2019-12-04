package io.invoiceapp.invoicegenerator.rest;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRequest {

  private String invoiceId;
  private String orderId;

  private String customer;
  private String issuer;

  private String currency;

  private String issueDate;
  private String dueDate;

  private String note;
  private String status;

  private List<InvoiceRowRequest> rows = new ArrayList<>();

  private String subTotal;
  private String tax;
  private String balanceDue;

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(String issueDate) {
    this.issueDate = issueDate;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<InvoiceRowRequest> getRows() {
    return rows;
  }

  public void setRows(List<InvoiceRowRequest> rows) {
    this.rows = rows;
  }

  public String getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(String subTotal) {
    this.subTotal = subTotal;
  }

  public String getTax() {
    return tax;
  }

  public void setTax(String tax) {
    this.tax = tax;
  }

  public String getBalanceDue() {
    return balanceDue;
  }

  public void setBalanceDue(String balanceDue) {
    this.balanceDue = balanceDue;
  }
}
