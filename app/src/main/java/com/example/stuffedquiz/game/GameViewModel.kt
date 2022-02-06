package com.example.stuffedquiz.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stuffedquiz.data.TriviaRepository
import com.example.stuffedquiz.model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val triviaRepository: TriviaRepository
): ViewModel() {

    val questions: LiveData<List<Question>> = MutableLiveData()

    fun load(
        category: Int,
        difficulty: String
    ) {
        if (questions.value.isNullOrEmpty()) {
            viewModelScope.launch {
                // TODO allow user to choose number of questions
                val result = triviaRepository.getQuestions("10", category, difficulty)
                (questions as MutableLiveData).postValue(result)
            }
        }
    }
}