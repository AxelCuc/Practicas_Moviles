package com.axelcuc.coroutines_app.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesViewModel : ViewModel(){
    val result = mutableStateOf("")
    val result2 = mutableStateOf("")
    val result3 = mutableStateOf("")

    var isLoading = mutableStateOf( false)
    var isLoading2 = mutableStateOf( false)
    var isLoading3 = mutableStateOf( false)


    fun callToApi(){
        viewModelScope.launch {
            call()
        }
    }

    suspend fun call(){
        isLoading.value = true
        result.value = withContext(Dispatchers.IO){
            delay(3000)
            "Result 1"
        }
        isLoading.value = false
    }

    fun callToApi2(){
        viewModelScope.launch {
            call2()
        }
    }

    suspend fun call2(){
        isLoading2.value = true
        result2.value = withContext(Dispatchers.IO){
            delay(3000)
            "Result 2"
        }
        isLoading2.value = false
    }

    fun callToApi3(){
        viewModelScope.launch {
            call3()
        }
    }

    suspend fun call3(){
        isLoading3.value = true
        result3.value = withContext(Dispatchers.IO){
            delay(3000)
            "Result 3"
        }
        isLoading3.value = false
    }
}