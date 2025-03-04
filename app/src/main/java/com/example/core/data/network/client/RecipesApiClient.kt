package com.example.core.data.network.client
import com.example.core.constants.K
import com.example.core.data.network.services.RecipeService
import com.example.recipeapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipesApiClient {
    private fun getHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)

        return httpClient.build()
    }

    fun createRecipeService(): RecipeService {
        return Retrofit.Builder()
            .client(getHttpClient())
            .baseUrl(K.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)
    }
}