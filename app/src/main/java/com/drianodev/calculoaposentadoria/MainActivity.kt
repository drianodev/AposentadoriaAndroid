package com.drianodev.calculoaposentadoria

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : Activity() {

    private lateinit var spn_sexo: Spinner
    private lateinit var txt_idade: EditText
    private lateinit var btn_calcular: Button
    private lateinit var txt_resultado: TextView
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        spn_sexo = findViewById(R.id.spn_sexo)
        txt_idade = findViewById(R.id.txt_idade)
        btn_calcular = findViewById(R.id.btn_calcular)
        txt_resultado = findViewById(R.id.txt_resultado)
        handler = Handler(Looper.getMainLooper())

        spn_sexo.adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("selecione um sexo", "masculino", "feminino")
        )

        btn_calcular.setOnClickListener {
            val sexo = spn_sexo.selectedItem as String
            val idadeText = txt_idade.text.toString()

            if (idadeText.isEmpty() && sexo == "selecione um sexo") {
                txt_resultado.text = "Selecione um sexo e digite sua idade."
                txt_resultado.setTextColor(Color.RED)
                handler.postDelayed({
                    txt_resultado.text = ""
                }, 2000)
                Toast.makeText(this, "Por favor, selecione um sexo e digite sua idade.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (idadeText.isEmpty()) {
                txt_resultado.text = "Digite sua idade."
                txt_resultado.setTextColor(Color.RED)
                handler.postDelayed({
                    txt_resultado.text = ""
                }, 2000)
                Toast.makeText(this, "Por favor, digite sua idade.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (sexo == "selecione um sexo") {
                txt_resultado.text = "Selecione um sexo."
                txt_resultado.setTextColor(Color.RED)
                handler.postDelayed({
                    txt_resultado.text = ""
                }, 2000)
                Toast.makeText(this, "Por favor, selecione um sexo.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val idade = idadeText.toInt()
                var resultado = 0

                if (sexo == "masculino") {
                    resultado = 65 - idade
                } else if (sexo == "feminino") {
                    resultado = 60 - idade
                }

                if (resultado <= 0) {
                    txt_resultado.setTextColor(Color.BLACK)
                    txt_resultado.text = "Terror do INSS."
                } else {
                    txt_resultado.setTextColor(Color.BLACK)
                    txt_resultado.text = "Faltam $resultado anos para você se aposentar."
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Por favor, insira uma idade válida.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}