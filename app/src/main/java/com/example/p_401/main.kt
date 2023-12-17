package com.example.p_401

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Scanner

/**
 * Clase principal que representa la actividad principal de la aplicación Android
 * Implementa la interfaz [MensajeHandler] para mostrar mensajes a través de Toast
 */

/**
 * Actividad principal que gestiona las interacciones de los botones y muestra mensajes mediante Toast
 */
class MainActivity : AppCompatActivity(), MensajeHandler {

    //Cuaderno de profesor utilizado para gestionar las notas
    private val cuadernoProfesor = cuaderno_profesor(this)

    /**
     * Función llamada cuando se crea la actividad
     * Configura los listeners de los botones y realiza la inicialización necesaria
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Configuración de listeners para los botones
        val buttonClickListener = ButtonClickListener(this, cuadernoProfesor)
        val btnPutGrades: Button = findViewById(R.id.btnPutGrades)
        val btnGetHighest: Button = findViewById(R.id.btnGetHighest)
        val btnCalculateMedia: Button = findViewById(R.id.btnCalculateMedia)
        val btnDeleteNote: Button = findViewById(R.id.btnDeleteNote)
        val btnDeleteAllNotes: Button = findViewById(R.id.btnDeleteAllNotes)
        val btnExit: Button = findViewById(R.id.btnExit)

        btnPutGrades.setOnClickListener(buttonClickListener)
        btnGetHighest.setOnClickListener(buttonClickListener)
        btnCalculateMedia.setOnClickListener(buttonClickListener)
        btnDeleteNote.setOnClickListener(buttonClickListener)
        btnDeleteAllNotes.setOnClickListener(buttonClickListener)
        btnExit.setOnClickListener(buttonClickListener)
    }
    /**
     * Implementación de la interfaz [MensajeHandler] para mostrar mensajes mediante Toast
     */
    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
/**
 * Función principal que simula la ejecución de la aplicación en un entorno de consola
 * Utiliza un cuaderno de profesor y un scanner para simular interacciones de usuario
 */
fun main() {
    val mensajeHandler = object : MensajeHandler {
        override fun mostrarMensaje(mensaje: String) {
            println(mensaje)
        }
    }
    //Cuaderno de profesor utilizado para gestionar las notas.
    val cuadernoProfesor = cuaderno_profesor(mensajeHandler)
    val scanner = Scanner(System.`in`)

    var exit = false
    do {
        println("Menú: \n" +
                "1. Poner las notas\n" +
                "2. Obtener la nota más alta y su posición\n" +
                "3. Calcular la media sin la nota más alta y baja\n" +
                "4. Borrar una nota\n" +
                "5. Borrar todas las notas\n" +
                "6. Exit")

        try {
            val option = scanner.nextInt()

            when (option) {
                1 -> {
                    println("Ingresa las notas separadas por coma:")
                    val notasInput = scanner.next()
                    val notas = notasInput.split(",").map { it.trim().toFloatOrNull() ?: 0.0f }
                    cuadernoProfesor.poner_Notas(notas)
                }
                2 -> {
                    val result = cuadernoProfesor.nota_Mas_Alta()
                    if (result != null) {
                        val (notaMasAlta, posicion) = result
                        println("La nota más alta es: $notaMasAlta, en la posición $posicion del cuaderno")
                    } else {
                        println("No hay notas registradas.")
                    }
                }
                3 -> cuadernoProfesor.Media()
                4 -> {
                    println("Indica el índice de la nota para eliminarla:")
                    val indice = scanner.nextInt()
                    cuadernoProfesor.borrar_Nota(indice)
                }
                5 -> cuadernoProfesor.borrar_Todo()
                6 -> {
                    println("Saliendo...")
                    exit = true
                }
                else -> println("Por favor, intenta de nuevo")
            }
        } catch (e: Exception) {
            println("Por favor, introduce un número válido")
            scanner.nextLine() // Limpiar el buffer del scanner
        }
    } while (!exit)
}
