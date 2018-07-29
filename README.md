# account-transactions
Demonstration application of money transfer from one account to another.

To run application with maven use:

```sh
mvn clean package
java -jar .\target\account-transactions-0.0.1-SNAPSHOT.jar
```
or
```sh
mvn spring-boot:run
```

### Table with default users
| id | name | balance |
| ------ | ------ | ------ |
| 1 | Ivan | 0 |
| 2 | Petr | 1000 |
| 3 | Misha | 100.1 |

### API
| Method Type | URL | Params | Example |
| ------ | ------ | ------ | ------ |
| GET | / |  | http://localhost:8080/ |
| GET | /put | name, amount | http://localhost:8080/put?name=Ivan&amount=100.1 |
| GET | /withdraw | name, amount | http://localhost:8080/withdraw?name=Ivan&amount=100 |
| GET | /transfer | nameFrom, nameTo, amount | http://localhost:8080/transfer?nameFrom=Petr&nameTo=Ivan&amount=100 |
