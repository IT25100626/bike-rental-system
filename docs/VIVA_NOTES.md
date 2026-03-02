# Viva Presentation Notes – Bike Rental User Management

**SE1020 – Object Oriented Programming Project**

Use these points to explain your contribution and demonstrate functionality (Presentation & Viva – 10 marks).

---

## 1. What to demonstrate

1. **Home page** – Open the app; show navigation to Register and Search.
2. **Create (Register)** – Register a new user; show success message and that the user appears in Search.
3. **Read (Search)** – Show the list; use the search box (e.g. by name or email); show results.
4. **Update** – Click Update on a user; change name/phone/role; save; show updated row in Search.
5. **Delete** – Click Delete; confirm; show that the user is removed and the list updates.
6. **File storage** – Open `data/users.txt` and show that data is stored as text (one line per user, `|||` delimiter). Explain: no database, only file read/write.

---

## 2. Backend (Java) – likely questions

### CRUD and file handling

- **Where is the file?**  
  `data/users.txt`; path is from `UserService` (either `data.dir` system property or project-relative `data/`).
- **How does Create work?**  
  `UserService.create()` appends one line (user’s `toFileLine()`) to the file.
- **How does Read work?**  
  `UserService.findAll()` / `search()` use `Files.lines()` to read the file, then `User.fromFileLine(line)` to parse each line.
- **How does Update/Delete work?**  
  Read all users from file, modify the list (replace one user or remove one), then `writeAll(users)` overwrites the whole file.

### OOP

- **Encapsulation:**  
  `User` and `BaseEntity` have private fields; other classes use only getters/setters (e.g. `getEmail()`, `setRole()`).
- **Inheritance:**  
  `User extends BaseEntity`; `User` has `id` from `BaseEntity` plus its own fields (email, fullName, phone, role).
- **Polymorphism:**  
  `BaseEntity` declares abstract `toFileLine()`; `User` overrides it. When the service calls `user.toFileLine()`, the actual implementation used is `User`’s.
- **Abstraction:**  
  `FileStorable` is an interface with `toFileLine()`. `BaseEntity` implements it. So any entity that extends `BaseEntity` can be treated as “something that can be written to a file.”
- **Information hiding:**  
  Internal details (e.g. how the line is built, the delimiter `|||`) are inside the class; callers only use `toFileLine()` and getters/setters.

### Servlets

- **Register:**  
  GET shows the form; POST reads parameters, builds a `User`, calls `UserService.create()`, then redirects or shows an error.
- **Search:**  
  GET reads optional `keyword`, calls `UserService.search(keyword)`, puts the list in request, forwards to `search.jsp`.
- **Update:**  
  GET loads user by `id` and shows the form; POST reads parameters, updates the `User`, calls `UserService.update()`, redirects to search.
- **Delete:**  
  POST receives `id`, calls `UserService.delete(id)`, redirects to search.

---

## 3. Sample data for demo

Ensure `data/users.txt` exists and has a few lines, e.g.:

```
usr-001|||alice@example.com|||Alice Johnson|||555-0101|||CUSTOMER
usr-002|||bob@example.com|||Bob Smith|||555-0102|||RIDER
```

Format: `id|||email|||fullName|||phone|||role`

---

## 4. Git commit history

Have your repository open. Run:

```bash
git log --oneline
```

Explain who did what (e.g. “I did the model and file service; my teammate did the Servlets and JSPs”). The final report should include this history and a short “evidence of individual work” paragraph.

---

## 5. Checklist before viva

- [ ] App runs (Tomcat/IDE); all four CRUD operations work.
- [ ] `data/users.txt` is present and updated when you register/update/delete.
- [ ] You can explain each OOP concept with a concrete place in the code (class/method).
- [ ] You can explain file read vs write (which methods use `Files.lines`, which use `BufferedWriter`/`writeAll`).
- [ ] Git repo is pushed; commit history is visible and mentioned in the final report.
- [ ] Class diagram (e.g. from `docs/CLASS_DIAGRAM.md`) is ready to show or attach to the report.
