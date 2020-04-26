package com.example.android_navigation_componenet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android_navigation_componenet.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       handleClicks()
    }

    private fun handleClicks() {
        loginBinding.buttonLogin.setOnClickListener {
            if (getData()) {
                val email=fetchEmail()
                val pass=fetchPass()
                val action=LoginFragmentDirections.actionLoginFragmentToHomeFragment2(email,pass)
                it.findNavController().navigate(action)
            }else{
                txtInputEmail.error="Please Enter Email"
                txtInputPass.error="Please Enter Pass"
            }
        }

        loginBinding.buttonSignUP.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment2)
        }

        loginBinding.txtTermsLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_termsFragment)
        }

    }

    private fun getData():Boolean{
        if (fetchEmail().isEmpty())
            return false
        if (fetchPass().isEmpty())
            return false
        return true
    }


    private fun fetchEmail(): String {
       val email= loginBinding.etEmailLogin.text.toString()
        if (email.isNotEmpty())
            return email
        return ""
    }

    private fun fetchPass(): String {

        val pass= loginBinding.etPassLogin.text.toString()
        if (pass.isNotEmpty())
            return pass
        return ""
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
