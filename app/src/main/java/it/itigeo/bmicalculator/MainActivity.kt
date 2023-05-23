package it.itigeo.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.widget.*
import androidx.annotation.VisibleForTesting
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val immagineUomo= findViewById<ImageView>(R.id.immagine_ragazzo)
            val immagineDonna = findViewById<ImageView>(R.id.immagine_donna)
            val peso = findViewById<EditText>(R.id.valorePeso)
            val altezza = findViewById<EditText>(R.id.valoreAltezza)
            val bottoneCalcola = findViewById<Button>(R.id.bottoneCalcola)
            val bmi = findViewById<TextView>(R.id.bmi)
            val statoBmi = findViewById<TextView>(R.id.statoBmi)
            val bmiView = findViewById<LinearLayout>(R.id.bmiView)
            val nuovoCalcolo = findViewById<TextView>(R.id.nuovoCalcolo)

            immagineUomo.setOnClickListener {
                immagineUomo.setImageResource(R.drawable.uomo)
                immagineDonna.setImageResource(R.drawable.donna2)
            }

            immagineDonna.setOnClickListener {
                immagineUomo.setImageResource(R.drawable.uomo)
                immagineDonna.setImageResource(R.drawable.donna2)
            }

            bottoneCalcola.setOnClickListener {
                var valorePeso = 0.0
                var valoreAltezza = 0.0
                if (peso.text.toString().isNotEmpty()) {
                    valorePeso = peso.text.toString().toDouble()
                }
                if (altezza.text.toString().isNotEmpty()) {
                    valoreAltezza = (altezza.text.toString().toDouble() / 100)
                }
                if (valorePeso > 0.0 && valoreAltezza > 0.0) {
                    val bmiValue = String.format("%.2f", valorePeso / valoreAltezza.pow(2))
                    bmi.text = bmiValue
                    statoBmi.text = statoBmi(valorePeso / valoreAltezza.pow(2))
                    bmiView.visibility = View.VISIBLE
                    bottoneCalcola.visibility = View.GONE
                }else Toast.makeText(this, "ATTENZIONE, DATI NON INSERITI CORRETTAMENTE", Toast.LENGTH_LONG).show()
            }

            nuovoCalcolo.setOnClickListener {
                bmiView.visibility = View.GONE
                bottoneCalcola.visibility = View.VISIBLE
                peso.text.clear()
                altezza.text.clear()
                peso.requestFocus()
            }
        }


        private fun statoBmi(bmi:Double): String{
        lateinit var statoBmi: String
        if(bmi<18.5)
            statoBmi = "SOTTOPESO!!!"
        else if(bmi>=18.5 && bmi<25)
            statoBmi = "NORMOPESO"
        else if(bmi>=18.5 && bmi<25)
            statoBmi = "NORMOPESO"
        else if(bmi>=25 && bmi<30)
            statoBmi = "SOVRAPPESO!"
        else if(bmi>30)
            statoBmi = "OBESO!!!"
        return statoBmi
    }

}