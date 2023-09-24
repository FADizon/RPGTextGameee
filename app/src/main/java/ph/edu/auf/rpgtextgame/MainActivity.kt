package ph.edu.auf.rpgtextgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var gameStatus: TextView
    private lateinit var actionGroup: RadioButton
    private lateinit var performActionBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        gameStatus = findViewById(R.id.gameStatus)
        actionGroup = findViewById(R.id.actionGroup)
        performActionBtn = findViewById(R.id.performActionBtn)


        performActionBtn.setOnClickListener {
            val selectedActionId = actionGroup.id
            val selectedAction = findViewById<RadioButton>(selectedActionId)

            if (selectedAction != null) {
                val actionText = selectedAction.text.toString()
                performAction(actionText)
            } else {
                Toast.makeText(applicationContext, "Please select an action!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun performAction(action: String) {
        val result = "Performed $action action. Game logic goes here."
        gameStatus.text = result
    }
}