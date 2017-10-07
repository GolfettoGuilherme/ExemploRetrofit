package com.example.ggolfetto.exemploconsumowebservicerest

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.ggolfetto.exemploconsumowebservicerest.model.Endereco
import com.example.ggolfetto.exemploconsumowebservicerest.model.PessoaObj
import com.example.ggolfetto.exemploconsumowebservicerest.network.APIClient
import com.example.ggolfetto.exemploconsumowebservicerest.utils.Utils

import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

class MainActivity : AppCompatActivity() {

    private var load: ProgressDialog? = null
    private var callbackRetorno: Callback<Endereco>? = null

    private var txtCep: EditText? = null
    private var txvEndereceo: TextView? = null
    private var txvComplemento: TextView? = null
    private var txvBairro: TextView? = null
    private var txvUf: TextView? = null
    private var btnBuscar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCep = findViewById(R.id.txtCep) as EditText
        txvEndereceo = findViewById(R.id.txvEndereceo) as TextView
        txvComplemento = findViewById(R.id.txvComplemento) as TextView
        txvBairro = findViewById(R.id.txvBairro) as TextView
        txvUf = findViewById(R.id.txvUf) as TextView
        btnBuscar = findViewById(R.id.btnBuscar) as Button


        btnBuscar!!.setOnClickListener {
            configureCallbackRetorno()
            val cep = txtCep!!.text.toString()
            APIClient().restService.getEndereco(cep, callbackRetorno)
            load = ProgressDialog.show(this@MainActivity, "Por favor Aguarde...", "Recuperando informações")
        }


    }

    private fun configureCallbackRetorno() {
        callbackRetorno = object : Callback<Endereco> {
            override fun success(endereco: Endereco, response: Response) {
                txvEndereceo!!.text = endereco.logradouro
                txvComplemento!!.text = endereco.complemento
                txvBairro!!.text = endereco.bairro
                txvUf!!.text = endereco.uf
                load!!.dismiss()
            }

            override fun failure(error: RetrofitError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_LONG).show()
                load!!.dismiss()
            }
        }
    }
}















