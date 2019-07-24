package com.example.quizapp.data

import java.io.Serializable

data class Items(val items:List<Quiz>)

data class Quiz (val id:String,val title:String,val questions: Int,val mainPhoto: MainPhoto) : Serializable

data class MainPhoto(val url:String)