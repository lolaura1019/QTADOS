package com.example.myapplication

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.appbar.MaterialToolbar

class DosMitadesFragment : Fragment() {

    private lateinit var usuario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuario = arguments?.getString("correo") ?: "Usuario"
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dos_mitades, container, false)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_dos_mitades)
        toolbar.title = "Dos Mitades"
        toolbar.inflateMenu(R.menu.menu_cerrar_sesion)
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_cerrar_sesion) {
                val mainNavController = Navigation.findNavController(
                    requireActivity(), R.id.nav_host_fragment
                )
                mainNavController.popBackStack(R.id.loginFragment, false)
                true
            } else {
                false
            }
        }

        val saludo = view.findViewById<TextView>(R.id.tv_saludo)
        val explicacion = view.findViewById<TextView>(R.id.tv_explicacion)
        val input = view.findViewById<EditText>(R.id.et_entrada)
        val boton = view.findViewById<Button>(R.id.btn_solucionar)

        saludo.text = "Hola $usuario"
        explicacion.text = """
            Te voy a ayudar a solucionar el problema de las 2 mitades.

            1. Ingresa una cadena de caracteres.
            2. Te ayudare a cortar la cadena en dos partes 
               "iguales"(si la longitud de la cadena es impar,
               colocare el carecter central en la primera 
               cadena, de modo que la priemra cadena
               contenga un caracter mas que la segunda).
            3. Luego imprime la nueva cadena en una sola
               fila con la primera y la segunda mitad
               intercambiadas (la segunda mitad es la
               primera y la primera mitad es la segunda)
        """.trimIndent()

        boton.setOnClickListener {
            val texto = input.text.toString()
            if (texto.isNotEmpty()) {
                val mitad = (texto.length + 1) / 2
                val primera = texto.substring(0, mitad)
                val segunda = texto.substring(mitad)
                val resultado = segunda + primera
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Ups! algo salió mal revisa tu cadena.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
