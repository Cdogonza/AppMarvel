package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)


        binding.loginBtn.setOnClickListener {

            if (binding.email.text?.isNotEmpty() == true && binding.password.text?.isNotEmpty() == true) {

                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
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
        // Inflate the layout for this fragment
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
        val fragment = DashboardFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_Container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}