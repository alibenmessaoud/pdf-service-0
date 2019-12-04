package io.invoiceapp.invoicegenerator.rest;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;

class InvoiceRequestTest {

    @Test
    void should_serialize_request() {

        InvoiceRequest request = new InvoiceRequest();
        request.setInvoiceId("100");
        request.setOrderId("11");
        request.setCurrency("dollar");
        request.setCustomer("customer A");
        request.setIssuer("provider B");
        request.setDueDate("01/01/2020");
        request.setIssueDate("03/12/2019");
        request.setNote("n/a");
        request.setStatus("ready");
        request.setSubTotal("90");
        request.setTax("10");
        request.setBalanceDue("100");
        InvoiceRowRequest e1 = new InvoiceRowRequest();
        e1.setDescription("Prod 1");
        e1.setPrice("30");
        e1.setTotal("90");
        e1.setQuantity("3");
        request.setRows(List.of(e1));

        Gson g = new Gson();
        System.out.println(g.toJson(request));

    }
}