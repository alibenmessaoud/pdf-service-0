# Getting Started

```
curl -X POST \
  http://localhost:8080/invoice/post \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'cache-control: no-cache' \
  -d '{"invoiceId":"100","orderId":"11","customer":"customer A","issuer":"provider B","currency":"dollar","issueDate":"03/12/2019","dueDate":"01/01/2020","note":"n/a","status":"ready","rows":[{"description":"Prod 1","quantity":"3","price":"30","total":"90"}],"subTotal":"90","tax":"10","balanceDue":"100"}'
```
```
{
    "path": "e6d11f52-5531-4207-89b0-ba8d4ecf9407.pdf"
}
```