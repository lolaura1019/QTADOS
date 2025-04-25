package com.example.myapplication

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.appbar.MaterialToolbar

class QuitarFragmentoFragment : Fragment() {

    private lateinit var usuario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuario = requireParentFragment().arguments?.getString("correo") ?: "Usuario"
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_quitar_fragmento, container, false)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_quitar_fragmento)
        toolbar.title = "Quitar Fragmento"
        toolbar.inflateMenu(R.menu.menu_cerrar_sesion)

        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_cerrar_sesion) {
                val mainNavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
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
        Te voy a ayudar a solucionar el problema quitar el
        fragmento.

        1. Ingresa una cadena de caracteres en la que
           por lo menos existe 2 veces la letra h.
        2. Eliminare de esa cadena la primera y la ultima
           aparicion de la letra h, asi como todos los
           caracteres entre ellas.
        3. Luego imprimiré la nueva cadena en pantalla.   
        """

        boton.setOnClickListener {
            val texto = input.text.toString()
            val i = texto.indexOf('h')
            val j = texto.lastIndexOf('h')

            if (i != -1 && j != -1 && i < j) {
                val resultado = texto.removeRange(i, j + 1)
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Ups! algo salió mal revisa tu cadena", Toast.LENGTH_SHORT).show()
            }

        }

        return view
    }
}
