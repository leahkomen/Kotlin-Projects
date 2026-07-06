# Currency Converter
A console-based currency converter built in Kotlin that retrieves live exchange rates from the ExchangeRate API and converts amounts between different currencies.

## Features

- **Live exchange rates** — fetches the latest exchange rates from the ExchangeRate API.
- **Currency conversion** — converts between supported currencies using real-time exchange rates.
- **Multiple currencies** — supports any currency available in the API response (e.g., USD, EUR, KES, GBP, JPY).
- **Multiple conversions** — performs multiple currency conversions during a single program session.
- **Input validation** — validates currency codes and numeric input.
- **Exit confirmation** — confirms before closing via the Exit menu option.

## How it works
The application retrieves the latest exchange rates from the ExchangeRate API using the Ktor HTTP client. The JSON response is deserialized into an `ExchangeRateResponse` data class using `kotlinx.serialization`.
To convert between two currencies, the application first converts the source amount back to the base currency (USD), then converts it to the target currency using the formula:

```text
convertedAmount = amount / fromRate × toRate
```

Users interact with the application through a menu-driven interface by selecting the source currency, destination currency, and amount to convert. Exchange rates are downloaded once when the application starts and reused for all conversions during that session. Choosing not to convert again after a conversion exits the application directly, while the Exit menu option prompts for a separate confirmation before closing.

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle
- Kotlin Coroutines
- Ktor Client (CIO)
- kotlinx.serialization
- Internet connection

## Concepts Practiced

- Coroutines
- HTTP Networking with Ktor Client
- REST API Consumption
- JSON Serialization and Deserialization
- Data Classes
- Functions
- Maps
- User Input Validation
- Console Application Development
