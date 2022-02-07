package com.example.stuffedquiz.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stuffedquiz.data.TriviaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val triviaRepository: TriviaRepository
) : ViewModel() {

    // TODO only expose immutable LiveData
    val questions: LiveData<List<QuestionViewState>> = MutableLiveData()
    val score: LiveData<Int> = MutableLiveData(0)

    fun load(
        category: Int,
        difficulty: String
    ) {
        if (questions.value.isNullOrEmpty()) {
            viewModelScope.launch {
                // TODO allow user to choose number of questions
                val result = triviaRepository.getQuestions("10", category, difficulty)
                (questions as MutableLiveData).postValue(
                    result.map {
                        QuestionViewState(
                            question = it.question,
                            allAnswers = it.incorrectAnswers.plus(it.correctAnswer)
                                .shuffled(), // Randomise answers
                            correctAnswer = it.correctAnswer
                        )
                    })
            }
        }
    }

    fun incrementScore() {
        (score as MutableLiveData).postValue(
            score.value!! + 1
        )
    }
}

data class QuestionViewState(
    val question: String,
    val allAnswers: List<String>,
    val correctAnswer: String,
)