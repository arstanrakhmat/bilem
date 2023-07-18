package com.example.bilemonline.koin

import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.data.api.BilemApi
import com.example.bilemonline.data.repository.AuthRepository
import com.example.bilemonline.data.repository.CategoryRepository
import com.example.bilemonline.data.repository.CourseRepository
import com.example.bilemonline.utils.Constants
import com.example.bilemonline.viewmodels.AuthViewModel
import com.example.bilemonline.viewmodels.CategoryViewModel
import com.example.bilemonline.viewmodels.CourseViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { getOkHttp() }
    single { getRetrofitInstance(okHttpClient = get()) }
    single { getBilemApi(retrofit = get()) }
    single { UserPreferences(androidApplication()) }
    factory { AuthRepository(api = get()) }
    factory { CourseRepository(api = get()) }
    factory { CategoryRepository(api = get()) }
}

val viewModules = module {
    viewModel { AuthViewModel(authRepository = get()) }
    viewModel { CourseViewModel(courseRepository = get()) }
    viewModel { CategoryViewModel(categoryRepository = get()) }
}

fun getOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .build()
}

fun getBilemApi(retrofit: Retrofit): BilemApi {
    return retrofit.create(BilemApi::class.java)
}

fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}