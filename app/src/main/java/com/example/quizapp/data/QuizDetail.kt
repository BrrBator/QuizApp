package com.example.quizapp.data

data class QuizDetail (val questions: List<Questions>)

data class Questions (val text:String,val order:Int,val answers: List<Answers>, val image: Image)

data class Answers (val text:String,val isCorrect:Int, val image: Image)

data class Image (val url:String)