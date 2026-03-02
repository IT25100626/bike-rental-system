# Class Diagram – Bike Rental User Management

**SE1020 OOP Project – Documentation (Class Diagrams & Final Report)**

---

## 1. UML-style class diagram (text)

```
┌─────────────────────────────┐
│      <<interface>>          │
│      FileStorable           │  ◄── ABSTRACTION
├─────────────────────────────┤
│ + toFileLine(): String      │
└──────────────▲──────────────┘
               │ implements
               │
┌──────────────┴──────────────┐
│   <<abstract>>               │
│   BaseEntity                 │  ◄── INHERITANCE (base for entities)
├─────────────────────────────┤
│ - id: String                 │  ◄── INFORMATION HIDING (private)
├─────────────────────────────┤
│ + getId(): String           │
│ + setId(String): void       │
│ + toFileLine(): String      │  ◄── POLYMORPHISM (abstract, overridden in User)
└──────────────┬──────────────┘
               │ extends
               ▼
┌─────────────────────────────┐
│   User                      │
├─────────────────────────────┤
│ - email: String             │  ◄── ENCAPSULATION + INFORMATION HIDING
│ - fullName: String          │
│ - phone: String             │
│ - role: String              │
├─────────────────────────────┤
│ + getters/setters           │  ◄── ENCAPSULATION (controlled access)
│ + toFileLine(): String      │  ◄── POLYMORPHISM
│ + fromFileLine(String): User│
└─────────────────────────────┘

┌─────────────────────────────┐
│   UserService               │  ◄── FILE HANDLING (read/write users.txt)
├─────────────────────────────┤
│ - usersPath: Path           │
├─────────────────────────────┤
│ + create(User): User        │  ◄── CREATE (file append)
│ + findAll(): List<User>      │  ◄── READ
│ + findById(String): User    │
│ + findByEmail(String): User │
│ + search(String): List<User>│
│ + update(User): boolean     │  ◄── UPDATE (read file, modify, write)
│ + delete(String): boolean  │  ◄── DELETE (read file, remove, write)
│ - writeAll(List): void      │  ◄── private file write
└─────────────────────────────┘

Servlets: RegisterUserServlet, SearchUserServlet, UpdateUserServlet, DeleteUserServlet
          each use UserService for CRUD and forward to JSP (UI).
```

---

## 2. Rubric mapping – OOP concepts (20 marks)

| OOP concept        | Where implemented | Rubric relevance |
|--------------------|--------------------|-------------------|
| **Encapsulation**  | `User`, `BaseEntity`: private fields; public getters/setters only. No direct field access from outside. | Data and state hidden behind methods. |
| **Inheritance**    | `User extends BaseEntity`. Subclass reuses `id` and overrides `toFileLine()`. | IS-A relationship; code reuse. |
| **Polymorphism**   | `BaseEntity.toFileLine()` abstract; `User.toFileLine()` provides implementation. `UserService` uses `user.toFileLine()` without knowing concrete type. | Same operation, different behaviour per type. |
| **Abstraction**    | Interface `FileStorable` with `toFileLine()`. `BaseEntity` implements it. Defines “can be stored in a file” without implementation details. | Contract for file-storable entities. |
| **Information hiding** | All entity fields are `private`. External code uses only `getId()`, `getEmail()`, etc. Internal representation (e.g. delimiter `|||`) not exposed. | Implementation details hidden from callers. |

---

## 3. Rubric mapping – CRUD (30 marks)

| Operation | Servlet              | UserService method | File handling |
|-----------|----------------------|---------------------|---------------|
| **Create**  | RegisterUserServlet  | `create(User)`      | Append one line to `users.txt` |
| **Read**    | SearchUserServlet    | `findAll()`, `search(keyword)` | Read file line-by-line, parse to `User` |
| **Update**  | UpdateUserServlet    | `update(User)`       | Read all, replace one user, write all |
| **Delete**  | DeleteUserServlet    | `delete(id)`         | Read all, remove user, write all |

---

## 4. Rubric mapping – File handling (10 marks)

- **Read**: `Files.lines(usersPath)` to read `data/users.txt`; each line parsed with `User.fromFileLine(line)`.
- **Write**: `BufferedWriter` (from `Files.newBufferedWriter`): append for Create; overwrite for Update/Delete via `writeAll(users)`.

---

## 5. Data flow (for viva)

1. Browser → HTTP request → **Servlet**
2. Servlet → **UserService** (e.g. create, search, update, delete)
3. UserService → **file read/write** on `data/users.txt`; uses **User** (and `FileStorable`/`BaseEntity`) for parse/serialize
4. Servlet → set request attributes → forward to **JSP**
5. JSP → HTML (Bootstrap) → user

You can redraw this diagram in draw.io, PlantUML, or any UML tool for the final report.
