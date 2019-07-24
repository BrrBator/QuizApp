package com.example.quizapp.modules

import com.example.quizapp.services.QuizServiceConnection
import com.example.quizapp.services.QuizServices
import com.example.quizapp.vm.QuizViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { retrofit() }
    single { QuizServiceConnection(get()) }
    viewModel { QuizViewModel(get()) }
}

fun retrofit(): QuizServices {
    return Retrofit.Builder()
        .baseUrl("http://quiz.o2.pl/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(QuizServices::class.java)
}
