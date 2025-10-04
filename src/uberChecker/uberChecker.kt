package uberChecker

fun main() {
    println("Ingrese la distancia del conductor en kilómetros: ")

    val distancia = readln().toDouble()
    println("Ingrese la disponibilidad del conductor (true/false): ")

    val disponible = readln().toBoolean()
    if (distancia <= 0.5 && disponible) {

        println("Listo para iniciar recorrido")

    } else if (distancia <= 0.5) {

        println("Conductor cercano, pero no disponible")

    } else if (disponible) {

        println("Conductor disponible pero muy lejos, aplicarán tarifas más altas")

    } else {

        println("No hay conductores disponibles")

    }
}
