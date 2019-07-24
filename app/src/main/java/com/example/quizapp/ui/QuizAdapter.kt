package com.example.quizapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.data.Quiz
import com.example.quizapp.R
import com.example.quizapp.data.QuizDetail

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.quiz_item.view.*

class QuizAdapter(val items: ArrayList<Quiz>, val context: Context, val quizInterface: QuizInterface) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.quiz_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quiz = items.get(position)
        holder.title.text = quiz.title

        Picasso.get()
            .load(quiz.mainPhoto.url)
            .into(holder.image)

        holder.layout.setOnClickListener {quizInterface.onQuizClick(quiz)  }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(channels: ArrayList<Quiz>) {
        items.clear()
        items.addAll(channels)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.titleText
        val image = view.image
        val layout = view.item_layout
    }

    interface QuizInterface{
       fun onQuizClick(quiz:Quiz)
    }
}



