package com.mahendra.covid.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mahendra.covid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object RetrofitService {

    private val gson : Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(getUnsafeOkHttpClient(getOkHttpBuilder()).build())
        .build()

    fun <S> cteateService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }

    fun getUnsafeOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient.Builder{
        return try { // Create a trust manager that does not validate certificate chains
            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )
            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            builder.sslSocketFactory(
                sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
            )
//            builder.hostnameVerifier { hostname, session -> true }
//            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


    fun getOkHttpBuilder(): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClient.addInterceptor(logging)
        }
        return okHttpClient
    }
}