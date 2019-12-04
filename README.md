# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

```
curl -X POST \
  http://localhost:8080/invoice/post \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'cache-control: no-cache' \
  -d '{"invoiceId":"100","orderId":"11","customer":"customer A","issuer":"provider B","currency":"dollar","issueDate":"03/12/2019","dueDate":"01/01/2020","note":"n/a","status":"ready","rows":[{"description":"Prod 1","quantity":"3","price":"30","total":"90"}],"subTotal":"90","tax":"10","balanceDue":"100"}'
```

