package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val topAppBar = view.findViewById<MaterialToolbar>(R.id.topAppBar)
        topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val etCorreo = view.findViewById<EditText>(R.id.et_correo)
        val btnIngresar = view.findViewById<Button>(R.id.btn_ingresar)


        btnIngresar.setOnClickListener {
            val correo = etCorreo.text.toString()
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(correo)
            findNavController().navigate(action)
        }

        return view
    }

}
