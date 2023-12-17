package com.example.p_401

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Clase que implementa la interfaz [View.OnClickListener] para manejar los clics de los botones en la interfaz
 * de usuario
 * @property activity Instancia de [AppCompatActivity] que representa la actividad actual
 * @property cuadernoProfesor Instancia de [CuadernoProfesor] que se utiliza para realizar operaciones en el cuaderno
 */
class ButtonClickListener(private val activity: AppCompatActivity, private val cuadernoProfesor: cuaderno_profesor) :
    View.OnClickListener {

    /**
     * Maneja los clics de los botones en la interfaz de usuario
     * @param view Vista que ha recibido el clic
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnPutGrades -> {
                val builder = AlertDialog.Builder(activity)
                val input = EditText(activity)
                builder.setView(input)
                builder.setTitle("Poner las notas")
                builder.setMessage("Introduce las notas separadas por coma:")
                builder.setPositiveButton("OK") { _, _ ->
                    val notasInput = input.text.toString()
                    val notas = notasInput.split(",").map { it.trim().toFloatOrNull() ?: 0.0f }
                    cuadernoProfesor.poner_Notas(notas)
                }
                builder.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.cancel()
                }
                builder.show()
            }

            R.id.btnGetHighest -> {
                val result = cuadernoProfesor.nota_Mas_Alta()
                if (result != null) {
                    val (notaMasAlta, posicion) = result
                    //Realiza las acciones necesarias con la información obtenida
                } else {
                    Toast.makeText(activity, "No hay notas registradas.", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btnCalculateMedia -> {
                cuadernoProfesor.Media()
            }

            R.id.btnDeleteNote -> {
                val builder = AlertDialog.Builder(activity)
                val input = EditText(activity)
                builder.setView(input)
                builder.setTitle("Borrar una nota")
                builder.setMessage("Indica el índice de la nota para eliminarla:")
                builder.setPositiveButton("OK") { _, _ ->
                    val indice = input.text.toString().toIntOrNull()
                    if (indice != null) {
                        cuadernoProfesor.borrar_Nota(indice)
                        Toast.makeText(activity, "Nota en el índice $indice borrada", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "Índice no válido", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.cancel()
                }
                builder.show()
            }

            R.id.btnDeleteAllNotes -> {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("Borrar todas las notas")
                builder.setMessage("¿Estás seguro de que quieres borrar todas las notas?")
                builder.setPositiveButton("Sí") { _, _ ->
                    cuadernoProfesor.borrar_Todo()
                    Toast.makeText(activity, "Todas las notas borradas", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.cancel()
                }
                builder.show()
            }

            R.id.btnExit -> activity.finish()
        }
    }
}
