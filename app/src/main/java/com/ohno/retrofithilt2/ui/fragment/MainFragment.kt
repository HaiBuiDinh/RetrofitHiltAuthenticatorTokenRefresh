package com.ohno.retrofithilt2.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ohno.retrofithilt2.databinding.FragmentMainBinding
import com.ohno.retrofithilt2.ui.CoroutinesErrorHandler
import com.ohno.retrofithilt2.ui.TokenViewModel
import com.ohno.retrofithilt2.util.ApiResponse
import com.ohno.retrofithilt2.util.PageUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private val tokenViewModel: TokenViewModel by viewModels()

    private var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tokenViewModel.token.observe(viewLifecycleOwner) { token ->
            this.token = token
            if (token == null) {
                PageUtils.replaceFragment(requireActivity(), LoginFragment())
            }
        }

        mainViewModel.userInfoResponse.observe(viewLifecycleOwner) {
            binding.infoTV.text = when (it) {
                is ApiResponse.Failure -> {
                    Log.d("hai.bui1", "Failed get")
                    "Code: ${it.code}, ${it.errorMessage}"
                }
                ApiResponse.Loading -> "Loading..."
                is ApiResponse.Success -> {
                    Log.d("hai.bui1", "ID: ${it.data.userInfo._id}\n" +
                            "Mail: ${it.data.userInfo.email_address}\n" +
                            "\n" +
                            "Token: $token")
                    "ID: ${it.data.userInfo._id}\n" +
                            "Mail: ${it.data.userInfo.email_address}\n" +
                            "\n" +
                            "Token: $token"
                }
            }
        }

        binding.infoButton.setOnClickListener {
            mainViewModel.getUserInfo(object : CoroutinesErrorHandler {
                override fun onError(message: String) {
                    binding.infoTV.text = "Error: $message"
                }

            })
        }

        binding.logoutButton.setOnClickListener {
            tokenViewModel.deleteToken()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}