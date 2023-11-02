package com.loginapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.lifecycleScope
import com.loginapi.ApiParameters.DEVICE_ID
import com.loginapi.ApiParameters.DEVICE_TOKEN
import com.loginapi.ApiParameters.DEVICE_TYPE
import com.loginapi.ApiParameters.EMAIL
import com.loginapi.ApiParameters.PASSWORD
import com.loginapi.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (email.isEmpty()) {
                binding.email.error = "Email Required"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.password.error = "Password Required"
                binding.password.requestFocus()
                return@setOnClickListener
            }
            loginCallback()
        }
    }

    @SuppressLint("HardwareIds")
    internal fun getUuid(): String? {
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }
    private fun loginCallback() {
        val deviceType = "android"
        val options: MutableMap<String?, String?> = HashMap()
        options[EMAIL] = binding.email.text.toString().trim()
        options[PASSWORD] = binding.password.text.toString().trim()
        options[DEVICE_ID] = getUuid()
        options[DEVICE_TYPE] = deviceType
        options[DEVICE_TOKEN] = ApplicationClass.token
        lifecycleScope.launchWhenStarted {
            val resultResponse = UserRepository().logUser(options)
            apiResponseResult(resultResponse)
        }
    }
    private fun apiResponseResult(resultResponse: ResultResponse) {

        return when (resultResponse) {
            is ResultResponse.Success<*> -> {
                runOnUiThread {

                    val response = resultResponse.response as LoginResponse
                    if (response.status == "true") {
                        val user: Data? = response.data
                        PrefData.setModel(
                            ApplicationClass.instance?.applicationContext!!,
                            PrefData.KEY_USER_MODEL,
                            user
                        )
                    } else {
//                            showSnackBar(binding.rootView, response.message.toString())

                    }
                }
            }

            else -> {
                runOnUiThread {
//                        showError(resultResponse, binding.rootView)
                }
            }
        }
    }
}

