# Log File Analyzer
A console-based log file parser built in Kotlin that uses Regular Expressions (Regex) to extract structured information from application log files. The project demonstrates how to parse log entries, extract IP addresses, and process multiple log records efficiently.

## Features

- **Parse log entries** — extract the date, time, log level, and message from a log entry.
- **Extract IP addresses** — identify and retrieve IPv4 addresses contained in log messages.
- **Process multiple log entries** — parse an entire log file using a single regular expression.
- **Support multiple log levels** — recognizes `ERROR`, `WARNING`, and `INFO` log messages.
- **Regex-based parsing** — uses Kotlin's `Regex`, capturing groups, and `findAll()` for efficient text processing.
- **Graceful error handling** — reports when log entries do not match the expected format.

## How it works
The application uses a regular expression to match log entries with the following format:

```text
YYYY-MM-DD HH:MM:SS [LEVEL] Message
```

Example:

```text
2026-06-20 14:32:01 [ERROR] Failed login attempt from IP 192.168.1.105
```

The parser extracts:

- Year
- Month
- Day
- Hour
- Minute
- Second
- Log Level
- Message

A separate regular expression searches log messages for IPv4 addresses and returns the detected IP address if one exists.
For processing multiple log entries, the application uses `findAll()` with the `MULTILINE` regex option to iterate through every log record in the input.

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle

## Concepts Practiced

- Regular Expressions (Regex)
- Capturing Groups
- Pattern Matching
- `find()`
- `findAll()`
- `groupValues`
- Multiline Regex
- Nullable Types
- Functions
- Loops
- Conditional Statements
- String Processing
- Console Application Development

## Example

```text
Year: 2026
Month: 06
Day: 20
Hour: 14
Minute: 32
Second: 01
Level: ERROR
Message: Failed login attempt from IP 192.168.1.105

192.168.1.105
null

ERROR   Failed login attempt from IP 192.168.1.105
INFO    User Favor logged in successfully from IP 41.89.64.12
WARNING High memory usage detected: 87%
ERROR   Database connection timeout from IP 192.168.1.105
INFO    User admin logged in successfully from IP 102.0.5.21
```

---

This project was built to practice Kotlin Regular Expressions by parsing structured log files, extracting useful information, and processing multiple log entries using pattern matching techniques.
