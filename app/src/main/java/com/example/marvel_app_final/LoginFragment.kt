package com.example.marvel_app_final

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.example.marvel_app_final.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val editor = binding.root.context.getSharedPreferences("sharedPrefs", MODE_PRIVATE).edit()
        editor.putBoolean("isLogged", false)
        editor.putString("email", "")
        editor.apply()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        val editor = binding.root.context.getSharedPreferences("sharedPrefs", MODE_PRIVATE).edit()
        editor.putBoolean("isLogged", false)
        editor.putString("email", "")
        editor.apply()

        binding.loginBtn.setOnClickListener {

            if (binding.email.text?.isNotEmpty() == true && binding.password.text?.isNotEmpty() == true) {

                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                editor.putBoolean("isLogged", true)
                editor.putString("email", email)
                editor.apply()
                login(email, password)
            }else{
                Toast.makeText(requireContext(), "Debe de Rellenar los campos de Email y Password", Toast.LENGTH_SHORT).show()
            }
        }
        binding.signUpBtn.setOnClickListener {
            if (binding.email.text?.isNotEmpty() == true && binding.password.text?.isNotEmpty() == true) {

                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                registrarse(email, password)
            }else{
                Toast.makeText(requireContext(), "Debe de Rellenar los campos de Email y Password", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun registrarse(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Usuario Creado Correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    showError()
                }
            }
    }


    private fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    binding.email.text?.clear()
                    binding.password.text?.clear()
                    showDashboard()
                } else {
                    showError()
                }
            }
    }

    private fun showError() {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }

    private fun showDashboard() {

        var navController = findNavController()
        navController.navigate(R.id.action_loginFragment_to_allCharactersFragment)
    }


}