# SE1020 – Object Oriented Programming Project  
# Final Report: Bike Rental and Ride-Sharing (User Management)

**Student ID:** _________________________  
**Project:** Bike Rental and Ride-Sharing – User Management Module  
**Date:** _________________________

---

## 1. Introduction

This report documents the **User Management** module of the Bike Rental and Ride-Sharing web application. The module allows **Create** (register), **Read** (search), **Update**, and **Delete** operations on users, with data stored using **file read/write** (no database). The backend is implemented with **JSP and Servlets**; the frontend uses **HTML, JSP, and Bootstrap** for a responsive UI.

---

## 2. Assessment Criteria Addressed

### 2.1 Functionality of CRUD Operations (30 marks)

| Operation | Description | Implementation |
|-----------|-------------|----------------|
| **Create** | Register a new user | `RegisterUserServlet` → `UserService.create()` → append line to `data/users.txt` |
| **Read** | Search/list users | `SearchUserServlet` → `UserService.search()` / `findAll()` → read file, parse lines to `User` |
| **Update** | Edit user details | `UpdateUserServlet` → `UserService.update()` → read all, replace one, write all |
| **Delete** | Remove a user | `DeleteUserServlet` → `UserService.delete()` → read all, remove one, write all |

All four CRUD operations are implemented and use file handling only.

### 2.2 OOP Concepts (20 marks)

- **Encapsulation:** `User` and `BaseEntity` have private fields; access only through getters/setters.
- **Inheritance:** `User extends BaseEntity`; shared `id` and contract for `toFileLine()`.
- **Polymorphism:** `toFileLine()` is abstract in `BaseEntity` and overridden in `User`; same method name, type-specific behaviour.
- **Abstraction:** Interface `FileStorable` defines the contract for file-serializable entities; `BaseEntity` implements it.
- **Information hiding:** No public fields; internal representation (e.g. delimiter) is not exposed.

See **Class diagram** in `docs/CLASS_DIAGRAM.md`.

### 2.3 File Handling (10 marks)

- **Read:** `Files.lines(usersPath)` to read `data/users.txt`; each line parsed with `User.fromFileLine(line)`.
- **Write:** `BufferedWriter` (from `Files.newBufferedWriter`): append for Create; full file rewrite for Update and Delete in `writeAll(users)`.

No database is used; all persistence is file-based.

### 2.4 User Interface Design (10 marks)

- **Home (index.jsp):** Landing page with navigation and short description.
- **Register (register.jsp):** Form for email, full name, phone, role; validation and success/error messages.
- **Search (search.jsp):** Search box (keyword) and table of users with Update/Delete actions.
- **Update (update.jsp):** Pre-filled form to edit a user; cancel returns to search.

Bootstrap 5 is used for layout, responsiveness, and consistent styling.

### 2.5 Individual Contribution & GitHub Commit History (10 marks)

**Insert your Git repository commit history below.**  
Example format (replace with your actual history):

```
git log --oneline

abc1234 Add DeleteUserServlet and delete flow
def5678 Add UpdateUserServlet and update.jsp
ghi9012 Add SearchUserServlet and search.jsp
jkl3456 Add RegisterUserServlet and register.jsp
mno7890 Add UserService with file CRUD
pqr1234 Add User and BaseEntity models
stu5678 Initial Maven and web.xml setup
```

**Evidence of individual work:** [Describe your commits: e.g. “I implemented the User model and UserService file handling in commits X and Y; another member did the Servlets.”]

### 2.6 Documentation (10 marks)

- **Class diagrams:** See `docs/CLASS_DIAGRAM.md` (and any exported diagram from draw.io/PlantUML).
- **Final report:** This document; include the completed Git commit history and rubric mapping above.

---

## 3. Conclusion

The User Management module fulfils the project requirements: full CRUD via JSP/Servlets, OOP concepts (encapsulation, inheritance, polymorphism, abstraction, information hiding), file-based storage only, and a clear UI. The implementation is suitable for demonstration in the viva and for inclusion in the project repository.

---

**Git repository:** [URL of your GitHub repo]  
**Sample data:** `data/users.txt` (see README for format and usage).
