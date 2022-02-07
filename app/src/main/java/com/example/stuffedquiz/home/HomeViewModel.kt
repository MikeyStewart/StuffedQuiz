package com.example.stuffedquiz.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stuffedquiz.data.TriviaRepository
import com.example.stuffedquiz.model.Category
import com.example.stuffedquiz.model.Difficulty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val triviaRepository: TriviaRepository
): ViewModel() {

    // TODO only expose immutable LiveData
    val categories: LiveData<List<Category>> = MutableLiveData()
    val selectedCategory: LiveData<Category?> = MutableLiveData(null)
    val selectedDifficulty: LiveData<Difficulty?> = MutableLiveData(null)

    init {
        viewModelScope.launch {
            (categories as MutableLiveData).postValue(
                triviaRepository.getCategories()
            )
            // TODO load session token
        }
    }

    fun updateSelectedCategory(category: Category?) {
        (selectedCategory as MutableLiveData).postValue(category)
    }

    fun updateSelectedDifficulty(difficulty: Difficulty?) {
        (selectedDifficulty as MutableLiveData).postValue(difficulty)
    }

    fun resetSelections() {
        updateSelectedCategory(null)
        updateSelectedDifficulty(null)
    }
}