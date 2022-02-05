package com.example.stuffedquiz.home

import androidx.lifecycle.ViewModel
import com.example.stuffedquiz.data.TriviaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val triviaRepository: TriviaRepository
): ViewModel() {


}