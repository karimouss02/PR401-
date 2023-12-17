package com.example.p_401

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Interfaz que define el manejo de mensajes para mostrar información al usuario
 */
interface MensajeHandler {
    /**
     * Muestra un mensaje al usuario
     * @param mensaje Mensaje que se mostrará al usuario
     */
    fun mostrarMensaje(mensaje: String)
}

/**
 * Clase que representa un cuaderno de profesor y gestiona operaciones relacionadas con las notas
 * @property mensajeHandler Manejador de mensajes para mostrar información al usuario
 */
class cuaderno_profesor(private val mensajeHandler: MensajeHandler) {
    //Lista mutable que almacena las notas.
    private val notas = mutableListOf<Float>()

    /**
     * Registra nuevas notas en el cuaderno
     * @param notas Lista de notas a registrar
     */
    fun poner_Notas(notas: List<Float>) {
        this.notas.clear()
        this.notas.addAll(notas)
        mensajeHandler.mostrarMensaje("Notas registradas correctamente")
    }

    /**
     * Obtiene la nota más alta y su posición en el cuaderno
     * @return Par que contiene la nota más alta y su posición, o nulo si no hay notas registradas
     */
    fun nota_Mas_Alta(): Pair<Float, Int>? {
        if (notas.isEmpty()) {
            return null
        }

        var nota_Mas_Alta = notas[0]
        var posicion = 0
        for (i in notas.indices) {
            if (notas[i] > nota_Mas_Alta) {
                nota_Mas_Alta = notas[i]
                posicion = i
            }
        }
        mensajeHandler.mostrarMensaje("La nota más alta es: $nota_Mas_Alta, en la posición $posicion del cuaderno")
        return nota_Mas_Alta to posicion
    }

    /**
     * Calcula la media de las notas sin tener en cuenta la más baja ni la más alta
     * Muestra el resultado mediante el mensajeHandler
     */
    fun Media() {
        if (notas.isEmpty()) {
            mensajeHandler.mostrarMensaje("No hay notas registradas.")
            return
        }
        var nota_Mas_Alta = notas[0]
        var nota_Mas_Baja = notas[0]

        for (i in notas.indices) {
            if (notas[i] > nota_Mas_Alta) {
                nota_Mas_Alta = notas[i]
            } else if (notas[i] < nota_Mas_Baja) {
                nota_Mas_Baja = notas[i]
            }
        }

        var total = 0.0f
        for (element in notas) {
            total += element
        }
        total -= nota_Mas_Alta
        total -= nota_Mas_Baja

        val media = total / (notas.size - 2)
        mensajeHandler.mostrarMensaje("La media de la nota sin la más baja ni la más alta es: $media")
    }


    /**
     * Borra una nota en la posición especificada por el índice
     * @param indice Índice de la nota a borrar
     */
    fun borrar_Nota(indice: Int) {
        if (indice in 0 until notas.size) {
            notas.removeAt(indice)
            println("Nota en el índice $indice borrada")
        } else {
            println("Índice no válido")
        }
    }

    /**
     * Borra todas las notas del cuaderno
     */
    fun borrar_Todo() {
        notas.clear()
        println("Todas las notas han sido borradas")
    }
}
