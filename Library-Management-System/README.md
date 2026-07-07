# Library Management System
A console-based library management system built in Kotlin that allows users to manage books, borrow and return them, and persist library records using a CSV file.

## Features

- **Add books** — add new books with an ID, title, description, author, and publish date.
- **Unique IDs** — prevents duplicate book IDs by checking existing records before adding a new book.
- **View a book** — display a specific book by its ID.
- **View all books** — display every book stored in the library.
- **Update book details** — modify a book's title, author, description, and publish date.
- **Delete books** — remove outdated or unwanted books with a confirmation prompt.
- **Borrow books** — record a book's borrow date and expected return date.
- **Return books** — mark borrowed books as returned by clearing the borrow and return dates.
- **Persistent storage** — all library records are saved to a CSV file and automatically loaded when the application starts.
- **Input validation** —validates menu selections, rejects invalid numeric input for book IDs, and prevents duplicate book IDs.

## How it works
Each book is represented by a `Book` data class containing:

- ID
- Title
- Description
- Author
- Publish Date
- Borrow Date
- Return Date

Books are stored in a `MutableList<Book>` and persisted to `library.csv`. Whenever a book is added, updated, borrowed, returned, or deleted, the library is automatically saved. When the application starts, previously saved records are loaded from the CSV file, allowing data to persist between program executions.
Users interact with the application through a menu-driven console interface to manage the library collection and borrowing records.

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle

## Concepts Practiced

- Data Classes
- Mutable Collections
- Functions
- CRUD Operations
- File I/O
- CSV File Handling
- Collection Operations (`find`)
- Conditional Statements
- Loops
- User Input Validation
- Console Application Development

## Example

```text
=== TUMAINI LIBRARY SYSTEM ===
1. Add a book
2. Display a book by ID
3. Display all books
4. Update a book
5. Delete a book
6. Borrow a book
7. Return a book
8. Exit
```

---

This project was built to practice Kotlin file handling, CRUD operations, collection manipulation, and persistent data storage by developing a simple library management system that maintains book records across multiple program sessions.
