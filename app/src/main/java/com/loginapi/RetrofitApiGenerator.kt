package com.loginapi

import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiGenerator {

    private val spec: ConnectionSpec =
        ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS).supportsTlsExtensions(true)
            .tlsVersions(
                TlsVersion.TLS_1_3,
                TlsVersion.TLS_1_2,
                TlsVersion.TLS_1_1,
                TlsVersion.TLS_1_0,
                TlsVersion.SSL_3_0
            ).cipherSuites(
                CipherSuite.TLS_AES_256_GCM_SHA384,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA
            ).build()

    //with token------
    private fun okHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectionSpecs(listOf(ConnectionSpec.CLEARTEXT, spec))
        okHttpClient.connectTimeout(2, TimeUnit.MINUTES)
        okHttpClient.readTimeout(2, TimeUnit.MINUTES)
        okHttpClient.writeTimeout(2, TimeUnit.MINUTES)
        val model = Singleton.getAppContext()
            ?.let { PrefData.getModel<Data>(it, PrefData.KEY_USER_MODEL) }
        if (model != null) {
            okHttpClient.addInterceptor(TokenInterceptor(model.token.toString()))
        }
        return okHttpClient.build()
    }

    //retrofit client with token
    internal fun retrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(ApiUrls.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient()).build()
    }

    //retrofit owner url
    internal fun retrofitOwnerClient(): Retrofit {
        return Retrofit.Builder().baseUrl(ApiUrls.BASE_OWNER_URL_API)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient()).build()
    }

    //without token--------
    private fun okHttpClientWithoutToken(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectionSpecs(listOf(ConnectionSpec.CLEARTEXT, spec))
        okHttpClient.connectTimeout(2, TimeUnit.MINUTES)
        okHttpClient.readTimeout(2, TimeUnit.MINUTES)
        okHttpClient.writeTimeout(2, TimeUnit.MINUTES)

        return okHttpClient.build()
    }

    internal fun retrofitClientWithoutToken(): Retrofit {
        return Retrofit.Builder().baseUrl(ApiUrls.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClientWithoutToken())
            .build()
    }

}