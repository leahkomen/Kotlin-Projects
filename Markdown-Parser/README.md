# Markdown Parser
A console-based Markdown parser built in Kotlin that converts a subset of Markdown syntax into HTML using Regular Expressions (Regex). The project demonstrates text parsing, pattern matching, and HTML generation by transforming Markdown-formatted text into valid HTML.

## Features

- **Parse headings** — converts Markdown headings (`#`, `##`, `###`) into HTML heading tags (`<h1>`–`<h3>`).
- **Parse bold text** — converts `**bold**` into `<b>`.
- **Parse italic text** — converts `*italic*` into `<i>`.
- **Parse inline code** — converts `` `code` `` into `<code>`.
- **Parse hyperlinks** — converts Markdown links into HTML anchor tags.
- **Parse unordered lists** — converts list items beginning with `-` into HTML list items.
- **Generate HTML lists** — automatically wraps consecutive list items inside `<ul>` tags.
- **Generate complete HTML documents** — wraps the converted content inside a basic HTML page structure.
- **Regex-based parsing** — uses Kotlin's `Regex` class and capturing groups to process Markdown syntax.

## How it works
The application processes Markdown text in several stages. Each parser function is responsible for converting one Markdown feature into its HTML equivalent.
The parsing pipeline performs the following operations:

1. Convert headings
2. Convert hyperlinks
3. Convert bold text
4. Convert unordered list items
5. Wrap list items inside `<ul>` tags
6. Convert italic text
7. Convert inline code
8. Wrap the final output inside a complete HTML document

For example, the Markdown input:

```text
# Welcome

This is **bold**, *italic*, and `code`.

- Apple
- Bread

Visit [Kotlin](https://kotlinlang.org)
```

is converted into HTML containing headings, formatting tags, unordered lists, hyperlinks, and code elements.

## Run it

```bash
./gradlew run
```

## Requirements
- Kotlin
- Gradle

## Concepts Practiced

- Regular Expressions (Regex)
- Pattern Matching
- Capturing Groups
- String Manipulation
- HTML Generation
- Markdown Parsing
- Functions
- Higher-Order Functions
- Lambda Expressions
- Conditional Processing
- Console Application Development

## Example

### Markdown Input

```markdown
# Welcome to My Blog

This is **bold**, this is *italic*, and this is `code`.

Check out [Kotlin](https://kotlinlang.org) for more info.

## Shopping List
- Apples
- Bread
- Milk
```

### HTML Output

```html
<html>
<head>
<title>My Markdown Page</title>
</head>
<body>
<h1>Welcome to My Blog</h1>

This is <b>bold</b>, this is <i>italic</i>, and this is <code>code</code>.

Check out <a href="https://kotlinlang.org">Kotlin</a> for more info.

<h2>Shopping List</h2>

<ul>
<li>Apples</li>
<li>Bread</li>
<li>Milk</li>
</ul>

</body>
</html>
```

---

This project was built to practice Kotlin Regular Expressions, string processing, and HTML generation by implementing a lightweight Markdown parser that converts common Markdown syntax into HTML.
