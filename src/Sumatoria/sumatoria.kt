package Sumatoria

fun main() {

    print("Ingresa un numero: ")
    val n = readln().toInt()
    var sum = 0
    for (i in 1..n) {
        sum += i
    }

    println("La suma es $sum")

    var factorial = 1
    var j = 1
    while (j <= n) {
        factorial *= j
        j++
    }

    println("El factorial es $factorial")
}