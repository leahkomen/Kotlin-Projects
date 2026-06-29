// Dependencies required (add to build.gradle.kts):
// implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
// implementation("io.ktor:ktor-client-core:2.3.13")
// implementation("io.ktor:ktor-client-cio:2.3.13")
// implementation("io.ktor:ktor-client-content-negotiation:2.3.13")
// implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.13")
// implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
// implementation("org.slf4j:slf4j-nop:2.0.13")
// Also add plugin: kotlin("plugin.serialization") version "2.2.0"


import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ExchangeRateResponse(
    val base: String,
    val rates: Map<String, Double>
)

suspend fun fetchRates(): ExchangeRateResponse
{
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    val response: ExchangeRateResponse = client.get("https://api.exchangerate-api.com/v4/latest/USD").body()
    client.close()
    return response
}
fun convertCurrency(amount: Double,fromRate:Double,toRate: Double): Double
{
    val result=amount/fromRate*toRate
    return result
}

fun main() 
{
    runBlocking {
        val data = fetchRates()
        while (true) {
            println("===CURRENCY CONVERTER===")
            println("1.Convert currency")
            println("2.Exit")

            println()

            println("Please enter your your choice:")
            val choice = readln()

            if (choice !in listOf("1", "2"))
            {
                println("Please enter a valid choice")
                continue
            }
            when (choice) 
            {
                "1" -> {
                    println("Enter the currency you want to convert FROM (e.g. KES,USD,JPY,EUR,GBP):")
                    val from = readln().uppercase()

                    println("Enter the currency you want to convert TO (e.g. EUR,GBP,JPY,KES,USD):")
                    val to = readln().uppercase()

                    println("Enter the amount:")
                    val amount = readln().toDoubleOrNull() ?: 0.0

                    val fromRate = data.rates[from]
                    val toRate = data.rates[to]

                    if (fromRate != null && toRate != null) 
                    {
                        val result = convertCurrency(amount, fromRate, toRate)
                        println("$amount $from = $result $to")

                        println("Do you want to convert again?Yes or No")
                        val choice = readln().uppercase()

                        if (choice == "YES") 
                        {
                            println("Enjoy your currency conversion......")
                        }
                        else 
                        {
                            println("I hope you enjoyed the services!!")
                            break
                        }
                        
                    } 
                    else
                    {
                        println("One or both currency codes were not found. Please try again.")
                    }
                }

                "2" -> {
                    println("Are you sure you want to exit?Yes or No")
                    val choice = readln().uppercase()

                    if (choice == "YES") 
                    {
                        println("I hope you enjoyed the services!!")
                        break
                    } 
                    else
                    {
                        println("Enjoy your currency conversion......")
                    }
                }
            }
        }
    }
}
