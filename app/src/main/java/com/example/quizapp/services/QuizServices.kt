package com.example.quizapp.services

import com.example.quizapp.data.Items
import com.example.quizapp.data.Quiz
import com.example.quizapp.data.QuizDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuizServices {

    @GET("quizzes/0/{quizzesNumber}")
    fun getQuizzes(@Path("quizzesNumber") quizzesNumber: Int): Call<Items>

    @GET("quiz/{quizId}/0")
    fun getQuizDetails(@Path("quizId") quizId: String): Call<QuizDetail>
}