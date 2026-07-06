# Banking Management System
A console-based banking application built in Kotlin that allows users to register accounts, check balances, deposit and withdraw funds, and manage account records with persistent file storage.

## Features

- **Account registration** — create a new account with an auto-generated account number and PIN protection.
- **Account details** — securely view registered account information after PIN verification.
- **Balance inquiry** — check the current account balance.
- **Withdraw funds** — withdraw money with balance validation.
- **Deposit funds** — deposit money into an existing account.
- **Delete account** — remove an account after confirmation.
- **Persistent storage** — account information is stored in `bank.csv`, while the account counter is stored in `counter.txt`, ensuring data is retained between program runs.

## How it works
Each account stores the following information:
- Account Number
- PIN
- Balance
- Account Holder Name
- National ID

All accounts are maintained in a `MutableList<Account>`. Whenever an account is added, updated, or deleted, the changes are automatically saved to a CSV file. When the application starts, the saved data is loaded back into memory, allowing users to continue where they left off.

## Run the Project

```bash
./gradlew run
```

## Requirements

- Kotlin
- Gradle

## Concepts Practiced

- Object-Oriented Programming (OOP)
- Classes
- Mutable Collections
- Functions
- File I/O
- CSV File Handling
- Exception Handling
- User Input Validation
- Console Application Development
