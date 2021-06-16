package com.example.tutifruti.game

import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//constantes para vibracion
private val PANICO_BUZ_P= longArrayOf(0,200)
private val NO_BUZ_P= longArrayOf(0)

class GameViewModel: ViewModel() {

enum class Vibracion(val parametro: LongArray){
    PANIC(PANICO_BUZ_P),
    NO_BUZ(NO_BUZ_P)
}

//temporizador
    //valores para el temporizador
    companion object{
        private val INICIO=    0L
        private val SEG=    1000L
        private val PANICO_SEG= 10000L
        private val TEMP=  60000L
    }
    private lateinit var temporizador:CountDownTimer

//variables
    private lateinit var listaLetras: MutableList<String>

//encapsulamiento LIVE DATA
    //letra
    private val _letra= MutableLiveData<String>()
    val letra: LiveData<String>
        get()=_letra
    //temporizador
    private val _temp= MutableLiveData<Long>()
    val temp: LiveData<Long>
        get()=_temp
    //vibracion
    private val _eventVibracion= MutableLiveData<Vibracion>()
    val eventVibracion: LiveData<Vibracion>
        get()=_eventVibracion
    //evento termino
    private val _eventFin= MutableLiveData<Boolean>()
    val eventFin: LiveData<Boolean>
        get()=_eventFin

    init {
        _eventFin.value=false //se inica false porque todavia no termino
        iniciarTemporizador()
        resetLista()
        siguienteLetra()
    }
    private fun resetLista(){
        listaLetras= mutableListOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","W","X","Y","Z")
        listaLetras.shuffle()
    }
    private fun iniciarTemporizador(){
        temporizador= object:CountDownTimer(TEMP, SEG) {
            override fun onTick(millisUntilFinished: Long) {
                if(millisUntilFinished <= PANICO_SEG) {         //solamente vibra cuando llega al panico
                    _eventVibracion.value= Vibracion.PANIC
                }
                _temp.value= (millisUntilFinished / SEG)
            }
            override fun onFinish() {
                _temp.value= INICIO
            }
        }
        temporizador.start()
    }
    fun siguienteLetra(){
        if(listaLetras.size>0) {
            iniciarTemporizador()
            _letra.value = listaLetras.removeAt(0)
        }else{
            _eventFin.value=true //como termino se pone en true
        }
    }
    fun terminoCompleto(){
        _eventFin.value= false //funciona como un candado ya no entra
    }
    fun terminoVibracion(){
        _eventVibracion.value= Vibracion.NO_BUZ
    }
}