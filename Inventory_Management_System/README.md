# Inventory Management System
A console-based inventory management system built in Kotlin that lets you add, update, search, delete, and persist three types of items — Books, Electronics, and Clothing — using CSV file storage.

## Features

- **Add items** — add Books, Electronics, or Clothing, each with type-specific fields (e.g., author/publisher for Books, brand/warranty for Electronics, size/category for Clothing).
- **Display items** — view all items currently in the inventory.
- **Update items** — update an existing item's name, quantity, price, and type-specific fields by ID.
- **Delete items** — remove an item by ID, with a confirmation prompt before deletion.
- **Search items** — search by ID or by name.
- **Persistent storage** — inventory is automatically saved to and loaded from a CSV file (`Inventory.csv`), so data survives between runs.
- **Input validation** — validates numeric input (quantity, price, ID) and invalid menu choices, preventing crashes from bad input.
- **Automatic ID generation** — new items are automatically assigned the next available ID.

## How it works
The application defines a common `InventoryItem` interface implemented by three classes — `Book`, `Electronics`, and `Clothing` — each with its own additional fields alongside the shared `id`, `name`, `quantity`, and `price`.
All items are stored in a single `MutableList<InventoryItem>`. Users interact through a menu-driven interface to add, display, update, delete, or search items. After every change (add, update, delete), the inventory is saved to `Inventory.csv`, and on startup the application loads any existing data from that file so nothing is lost between sessions.
Deleting an item requires confirmation — the user is asked "Are you sure you want to delete this item?" and the item is only removed if they respond "Yes".

## Run it

```bash
kotlinc InventoryManagementSystem.kt -include-runtime -d inventory.jar
java -jar inventory.jar
```

## Requirements
- Kotlin
- JVM

## Concepts Practiced

- Interfaces and Polymorphism
- Classes and Objects
- Mutable Collections
- Conditional Statements and Loops
- File I/O (CSV read/write)
- Null Safety
- User Input Validation
- Console Application Development
