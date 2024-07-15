package com.alam.tictaktoe

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Button>
    private var currentPlayer: String = "X"
    private var gameOver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridLayout = findViewById<GridLayout>(R.id.grid_layout)
        buttons = Array(9) { i ->
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener { buttonClick(button) }
            button
        }

        findViewById<Button>(R.id.reset_button).setOnClickListener { resetGame() }
    }

    private fun buttonClick(button: Button) {
        if (!gameOver && button.text.isEmpty()) {
            button.text = currentPlayer
            checkWin()
            currentPlayer = if (currentPlayer == "X") "O" else "X"
        }
    }

    private fun checkWin() {
        val winningCombinations = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        for (combination in winningCombinations) {
            val button1 = buttons[combination[0]]
            val button2 = buttons[combination[1]]
            val button3 = buttons[combination[2]]

            if (button1.text == button2.text && button2.text == button3.text && button1.text.isNotEmpty()) {
                gameOver = true
                button1.setBackgroundColor(resources.getColor(R.color.white))
                button2.setBackgroundColor(resources.getColor(R.color.white))
                button3.setBackgroundColor(resources.getColor(R.color.white))
                return
            }
        }
    }

    private fun resetGame() {
        gameOver = false
    }
}
