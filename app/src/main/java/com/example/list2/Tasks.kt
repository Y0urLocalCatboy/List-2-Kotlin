package com.example.list2
fun main() {
    task1()
    triangle(3, 4, 5)
    task3()
    println("Dice results: " + dice().contentToString())
    println("Vowels in the text 'Hello world': " + vowels("Hello World"))
    println("Is the word a palindrome? " + palindrome())
    bracketsCount("(()))")
    CeasarCode(3)
    task9()
    val array1 = arrayOf(1, 2, 3, 4, 5,21, 31, 41, 42, 51, 61, 71, 88, 99, 100, 99, 12, 17, 17, 18)
    consoleHistogram(array1)

}

fun task1(){
    println("Task 1, arrays (OUR INDEXES START WITH 0): ")
    val array1 = arrayOf(1, 2, 3, 4, 5)
    val array2 = arrayOf(6, 7, 8, 9, 10)
    array(array1, array2, 10, 3)

    println("Task 2, matrices (OUR INDEXES START WITH 0): ")
    val matrix1 = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
    val matrix2 = arrayOf(arrayOf(10, 11, 12), arrayOf(13, 14, 15), arrayOf(16, 17, 18))
    matrix(matrix1, matrix2, 2, 3, 1, 2) //k and l are the coordinates of arrays, kk and ll are the coordinates of the element in the array
}

fun array(arr1: Array<Int>, arr2: Array<Int>, k: Int, l: Int) {
    println("Arrays before: ")
    println("Array 1: ")
    arrayPrinter(arr1) //I have created a simple function to print the arrays instead of printing them manually 2 times
    println("Array 2: ")
    arrayPrinter(arr2)

    val newK = checker(arr1, k) //I have created a simple function to check if the index is out of bounds
    val newL = checker(arr2, l)

    val temp1 = arr1[newK]
    val temp2 = arr2[newL]
    arr1[newK] = temp2
    arr2[newL] = temp1

    println("Arrays after: ")
    println("Array 1: ")
    arrayPrinter(arr1)
    println("Array 2: ")
    arrayPrinter(arr2)
}
fun arrayPrinter(arr1: Array<Int>){
    for (i in arr1) {
        print("$i ")
    }
    println()
}
fun checker(arr: Array<Int>, numb: Int): Int {
    var newNumb = numb
    while (true) {
        if (newNumb < 0 || newNumb >= arr.size) {
            println("Index $newNumb is out of bounds. Choose new index: ")
            var input = readln()
            while(input.toIntOrNull() == null){
                println("Index must be an integer! Choose another number: ")
                input = readln()
            }
            newNumb = input.toInt()
        } else
            return newNumb
    }
}

fun matrix(matrix1: Array<Array<Int>>, matrix2: Array<Array<Int>>, k: Int, kk:Int, l:Int, ll:Int){
    println("Matrixes before: ")
    println("Matrix 1: ")
    matrixPrinter(matrix1) //The same as in the arrays' task
    println("Matrix 2: ")
    matrixPrinter(matrix2)

    val newK = matrixChecker(matrix1, k) //To check if the index is out of bounds (the same as in the arrays' task but with the y coordinate)
    val newL = matrixChecker(matrix2, l)

    val newKK = checker(matrix1[newK],kk) //To check if the index is out of bounds for the x coordinate
    val newLL = checker(matrix2[newL],ll)

    val temp1 = matrix1[newK][newKK] //The same as in the arrays' task
    val temp2 = matrix2[newL][newLL]
    matrix1[newK][newKK] = temp2
    matrix2[newL][newLL] = temp1

    println("Matrixes after: ")
    println("Matrix 1: ")
    matrixPrinter(matrix1)
    println("Matrix 2: ")
    matrixPrinter(matrix2)

}
fun matrixPrinter(matrix1: Array<Array<Int>>){
    for (i in matrix1) {
        for (j in i) {
            print("$j ")
        }
        println()
    }
}
fun matrixChecker(matrix: Array<Array<Int>>, numb: Int): Int {
    var newNumb = numb
    while (true) {
        if (newNumb < 0 || newNumb >= matrix.size) {
            println("Index $newNumb is out of bounds. Choose new index: ")
            var input = readln()
            while(input.toIntOrNull() == null){
                println("Index must be an integer! Choose another number: ")
                input = readln()
            }
            newNumb = input.toInt()
        } else
            return newNumb
    }
}

fun triangle(a: Int, b: Int, c: Int){
    println("Task 2: ")
    if(a+b>c && a+c>b && b+c>a){
        println("True")
    }
    else
        println("False")
}

