package com.example.mvi_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var numberTv: TextView
    lateinit var addNumber: Button

    private val viewModel: AddNumberViewModel by lazy {

        ViewModelProvider(this).get(AddNumberViewModel::class.java)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberTv = findViewById(R.id.number_textview)
        addNumber = findViewById(R.id.button)

        render()
        // send the
        addNumber.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intentChanel.send(MainIntent.AddNNumber)
            }
        }


        // render the


    }

    private fun render() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainViewState.Idle -> numberTv.text = "Idel"
                    is MainViewState.Number -> numberTv.text = it.toString()
                    is MainViewState.Error -> numberTv.text = it.error
                }
            }
        }
    }
}