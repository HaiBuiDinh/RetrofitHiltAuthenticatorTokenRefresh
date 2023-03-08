package com.ohno.retrofithilt2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.protobuf.Api
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ohno.retrofithilt2.data.remote.model.Auth
import com.ohno.retrofithilt2.databinding.FragmentLoginBinding
import com.ohno.retrofithilt2.ui.CoroutinesErrorHandler
import com.ohno.retrofithilt2.ui.TokenViewModel
import com.ohno.retrofithilt2.util.ApiResponse
import com.ohno.retrofithilt2.util.PageUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: AuthViewModel by viewModels()
    private val tokenViewModel: TokenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tokenViewModel.token.observe(this.viewLifecycleOwner) { token ->
            if (token != null) {
                PageUtils.replaceFragment(requireActivity(), MainFragment())
            }

        }

        loginViewModel.loginResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ApiResponse.Failure -> binding.tvContent.text = it.errorMessage
                ApiResponse.Loading -> binding.tvContent.text = "Loading..."
                is ApiResponse.Success -> {
                    tokenViewModel.saveToken(it.data.token)
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            loginViewModel.login(
                Auth("test@gmail.com", "123Test"),
                object: CoroutinesErrorHandler {
                    override fun onError(message: String) {
                        binding.tvContent.text = "Error! $message"
                    }

                }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}