package com.example.tutifruti.game

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

//variables
    private lateinit var listaLetras: MutableList<String>
    var letra= MutableLiveData<String>()

    init {
        resetLista()
        siguienteLetra()
    }
    private fun resetLista(){
        listaLetras= mutableListOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","W","X","Y","Z")
        listaLetras.shuffle()
    }

    fun siguienteLetra(){
        if(listaLetras.size>0)
            letra.value= listaLetras.removeAt(0)
//        else
//            ultima letra
    }
}