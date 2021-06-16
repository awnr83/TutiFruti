package com.example.tutifruti.game

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

//variables
    private lateinit var listaLetras: MutableList<String>
//encapsulamiento LIVE DATA
    //letra
    private val _letra= MutableLiveData<String>()
    val letra: LiveData<String>
        get()=_letra
    //evento termino
    private val _eventFin= MutableLiveData<Boolean>()
    val eventFin: LiveData<Boolean>
        get()=_eventFin

    init {
        _eventFin.value=false //se inica false porque todavia no termino
        resetLista()
        siguienteLetra()
    }
    private fun resetLista(){
        listaLetras= mutableListOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","W","X","Y","Z")
        listaLetras.shuffle()
    }

    fun siguienteLetra(){
        if(listaLetras.size>0)
            _letra.value= listaLetras.removeAt(0)
        else{
            _eventFin.value=true //como termino se pone en true
        }
    }

    fun terminoCompleto(){
        _eventFin.value= false //funciona como un candado ya no entra
    }
}