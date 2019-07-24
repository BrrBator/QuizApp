package com.example.quizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.quizapp.R
import com.example.quizapp.data.Quiz
import com.example.quizapp.data.QuizDetail
import com.example.quizapp.vm.QuizViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_quiz_details.*
import org.koin.android.viewmodel.ext.android.viewModel


class QuizDetailsFragment : Fragment() {

    companion object {
        const val QUIZ_ID = "QUIZ_ID"
    }

    val viewModel: QuizViewModel by viewModel()// todo: problem with koin library https://github.com/InsertKoinIO/koin/issues/145
    lateinit var quiz: Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quiz = arguments!!.getSerializable(QUIZ_ID) as Quiz
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quiz_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectQuiz(quiz)

        viewModel.getQuizDetailLiveData().observe(this, Observer { data ->
            if (data != null) {
                updateView(data, 0)
            } else {
                showError()
            }
        })

        viewModel.quizQuestionNo().observe(this, Observer { data ->
            if (data != null && viewModel.quizDetail().value != null) {
                updateView(viewModel.quizDetail().value!!, data)
            } else {
                showError()
            }
        })

        buttonNext.setOnClickListener {
            val answerId =
                radioButtonGroup.indexOfChild(radioButtonGroup.findViewById(radioButtonGroup.checkedRadioButtonId))

            if (answerId == -1)
                Toast.makeText(context, answerId.toString(), Toast.LENGTH_SHORT).show()
            else
                viewModel.checkAnswer(answerId)
        }
    }

    fun updateView(quizDetail: QuizDetail, questionId: Int) {
        text.setText(quizDetail.questions.get(questionId).text)
        val imagePath = quizDetail.questions.get(questionId).image.url

        if (imagePath.isNotEmpty()) {
            Picasso.get()
                .load(imagePath)
                .into(image)
        }

        (radioButtonGroup.getChildAt(0) as RadioButton).setText(quizDetail.questions.get(questionId).answers.get(0).text)
        (radioButtonGroup.getChildAt(1) as RadioButton).setText(quizDetail.questions.get(questionId).answers.get(1).text)
    }

    fun showError() {
        Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
    }


}