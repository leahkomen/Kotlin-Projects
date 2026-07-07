# JSON Database Engine
A generic, reusable JSON-based database engine built in Kotlin that persists data to a local JSON file. The project demonstrates how generics and Kotlin serialization can be used to create a simple database supporting CRUD operations, filtering, sorting, and persistent storage.

## Features

- **Generic database engine** — stores any serializable Kotlin data type using generics.
- **Persistent storage** — automatically saves data to a local JSON file after every change.
- **Automatic loading** — loads existing data when the application starts.
- **Create records** — add new items with auto-generated IDs.
- **Read records** — view individual records or list all stored data.
- **Update records** — modify existing records.
- **Delete records** — remove records with a confirmation prompt.
- **Filter records** — filter data using custom criteria (course in this demo).
- **Sort records** — sort records by age or name.
- **JSON serialization** — uses `kotlinx.serialization` for reading and writing JSON files.

## How it works
The project consists of a reusable `JsonDatabase<T>` class that accepts any serializable data type through Kotlin generics.

```kotlin
class JsonDatabase<T>(
    private val fileName: String,
    private val serializer: KSerializer<T>
)
```

The database maintains a `MutableList<T>` internally and automatically writes the list to a JSON file whenever data changes. When the application starts, the database loads any previously saved data from disk.
The included demonstration manages student records using the following model:

- ID
- Name
- Gender
- Age
- Course

Each new student receives an automatically generated ID, while users can view, update, delete, filter, and sort records through a menu-driven console interface.

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle
- kotlinx.serialization

## Concepts Practiced

- Generics
- Generic Programming
- JSON Serialization
- JSON Deserialization
- Kotlin Serialization (`kotlinx.serialization`)
- Data Classes
- Higher-Order Functions
- Lambda Expressions
- CRUD Operations
- Collection Operations
- Filtering (`filter`)
- Sorting (`sortedBy`)
- File I/O
- Persistent Storage
- Console Application Development

## Example

```text
=== JSON DATABASE ENGINE ===
1. Add to the database
2. View an individual item
3. Update an item
4. Delete an item
5. View all items
6. Filter items
7. Sort items
8. Exit
```

---

This project demonstrates how Kotlin generics and JSON serialization can be combined to build a reusable database engine for different data models.
