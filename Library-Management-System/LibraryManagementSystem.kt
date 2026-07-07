import java.io.File

data class Book(
    val id: Int,
    var title: String,
    var description: String,
    var author: String,
    var publishDate: String,
    var borrowDate: String,
    var returnDate: String
)
val books=mutableListOf<Book>()
val fileName="library.csv"

fun addBook(
    list: MutableList<Book>,
    id: Int,
    title: String,
    description: String,
    author: String,
    publishDate: String,
    borrowDate: String,
    returnDate: String
)
{
    val book=Book(id, title, description, author, publishDate, borrowDate, returnDate)
    list.add(book)
}
fun displayBook(list: MutableList<Book>,id: Int)
{
    val book=list.find { it.id == id }
    if(book!=null)
    {
        println(book)
    }
    else
    {
        println("No data found.")
    }
}
fun displayBooks(list: MutableList<Book>)
{
    if(list.isEmpty()){
        println("No data found.")
    }
    else
    {
        for (book in list)
        {
            println(book)
        }
    }
}
fun updateBook(list: MutableList<Book>,id: Int)
{
    val book=list.find { it.id == id }
    if(book != null)
    {

        println("Enter the title of the book :")
        val title= readln().uppercase()

        println("Enter the book author:")
        val author= readln().uppercase()

        println("Enter the description of the book:")
        val description= readln().uppercase()

        println("Enter the book publish date:")
        val publishDate= readln()

        book.title=title
        book.author=author
        book.description=description
        book.publishDate=publishDate
    }
    else
    {
        println("No book found.")
    }
}
fun deleteBook(list: MutableList<Book>,id: Int)
{
    val book=list.find { it.id == id }
    if(book != null)
    {
        list.remove(book)
        println("You have successfully deleted the book.")
    }
    else
    {
        println("No book found.")
    }
}
fun borrowBook(list: MutableList<Book>,title: String)
{
    val book=list.find { it.title == title }
    if(book != null)
    {
        println("Enter the borrow date:")
        val borrowDate= readln()

        println("Enter the return date:")
        val returnDate= readln()

        book.borrowDate=borrowDate
        book.returnDate=returnDate
        println("You have successfully borrowed the book.Please return the book on time.Enjoy your reading!")
    }
    else
    {
        println("That book is unavailable right now.")
    }
}
fun returnBook(list: MutableList<Book>,title: String)
{
    val book=list.find { it.title == title }
    if(book != null)
    {
        book.borrowDate=""
        book.returnDate=""

        println("You have successfully returned the book.")
    }
    else
    {
        println("That book doesn't exist.")
    }
}
fun saveBooks(list: MutableList<Book>)
{
    val lines=mutableListOf<String>()
    for (book in list)
    {
        val line ="${book.id},${book.title},${book.description},${book.author},${book.publishDate},${book.borrowDate},${book.returnDate}"
        lines.add(line)
    }
    File(fileName).writeText(lines.joinToString("\n"))
}
fun loadBooks()
{
    val file = File(fileName)
    if(file.exists())
    {
        try {
            books.clear()
            val lines = file.readLines()
            for(line in lines)
            {
                val parts = line.split(",")
                if(parts.size == 7)
                {
                    books.add(
                        Book(
                            id = parts[0].toInt(),
                            title = parts[1],
                            description = parts[2],
                            author = parts[3],
                            publishDate = parts[4],
                            borrowDate = parts[5],
                            returnDate = parts[6]
                        )
                    )
                }
            }
        }catch (e: Exception){
            println("Error loading ${file.name}")
        }
    }
}
fun main()
{
    loadBooks()

    while(true)
    {
        println("===TUMAINI LIBRARY SYSTEM ===")
        println("1.Add a book.")
        println("2.Display a book by id .")
        println("3.Display all books.")
        println("4.Update a book.")
        println("5.Delete  unuseful or outdated  book.")
        println("6.Borrow  a book.")
        println("7.Return  a book.")
        println("8.Exit from library system.")

        println("Welcome to Tumaini Library.What do you want to do today?")
        val choice=readln().toString()

        if(choice !in listOf("1", "2", "3", "4", "5", "6", "7","8"))
        {
            println("Please choose a valid option!")
            continue
        }

        when(choice)
        {
            "1"->{
                println("Enter the id:")
                val id = readln().toIntOrNull()

                if (id == null)
                {
                    println("Invalid ID. Please enter a numeric ID.")
                    continue
                }

                val existing=books.find { it.id == id}
                if(existing!=null)
                {
                    println("The id already exists.")
                    continue
                }

                println("Enter the books name:")
                val bookName= readln().uppercase()

                println("Enter the book's description:")
                val description = readln().uppercase()

                println("Enter the book author:")
                val author= readln().uppercase()

                println("Enter the book publish date:")
                val publishDate= readln()
                addBook(books,id, bookName, description,author,publishDate,"","")

                saveBooks(books)
            }
            "2"->{
                println("Enter the id of the book you want to display:")
                val id = readln().toIntOrNull()

                if (id == null)
                {
                    println("Invalid ID.")
                }
                else
                {
                    displayBook(books,id)
                }
            }
            "3"->{
                displayBooks(books)
            }
            "4"->{
                println("Enter the id of the book you want to update:")
                val id = readln().toIntOrNull()

                if (id == null)
                {
                    println("Invalid ID.")
                }
                else
                {
                    updateBook(books,id)
                    println("The book has been updated.")
                    saveBooks(books)
                }
            }
            "5"->{
                println("Enter the id of the book you want to delete:")
                val id = readln().toIntOrNull()

                if (id == null)
                {
                    println("Invalid ID.")
                    continue
                }

                println("Are you sure you want to delete this book?yes or no")
                val choice=readln().uppercase()
                if(choice=="YES")
                {
                    deleteBook(books,id)
                    saveBooks(books)
                }
            }
            "6"->{
                println("Enter the title of the book you want to borrow:")
                val title= readln().uppercase()

                borrowBook(books,title)
                saveBooks(books)
            }
            "7"->{
                println("Enter the title of the book you want to return:")
                val title= readln().uppercase()

                returnBook(books,title)
                saveBooks(books)
            }
            "8"-> {
                println("Are you sure you want to exit Tumaini Library System? Yes or No")
                val  choice= readln().uppercase()

                if(choice == "YES")
                {
                    println("Thank you for visiting the Tumaini Library.I hope you enjoyed our services.")
                    break
                }
                else
                {
                    println("Continue enjoying the Tumaini Library services.")
                }
            }
        }
    }
}
