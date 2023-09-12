package com.example.diceprogram

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceprogram.ui.theme.DiceprogramTheme
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var tv: TextView
    private lateinit var bt: Button
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv=findViewById(R.id.tv)
        bt=findViewById(R.id.bt)
        img=findViewById(R.id.img)

        bt.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
     val random = Random()
     val randomNumber = random.nextInt(6) + 1
        val resultText= "You rolled a $randomNumber"
        tv.text = resultText

        when (randomNumber) {
            1 -> img.setImageResource(R.drawable.dice1)
            2 -> img.setImageResource(R.drawable.dice2)
            3 -> img.setImageResource(R.drawable.dice3)
            4 -> img.setImageResource(R.drawable.dice4)
            5 -> img.setImageResource(R.drawable.dice5)
            6 -> img.setImageResource(R.drawable.dice6)
        }

    }
}
