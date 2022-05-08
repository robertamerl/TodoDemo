# TodoDemo

- H2 in memory DB used to avoid mocking the backend
- GUI not implemented but in Android
- To have some pre loaded todos for test I used H2 in memory database. It is possible to browse the objects from a console, when the application is running:
  http://loalhost:8080/h2-console
  JDBC URL: jdbc:h2:mem:testdb
  user:sa
  password: password
  
- creation of 5 todo objects through script data.sql
- created only one test for each class, to be extended to all methods 
