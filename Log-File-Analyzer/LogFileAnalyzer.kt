fun parseLogLine(line: String)
{
    val pattern = Regex("""(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2}) \[(ERROR|WARNING|INFO)\] (.+)$""", RegexOption.MULTILINE)
    val matchResult = pattern.find(line)
    if (matchResult != null)
    {
        println("Year: ${matchResult.groupValues[1]}")
        println("Month: ${matchResult.groupValues[2]}")
        println("Day: ${matchResult.groupValues[3]}")
        println("Hour: ${matchResult.groupValues[4]}")
        println("Minute: ${matchResult.groupValues[5]}")
        println("Second: ${matchResult.groupValues[6]}")
        println("Level: ${matchResult.groupValues[7]}")
        println("Message: ${matchResult.groupValues[8]}")
    }
    else
    {
        println("No data found")
    }
}
fun extractIp(message: String): String?
{
    val pattern = Regex("""IP (\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})""")
    val matchResult = pattern.find(message)
    if (matchResult != null)
    {
        return matchResult.groupValues[1]
    }
    else
    {
        return null
    }
}
fun processWholeFile(line: String)
{
    val pattern = Regex("""(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2}) \[(ERROR|WARNING|INFO)\] (.+)$""", RegexOption.MULTILINE)
    val matches = pattern.findAll(line)
    matches.forEach { matchResult ->
        println("${matchResult.groupValues[7]}   ${matchResult.groupValues[8]}")
    }
}

fun main()
{
    val line = "2026-06-20 14:32:01 [ERROR] Failed login attempt from IP 192.168.1.105"
    parseLogLine(line)

    println()

    val withIp = "Failed login attempt from IP 192.168.1.105"
    val withoutIp = "High memory usage detected: 87%"
    println(extractIp(withIp))
    println(extractIp(withoutIp))

    println()

    val logs = """
2026-06-20 14:32:01 [ERROR] Failed login attempt from IP 192.168.1.105
2026-06-20 14:32:15 [INFO] User Favor logged in successfully from IP 41.89.64.12
2026-06-20 14:33:02 [WARNING] High memory usage detected: 87%
2026-06-20 14:33:45 [ERROR] Database connection timeout from IP 192.168.1.105
2026-06-20 14:34:10 [INFO] User admin logged in successfully from IP 102.0.5.21
"""

    processWholeFile(logs)
}
