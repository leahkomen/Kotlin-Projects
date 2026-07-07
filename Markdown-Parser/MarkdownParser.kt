fun wrapInHtml(content: String): String
{
    return """
        <html>
        <head>
        <title>My Markdown Page</title>
        </head>
        <body>
        $content
        </body>
        </html>
    """.trimIndent()
}
fun parseHeadings(text: String): String
{
    val pattern= Regex("""^(#{1,3})\s(.+)$""", RegexOption.MULTILINE)
    return pattern.replace(text){matchResult ->
        val level=matchResult.groupValues[1].length
        val content=matchResult.groupValues[2]
        "<h$level>$content</h$level>"
    }
}
fun parseLinks(text: String): String
{
    val linkPattern= Regex("""\[(.+?)\]\((.+?)\)""")
    return linkPattern.replace(text){matchResult ->
        val linkText=matchResult.groupValues[1]
        val url=matchResult.groupValues[2]
        "<a href=\"$url\">$linkText</a>"
    }
}
fun parseBold(text: String): String
{
    val pattern= Regex("""\*\*(.+?)\*\*""")
    return pattern.replace(text){matchResult ->
        "<b>${matchResult.groupValues[1]}</b>"
    }
}
fun parseLists(text: String):String
{
    val pattern= Regex("""^-\s(.+)$""",RegexOption.MULTILINE)
    return pattern.replace(text){matchResult ->
        val content=matchResult.groupValues[1]
        "<li>${content}</li>"
    }
}
fun wrapLists(text: String): String
{
    val wrapPattern= Regex("""(<li>.*</li>\n?)+""")
    return wrapPattern.replace(text){matchResult ->
        "<ul>\n${matchResult.value}\n</ul>\n"
    }
}
fun parseItalic(text: String): String
{
    val pattern= Regex("""(?<!\*)\*(?!\*)(.+?)(?<!\*)\*(?!\*)""")
    return pattern.replace(text){matchResult ->
        "<i>${matchResult.groupValues[1]}</i>"
    }
}
fun parseCode(text: String): String
{
    val pattern= Regex("""`(.+?)`""")
    return pattern.replace(text){matchResult ->
        "<code>${matchResult.groupValues[1]}</code>"
    }
}
fun parseMarkdown(text: String): String
{
    var result=text
    result=parseHeadings(result)
    result=parseLinks(result)
    result=parseBold(result)
    result=parseLists(result)
    result=wrapLists(result)
    result=parseItalic(result)
    result=parseCode(result)
    return result
}

fun main()
{
        val sample1 = "# Welcome to My Blog"
        val sample2 = "This is **bold**, this is *italic*, and this is `code`."
        val sample3 = "Check out [Kotlin](https://kotlinlang.org) for more info."
        val sample4 = "## Shopping List\n- Apples\n- Bread\n- Milk"

    val combined = parseMarkdown(sample1) + "\n" +
            parseMarkdown(sample2) + "\n" +
            parseMarkdown(sample3) + "\n" +
            parseMarkdown(sample4)

    println(wrapInHtml(combined))
}
