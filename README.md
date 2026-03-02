# Bike Rental and Ride-Sharing – User Management

Web-based user management for a Bike Rental and Ride-Sharing application, built with **Java, JSP, and Servlets**. Data is stored in text files (no database).

## Features

- **User management (CRUD)**
  - **Create**: Register a new user
  - **Read**: Search users by name, email, or phone
  - **Update**: Edit user details
  - **Delete**: Remove a user (with confirmation)
- **OOP** (Encapsulation, Inheritance, Polymorphism, Abstraction, Information hiding)
  - **Encapsulation**: `User` and `BaseEntity` – private fields, getters/setters only
  - **Inheritance**: `User extends BaseEntity`
  - **Polymorphism**: `toFileLine()` overridden in `User`
  - **Abstraction**: interface `FileStorable`; `BaseEntity` implements it
  - **Information hiding**: no direct field access; internal format (delimiter) hidden
- **Data storage**: File read/write only to `data/users.txt` (no database)

## Tech Stack

- **Backend**: Java 17, Servlets, JSP
- **Frontend**: HTML, Bootstrap 5, JSTL
- **Build**: Maven (WAR)
- **Data**: Plain text file (`data/users.txt`)

## Project Structure

```
src/main/java/com/bikerental/
├── model/
│   ├── FileStorable.java  # Interface (abstraction)
│   ├── BaseEntity.java    # Abstract base, implements FileStorable
│   └── User.java          # User entity
├── service/
│   └── UserService.java   # File-based CRUD
└── servlet/
    ├── RegisterUserServlet.java
    ├── SearchUserServlet.java
    ├── UpdateUserServlet.java
    └── DeleteUserServlet.java

src/main/webapp/
├── index.jsp              # Home
├── WEB-INF/
│   ├── web.xml
│   └── jsp/
│       ├── common/header.jsp
│       ├── register.jsp   # Register user UI
│       ├── search.jsp     # Search/list users UI
│       └── update.jsp     # Update user UI

data/
└── users.txt              # Sample/demo user data
```

## How to Run

### 1. Build

```bash
mvn clean package
```

The WAR will be in `target/bike-rental.war`.

### 2. Deploy

Deploy `target/bike-rental.war` to a Servlet 4.0 container (e.g. **Apache Tomcat 9+**).

- Copy the WAR to `tomcat/webapps/`.
- Start Tomcat. The app will be at `http://localhost:8080/bike-rental/`.

**Data file location**: By default the app uses the directory `data/` under the JVM working directory. When running from IntelliJ/Tomcat, the working directory is often the project root, so `data/users.txt` in the project is used. To override (e.g. on Tomcat), set JVM option: `-Ddata.dir=/absolute/path/to/data`.

### 3. Run from IntelliJ IDEA

1. **Import as Maven project**: File → Open → select the project folder (with `pom.xml`).
2. **Configure Tomcat** (or another server):
   - Run → Edit Configurations → Add New → Tomcat Server → Local.
   - Set Application server to your Tomcat installation.
   - In the Deployment tab, add **Artifact** → `bike-rental:war exploded`, set Application context to `/` or `/bike-rental`.
3. **Run**: Start the Tomcat configuration. Open `http://localhost:8080/` (or the context you set).

## Sample Data

`data/users.txt` contains demo users. Format per line:

```
id|||email|||fullName|||phone|||role
```

Example:

```
usr-001|||alice@example.com|||Alice Johnson|||555-0101|||CUSTOMER
```

## SE1020 Evaluation Rubric Mapping

| Criteria | Marks | Where addressed |
|----------|--------|------------------|
| **CRUD (Create, Read, Update, Delete)** | 30 | RegisterUserServlet (Create), SearchUserServlet (Read), UpdateUserServlet (Update), DeleteUserServlet (Delete); all use `UserService` + file |
| **OOP (Encapsulation, Inheritance, Polymorphism, Abstraction, Information hiding)** | 20 | `User`/`BaseEntity` (encapsulation, inheritance, polymorphism), `FileStorable` (abstraction), private fields (information hiding) – see `docs/CLASS_DIAGRAM.md` |
| **File handling** | 10 | `UserService`: read via `Files.lines()`, write via `BufferedWriter` (append for Create, full rewrite for Update/Delete) |
| **User interface design** | 10 | Home, Register, Search, Update JSPs; Bootstrap 5; shared header and `css/app.css` |
| **Individual contribution & GitHub** | 10 | Use Git; document commits in `docs/FINAL_REPORT_TEMPLATE.md` |
| **Presentation & viva** | 10 | Use `docs/VIVA_NOTES.md` for demo and Q&A prep |
| **Documentation (class diagram & report)** | 10 | `docs/CLASS_DIAGRAM.md`; `docs/FINAL_REPORT_TEMPLATE.md` (fill Git history and submit) |

## Deliverables Checklist

- [x] Source: `.java`, JSP, `web.xml`, `pom.xml`
- [x] Full CRUD: Create, Read, Update, Delete via file handling
- [x] OOP: Encapsulation, inheritance, polymorphism, abstraction, information hiding
- [x] At least 3 UIs: Home, Register, Search/List, Update (Bootstrap, responsive)
- [x] Sample data: `data/users.txt`
- [x] Class diagram: `docs/CLASS_DIAGRAM.md` (rubric-linked)
- [ ] Final report: complete `docs/FINAL_REPORT_TEMPLATE.md` and add Git commit history
- [ ] Viva: use `docs/VIVA_NOTES.md`; present commit history
