package com.example.myapplication

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.appbar.MaterialToolbar

class DosPalabrasFragment : Fragment() {

    private lateinit var usuario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuario = requireParentFragment().arguments?.getString("correo") ?: "Usuario"
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dos_palabras, container, false)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_dos_palabras)
        toolbar.title = "Dos Palabras"
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
        Te voy a ayudar a solucionar el problema de las 2
        pañabras.

        1. Ingresa una cadena de caracteres que
           contenga solo 2 palabras separadas por un
           espacio.
        2. Imprimiré una nueva cadena con las
           posiciones de la primera y segunda palabra
           intercambiadas (la segunda plabra se
           imprime primero). 
        """

        boton.setOnClickListener {
            val texto = input.text.toString().trim()
            val palabras = texto.split(" ")

            if (palabras.size == 2 && palabras[0].isNotEmpty() && palabras[1].isNotEmpty()) {
                val resultado = "${palabras[1]} ${palabras[0]}"
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Ups! algo salió mal revisa tu cadena", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}
