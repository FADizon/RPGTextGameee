package ph.edu.auf.rpgtextgame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroupActions: RadioGroup
    private lateinit var buttonChooseAction: Button
    private lateinit var textViewPlayerAction: TextView
    private lateinit var textViewEnemyAction: TextView
    private lateinit var textViewPlayerHealth: TextView
    private lateinit var textViewEnemyHealth: TextView
    private lateinit var buttonRestart: Button

    private var playerHealth = 100
    private var enemyHealth = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroupActions = findViewById(R.id.radioGroupActions)
        buttonChooseAction = findViewById(R.id.buttonChooseAction)
        textViewPlayerAction = findViewById(R.id.textViewPlayerAction)
        textViewEnemyAction = findViewById(R.id.textViewEnemyAction)
        textViewPlayerHealth = findViewById(R.id.textViewPlayerHealth)
        textViewEnemyHealth = findViewById(R.id.textViewEnemyHealth)
        buttonRestart = findViewById(R.id.buttonRestart)

        buttonChooseAction.setOnClickListener { chooseAction() }
        buttonRestart.setOnClickListener { restartGame() }
    }

    private fun chooseAction() {
        val selectedActionId = radioGroupActions.checkedRadioButtonId
        val selectedAction = findViewById<RadioButton>(selectedActionId)?.text.toString()

        textViewPlayerAction.text = "Player Choose Action: $selectedAction"
        performPlayerAction(selectedAction)
        performEnemyAction()

        updateHealthTextViews()

        if (playerHealth <= 0 || enemyHealth <= 0) {
            endGame()
        }
    }

    private fun performPlayerAction(action: String) {
        when (action) {
            "Attack" -> enemyHealth -= 20
            "Defend" -> enemyHealth -= 10
            "Heal" -> playerHealth += 10
        }
    }

    private fun performEnemyAction() {
        val actions = arrayOf("Attack", "Defend", "Heal")
        val randomIndex = (0 until actions.size).random()
        val selectedAction = actions[randomIndex]

        textViewEnemyAction.text = "Enemy Choose Action: $selectedAction"

        when (selectedAction) {
            "Attack" -> playerHealth -= 20
            "Defend" -> playerHealth -= 10
            "Heal" -> enemyHealth += 10
        }
    }

    private fun updateHealthTextViews() {
        textViewPlayerHealth.text = "Player Health: $playerHealth"
        textViewEnemyHealth.text = "Enemy Health: $enemyHealth"
    }

    private fun endGame() {
        buttonChooseAction.isEnabled = false
        buttonRestart.visibility = View.VISIBLE

        if (playerHealth <= 0) {
            textViewPlayerAction.text = "Game Over! You lost."
        } else {
            textViewPlayerAction.text = "Game Over! You won."
        }
    }

    private fun restartGame() {
        playerHealth = 100
        enemyHealth = 100

        textViewPlayerAction.text = "Player Choose Action"
        textViewEnemyAction.text = "Enemy Choose Action"

        updateHealthTextViews()

        buttonChooseAction.isEnabled = true
        buttonRestart.visibility = View.GONE
    }
}
