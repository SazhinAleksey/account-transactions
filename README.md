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
| 1 | Ivan | 0.00 |
| 2 | Petr | 1000.00 |
| 3 | Misha | 100.10 |

### API
| Method Type | URL | Body (Json) | Example |
| ------ | ------ | ------ | ------ |
| GET | /accounts |  | http://localhost:8080/ |
| PUT | /deposit | {"name": "Ivan","amount": 1} | http://localhost:8080/deposit |
| PUT | /withdraw | {"name": "Ivan","amount": 1} | http://localhost:8080/withdraw |
| PUT | /transfer | {"nameFrom": "Petr","nameTo": "Misha","amount": 1} | http://localhost:8080/transfer |
