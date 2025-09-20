# CRUD Project

This is a standard **Spring Boot** project with minimal CRUD operations connecting to a MSSQL server.

---

## Prerequisites

Make sure you have the following installed:

- **Java 17+**
- **Maven 3.8+**
- **Git**
- **MSSQL Server**
- An IDE (recommended: [IntelliJ IDEA](https://www.jetbrains.com/idea/))

---

## Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/maksud-daffodil/crud-be.git
cd crud-be
```

### 2. Set up the Database

You can either install MSSQL locally or run it via Docker.

#### Option A: Run MSSQL with Docker (recommended)
```bash
docker run -e "ACCEPT_EULA=Y" \
-e "SA_PASSWORD=YourStrong!Passw0rd" \
-p 1433:1433 \
--name mssql \
-d mcr.microsoft.com/mssql/server:2022-latest
```

#### Option B: Install MSSQL locally

- **Linux**: Follow Microsoft’s guide: [Install on Linux](https://learn.microsoft.com/sql/linux/quickstart-install-connect-docker).
- **macOS**: Use Docker (native install is not available).
- **Windows**: Install via [SQL Server Developer Edition](https://www.microsoft.com/en-us/sql-server/sql-server-downloads).

Once running, create a database:
```sql
CREATE DATABASE TestCrud;
GO;
```

### 3. Configure Application Properties

Update `src/main/resources/application.properties` (or `application.yml`) with your DB credentials:

```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=demo_db;encrypt=false
spring.datasource.username=sa
spring.datasource.password=YourStrong!Passw0rd
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
---
## Building and Running
### Linux / macOS
```bash
./mvnw clean spring-boot:run
```
### Windows (PowerShell)
```cmd
mvnw.cmd clean spring-boot:run
```

Or build a JAR and run:
```bash
./mvnw clean package
java -jar target/*.jar
```
---
### Running in IntelliJ IDEA

1. Open IntelliJ → Open Project → select the project root.
2. Make sure IntelliJ uses the correct JDK (17+).
3. Let IntelliJ import Maven dependencies automatically.
4. Add a Run Configuration:
   - Main class: `com.example.demo.DemoApplication` (adjust to your main class). 
   - Program arguments: (VM Options) `-Djava.security.properties=custom.java.security`
5. Run the application.

---
### Verifying the Setup

Once running, visit:
```
http://localhost:8080
```

Check DB connection logs in the console to confirm it’s connected to `TestCrud`.

---
## Useful Commands

- Start/stop MSSQL Docker container:
```bash
docker start mssql
docker stop mssql
```

- Connect to MSSQL CLI (inside container):
```bash
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "YourStrong!Passw0rd"
```
