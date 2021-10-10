package com.example.mvi_example

sealed class MainViewState{

    // id
    object Idle:MainViewState()

    // number
    // عشان هيرجع داتا
    data class Number(val number:Int) : MainViewState()

    // error

    data class Error(val error:String) : MainViewState()
}
