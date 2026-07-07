# Number Guessing Game
A console-based number guessing game built in Kotlin where players attempt to guess a randomly generated number within a limited number of attempts. The game features three difficulty levels, attempt tracking, a scoring system, and input validation.

## Features

- **Three difficulty levels** — choose between Easy, Medium, and Hard.
- **Random number generation** — generates a new secret number for every game.
- **Hint system** — displays the range in which the secret number lies.
- **Limited attempts** — each difficulty level provides a different number of guesses.
- **High/Low feedback** — informs the player whether the guess is too high or too low.
- **Attempt tracking** — displays the number of remaining guesses after each incorrect attempt.
- **Score system** — awards points based on how quickly the player guesses the correct number.
- **Input validation** — rejects non-numeric input without crashing the program.
- **Exit confirmation** — asks for confirmation before exiting the game.

## Difficulty Levels

| Level | Number Range | Attempts |
|--------|--------------|---------:|
| Easy | 0 – 50 | 10 |
| Medium | 50 – 200 | 7 |
| Hard | 200 – 1000 | 5 |

## How it works
When the player selects a difficulty level, the game generates a random secret number within the corresponding range.
The player repeatedly enters guesses until either:

- The correct number is guessed, or
- The maximum number of attempts is reached.

After each incorrect guess, the game provides feedback indicating whether the guess is too high or too low, along with the number of remaining attempts.
If the player guesses correctly, a score is calculated using the formula:

```text
Score = (Maximum Attempts − Attempts Used + 1) × 10
```

The fewer guesses used, the higher the final score.

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle

## Concepts Practiced

- Random Number Generation
- Functions
- Loops
- Conditional Statements
- Input Validation
- User Interaction
- Score Calculation
- Game Logic
- Console Application Development

## Example

```text
=== LEVELS ===
1. Easy
2. Medium
3. Hard
4. Exit

Choose the level you want to attempt:
1

Hint: The number is between 0 and 50

Enter your number:
25

Too low! Try a larger number.
You have 9 guesses left.

Enter your number:
38

You got it in 2 guesses! Congrats!!
Your score is 90 points
```

---

This project was built to practice Kotlin programming fundamentals by implementing a console-based game featuring random number generation, user input validation, scoring, and game flow control.
