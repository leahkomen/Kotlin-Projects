# Even-Odd Checker
A console-based Kotlin application that determines whether a number is even or odd. The program continuously accepts user input until the user chooses to exit.

## Features

- **Even number detection** — identifies numbers divisible by 2.
- **Odd number detection** — identifies numbers that are not divisible by 2.
- **Continuous execution** — allows users to check multiple numbers in one session.
- **Input validation** — handles non-numeric input gracefully.
- **Exit command** — type `exit` at any time to close the application.

## How it works
The application repeatedly prompts the user to enter a number or type `exit`. User input is converted safely using `toIntOrNull()`. If the input is a valid integer, the program checks whether the number is divisible by two using the modulus (`%`) operator.

```text
If number % 2 == 0 → Even
Otherwise → Odd
```

If the input is not a valid integer, an error message is displayed and the user is prompted again.

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle

## Concepts Practiced

- Functions
- Conditional Statements
- Loops
- User Input Validation
- Safe Type Conversion (`toIntOrNull()`)
- Arithmetic Operators
- Console Application Development