fun task3() {
    println("Task 3: ")
    println("Select 6 numbers from 1 to 49 or type 'quick pick' for an automatic pick: ")
    val numbers = Array(6) { 0 }
    for (i in 1..6) {
        println("Choose number $i: ")
        var input = readln()
        if (input == "quick pick") {
            for (j in i..6) {
                var random = (1..49).random()
                while(numbers.contains(random)){ //Okay, okay I KNOW that I used the same five lines of code in lottoarray function but do I REALLY want to create a small function just for that??
                    random = (1..49).random()
                }
                numbers[j - 1] = random
            }
            break
        } else while(input.toIntOrNull() == null){
            println("Input must be an integer! Choose another number: ")
            input = readln()
        }
        var inputInt = input.toInt()
        while (inputInt < 1 || inputInt > 49 || numbers.contains(inputInt)) {
            println("Number is out of bounds or already chosen. Choose another number: ")
            input = readln()
            while(input.toIntOrNull() == null){
                println("Input must be an integer! Choose another number: ")
                input = readln()
            }
            inputInt = input.toInt()
        }
        numbers[i - 1] = inputInt
    }
    println("Your numbers are: ")
    arrayPrinter(numbers) //Something like an arrayPrinter should be in the basic Kotlin/Java library...
    println("The winning numbers are: ")
    val winningNumbers = lotto(6, 49)
    arrayPrinter(winningNumbers)
    var counter = 0
    for (i in numbers) {
        if (winningNumbers.contains(i)) {
            counter++
        }
    }
    when(counter){
        3 -> println("You have 3 numbers right. You won 24 pln")
        4 -> println("You have 4 numbers right. You won 500pln")
        5 -> println("You have 5 numbers right. You won 4000pln")
        6 -> println("You have 6 numbers right. You won 2000000pln")
        else -> println("You have $counter numbers right. You won nothing")
    }
}
fun lotto(m: Int, n:Int): Array<Int>{
    val lottoArray = Array(m){0}
    for (i in 1..m){
        var random = (1..n).random()
        while(lottoArray.contains(random)){
            random = (1..n).random()
        }
        lottoArray[i-1] = (1..n).random()
    }
    return lottoArray
}

fun dice(): Array<Int>{
    val diceResults = Array(6){0}
    for(i in 1..1000)
        diceResults[(0..5).random()]++
    return diceResults
}

fun vowels(text: String): Int{ //we have done LITERALLY the same thing in Python
    var counter = 0
    for(i in text){
        if(i == 'y' || i == 'a' || i == 'e' || i == 'i' || i == 'o' || i == 'u')
            counter++
    }
    return counter
}

fun palindrome(): Boolean{ //that was fast
    println("Type a word: ")
    var word = readln()
    word = word.uppercase()
    return word == word.reversed()
}

fun bracketsCount(expression: String) {
    val charTable = expression.toCharArray()
    var isCorrect = 0
    for(i in charTable){
        if(isCorrect < 0){
            println("The expression ' $expression ' is incorrect")
            return
        } else if(i == '(')
            isCorrect++ //I am actually quite proud of this solution - it was unironically revealed to me in a dream
        else if(i == ')')
            isCorrect--
    }
    if(isCorrect != 0)
        println("The expression ' $expression ' is incorrect")
    else
        println("The expression ' $expression ' is correct")
}

fun CeasarCode(n: Int){ //I have done this task AT LEAST three times already - in Java, Python and C++. Also Kotlin is screaming at me that function's name should start with a lowercase letter
    println("Type a text using ONLY uppercase letters: ") //65 -> A, 90 -> Z
    var text = readln()
    text = text.uppercase() //why cant I use toUpperCase() as always?? I know that I didnt have to do that but honestly it makes things more user-safe
    var charArray = text.toCharArray()
    println("The text is: ")
    println(charArray)
    for(i in 0..<charArray.size){ //I had to google this one - apparently there's no such thing as len(array) or array.length in Kotlin
        if((charArray[i] + n).code > 90) //Apparently I cant use toInt() anymore
            charArray[i] = (charArray[i] + n - 26)
        else
            charArray[i] = (charArray[i] + n)
    }
    println("The coded (by n = $n) text is: ")
    println(charArray)
}

fun baseToExp(base: Int, exp: Int): Long{ //Why have I decided to use LONG here? Exponential function rises really fast so I have to avoid overflow
    var result = 1L
    val newBase = base.toLong()
    for(i in 1..exp)
        result *= newBase
    return result
}
fun task9(){
    println("Type the base: ")
    var input = readln()
    while(input.toIntOrNull() == null){
        println("Base must be an integer! Choose another number: ")
        input = readln()
    }
    val base = input.toInt()
    println("Type the exponent: ")
    input = readln()
    while(input.toIntOrNull() == null){
        println("Exponent must be an integer! Choose another number: ")
        input = readln()
    }
    var exp = input.toInt()
    while (exp < 0){
        println("The exponent cannot be negative! Please choose a new exponent: ")
        exp = readln().toInt()
    }
    println("The result is: " + baseToExp(base, exp))
}

fun consoleHistogram(n:Array<Int>){
    val howMany = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    for(i in n){
        when(i){
            in 0..9 -> howMany[0]++
            in 10..19 -> howMany[1]++
            in 20..29 -> howMany[2]++
            in 30..39 -> howMany[3]++
            in 40..49 -> howMany[4]++
            in 50..59 -> howMany[5]++
            in 60..69 -> howMany[6]++
            in 70..79 -> howMany[7]++
            in 80..89 -> howMany[8]++
            in 90..100 -> howMany[9]++
        }
    }
    for(i in 0..8) {
        print("${i*10} - ${i*10+9}: ")
        for (j in 1..howMany[i])
            print("*")
        println()
    }
    print("90 - 100: ")
    for (j in 1..howMany[9])
        print("*")
    println()

    //The harder part:
    var counter = 0
    for(i in 0..<howMany.size) //checking for the highest height
        if(howMany[i] > counter)
            counter = howMany[i]

    for(i in 0..counter){ //how many rows
        for(j in 0..9) { //how many columns
            if (howMany[j] - 1 >= 0 && howMany[j] >= counter - i) {
                if(j==0)
                    print("  *   ")
                else if(j==9)
                    print("    *")
                else print("    *    ")
                howMany[j]--
            } else print("         ")
        }
        println()
        if(i == counter){
            for(j in 0..8)
                print("${j*10} - ${j*10+9}  ")
            print("90 - 100")
    }
}
println()
}
