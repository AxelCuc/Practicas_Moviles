package PocionMultijugo

fun main() {
    println("Ingresa la cantidad en miligramos que usaras para la poción multijugos: ")
    val cantidad = readln().toInt()

    if (cantidad > 100) {
        println("¡Felicidades, es una buena poción multijugos!")
    } else {
        println("La poción es mediocre, sangre sucia inmunda")
    }
}
