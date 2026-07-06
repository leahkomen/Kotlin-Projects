# Calculator
A console-based calculator built in Kotlin that performs basic arithmetic operations while maintaining a history of calculations during the current program session.

## Features

- **Addition** — add two numbers.
- **Subtraction** — subtract one number from another.
- **Multiplication** — multiply two numbers.
- **Division** — divide two numbers with validation to prevent division by zero.
- **Calculation history** — view all successful calculations performed during the current session.
- **Clear history** — remove all saved calculations.
- **Input validation** — handles invalid operations and non-numeric input gracefully.

## How it works
The application uses a `Calculator` class that implements the four basic arithmetic operations:

- Addition
- Subtraction
- Multiplication
- Division

Users interact with the application through a menu-driven interface. Every successful calculation is stored in a `MutableList<String>`, allowing users to review or clear their calculation history before exiting the application.

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle

## Concepts Practiced

- Classes and Objects
- Functions
- Mutable Collections
- Conditional Statements
- Loops
- User Input Validation
- Basic Error Handling
- Console Application Development
