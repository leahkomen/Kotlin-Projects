# Employee Payroll System
A console-based employee payroll management system built in Kotlin that allows companies to manage employee records, calculate salaries, and store data persistently using CSV files.

## Features

- **Employee registration** — add full-time, part-time, and contract employees.
- **Multiple employee types** — supports different salary calculation methods through inheritance and polymorphism.
- **Auto-generated IDs** — assigns each new employee a unique ID based on the highest existing ID, even after deletions.
- **View employee details** — search employees by their ID.
- **Update employee information** — modify employee details.
- **Delete employee records** — remove employees with a confirmation prompt.
- **Salary calculation** — calculate salaries based on employee type.
- **Persistent storage** — employee records are saved to a CSV file and automatically loaded when the application starts.

## How it works

The application models employees using an abstract `Employee` class with three subclasses:

- `FullTimeEmployee`
- `PartTimeEmployee`
- `ContractEmployee`

Each employee type overrides the `calculateSalary()` function to implement its own salary calculation logic.

Employee records are stored in a `MutableList<Employee>`. Whenever records are added, updated, or deleted, the list is written to `employee.csv`. When the application starts, previously saved employee records are loaded automatically from the CSV file.

## Salary Calculation

Salary is calculated differently depending on the employee type:

```text
Full-Time Employee:
Salary = Basic Salary

Part-Time Employee:
Salary = Hours Worked × Hourly Rate

Contract Employee:
Salary = Contract Amount × Number of Projects
```

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle

## Concepts Practiced

- Object-Oriented Programming (OOP)
- Abstract Classes
- Inheritance
- Polymorphism
- Method Overriding
- Classes and Objects
- Mutable Collections
- Functions
- File I/O
- CSV File Handling
- CRUD Operations
- Conditional Statements
- Loops
- User Input Validation
- Console Application Development
