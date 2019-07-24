package com.example.quizapp.services

import androidx.lifecycle.MutableLiveData
import com.example.quizapp.data.Items
import com.example.quizapp.data.Quiz
import com.example.quizapp.data.QuizDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuizServiceConnection(val quizServices: QuizServices) {

    private val quizList = MutableLiveData<List<Quiz>>()
    private val quizesNumber = 100

    fun getQuizList(): MutableLiveData<List<Quiz>> {
        quizServices.getQuizzes(quizesNumber).enqueue(object : Callback<Items> {
            override fun onFailure(call: Call<Items>, t: Throwable) {
                quizList.value = null
            }

            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                quizList.value = response.body()!!.items}
        });
        return quizList
    }

    fun getQuizDetail(quizId:String): MutableLiveData<QuizDetail> {
        val quizDetail = MutableLiveData<QuizDetail>()

        quizServices.getQuizDetails(quizId).enqueue(object : Callback<QuizDetail> {
            override fun onFailure(call: Call<QuizDetail>, t: Throwable) {
                quizDetail.value = null
            }

            override fun onResponse(call: Call<QuizDetail>, response: Response<QuizDetail>) {
                quizDetail.value = response.body()!!}
        });
        return quizDetail
    }
}