package com.example.quizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.R
import com.example.quizapp.data.Quiz
import com.example.quizapp.ui.QuizDetailsFragment.Companion.QUIZ_ID
import com.example.quizapp.vm.QuizViewModel
import kotlinx.android.synthetic.main.fragment_quiz.*
import org.koin.android.viewmodel.ext.android.viewModel

class QuizListFragment : Fragment(), QuizAdapter.QuizInterface {


    val viewModel: QuizViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel.getQuizLiveData().observe(this, Observer { data ->
            if (data != null) {
                showList(ArrayList(data))
            } else {
                showError()
            }
        })


        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListAdapter()
    }

    fun initListAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = QuizAdapter(ArrayList(), context, this@QuizListFragment)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    fun showList(channelList: ArrayList<Quiz>) {
        (recyclerView.adapter as QuizAdapter).setList(channelList)
    }

    fun showError() {
        Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
    }

    override fun onQuizClick(quiz: Quiz) {
        viewModel.selectQuiz(quiz)
        val bundle = Bundle()
        bundle.putSerializable(QUIZ_ID, quiz)
        view!!.findNavController().navigate(R.id.action_quizSourceFragment_to_quizDetailsFragment, bundle)
    }
}