//Number Guessing Game
//A console game with 3 levels,attempt tracking and a score system

import kotlin.random.Random

fun playGame(min:Int, max:Int,maxAttempts:Int)
{
    var attempts=0

    val secretNumber= Random.nextInt(min, max)
    println("Hint:The number is between $min and $max")

    while(true) {
        println("Enter your number:")
        val num=readln().toIntOrNull()

        if(num==null)
        {
            println("Please enter a valid number")
            continue
        }
        attempts++


        if(num==secretNumber)
        {
            println("You got it in $attempts ${if (attempts == 1) "guess" else "guesses"}! Congrats!!")

            val score=(maxAttempts-attempts+1)*10
            println("Your score is $score points")
            break
        }
        else
        {
            if (num>secretNumber)
            {
                println("Too high!Try a smaller number")
            }
            else
            {
                println("Too low!Try a larger number")
            }

            println("You have ${maxAttempts - attempts} guesses left")

            if (attempts==maxAttempts)
            {
                println("You ran out of guesses! The number was $secretNumber")
                return
            }

        }
    }

}
fun main()
{
    while(true)
    {
        println("===LEVELS===")
        println("1.Easy")
        println("2.Medium")
        println("3.Hard")
        println("4.Exit")

        println("Choose the level you want to attempt:")
        val level=readln()

            when(level)
            {
                "1"->playGame(0,50,10)
                "2"->playGame(50,200,7)
                "3"->playGame(200,1000,5)
                "4" ->{
                    println("You just exited from the game.Are you sure you want to exit? Choose YES or NO")
                    val exit=readln().uppercase()

                    if (exit=="YES")
                    {
                        println("You have exited the game.")
                        break
                    }
                    else
                    {
                        println("Returning to menu....")
                    }
                }
                else -> {
                    println("Invalid level")
                    continue
                }
            }
    }
}
