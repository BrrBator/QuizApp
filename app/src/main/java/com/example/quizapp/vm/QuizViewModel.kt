package com.example.quizapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.data.Quiz
import com.example.quizapp.data.QuizDetail
import com.example.quizapp.services.QuizServiceConnection


class QuizViewModel(val quizServiceConnection: QuizServiceConnection) : ViewModel() {
    private var quizLiveData = quizServiceConnection.getQuizList()
    private var quizSelected = MutableLiveData<Quiz>()
    private var quizQuestionNo = MutableLiveData<Int>()
    private lateinit var quizDetail:MutableLiveData<QuizDetail>

    fun getQuizLiveData(): LiveData<List<Quiz>> {
        return quizLiveData
    }

    fun getQuizDetailLiveData():LiveData<QuizDetail>{
        quizDetail = quizServiceConnection.getQuizDetail(quizSelected.value!!.id)
        quizQuestionNo.value=0
        return quizDetail
    }

    fun selectQuiz(quiz: Quiz){
        quizSelected.value=quiz
    }

    fun quizDetail():LiveData<QuizDetail>{
        return quizDetail
    }

    fun quizQuestionNo():LiveData<Int>{
        return quizQuestionNo
    }

    fun checkAnswer(answerId:Int){
        //todo isCorrect
        quizQuestionNo.value = quizQuestionNo.value!!+1
    }
}