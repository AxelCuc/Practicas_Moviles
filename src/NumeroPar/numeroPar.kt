package NumeroPar

fun main() {
    print("Ingresa un número N: ")
    val n = readln().toInt()

    if (n <= 0) {
        println("Inserta un número positivo")
    } else {
        var resultado = ""
        for (i in 1..n) {
            if (i % 2 == 0) {
                if (resultado.isEmpty()) {
                    resultado += "$i"
                } else {
                    resultado += ", $i"
                }
            }
        }
        println(resultado)
    }
}