package Raices

import kotlin.math.sqrt

class Raices(private val a: Double, private val b: Double, private val c: Double) {

    private fun getDiscriminante(): Double {
        return (b * b) - (4 * a * c)
    }

    private fun tieneRaices(): Boolean {
        return getDiscriminante() > 0
    }

    private fun tieneRaiz(): Boolean {
        return getDiscriminante() == 0.0
    }

    fun obtenerRaices() {
        if (tieneRaices()) {
            val discriminante = sqrt(getDiscriminante())
            val x1 = (-b + discriminante) / (2 * a)
            val x2 = (-b - discriminante) / (2 * a)
            println("Soluciones: x1 = $x1, x2 = $x2")
        } else {
            println("No tiene dos soluciones reales.")
        }
    }

    fun obtenerRaiz() {
        if (tieneRaiz()) {
            val x = -b / (2 * a)
            println("Única solución: x = $x")
        } else {
            println("No tiene una única solución.")
        }
    }

    fun calcular() {
        when {
            tieneRaices() -> obtenerRaices()
            tieneRaiz() -> obtenerRaiz()
            else -> println("No existen soluciones reales.")
        }
    }
}