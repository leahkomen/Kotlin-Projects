# Expense Tracker
A console-based expense tracking application built in Kotlin that allows users to record, categorize, and analyze their spending, with data persisted to a CSV file.

## Features

- **Add expenses** — record an expense with category, description, amount, and date.
- **Category selection** — choose from a predefined list of categories (Food, Transport, Shopping, Education, Medical, Entertainment, Rent, Utilities, Emergency, Other), with re-prompting on invalid input.
- **View expenses by category** — display all recorded expenses within a chosen category.
- **Update expenses** — modify an existing expense by ID.
- **Delete expenses** — remove an expense by ID, with a confirmation prompt.
- **Auto-generated IDs** — assigns each new expense a unique ID based on the highest existing ID, even after deletions.
- **Category summaries** — view total spending grouped by category.
- **Total spending calculation** — calculate the total amount spent across all expenses.
- **Spending reports** — generate a summary report showing total expenses, total amount spent, and a breakdown by category.
- **Persistent storage** — expenses are saved to a CSV file and automatically loaded when the application starts.

## How it works
Each expense is represented by an `Expense` data class containing an ID, category, description, amount, and date. Expenses are stored in a `MutableList<Expense>` and persisted to `expensetracker.csv`, with data reloaded automatically each time the application starts.
Category selection is handled through a fixed menu (`getCategoryFromUser()`), ensuring expenses are grouped consistently rather than relying on free-text categories. Reports and summaries use Kotlin's `groupBy` and `sumOf` to calculate totals per category and overall.

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
- File I/O
- CSV File Handling
- CRUD Operations
- Collection Operations (`filter`, `groupBy`, `sumOf`, `forEach`)
- Conditional Statements
- Loops
- User Input Validation
- Console Application Development
