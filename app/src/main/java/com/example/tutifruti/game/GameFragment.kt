package com.example.tutifruti.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.tutifruti.R
import com.example.tutifruti.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    private lateinit var letras: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

//sufle de letra y se agrega una
        resetLista()
        siguienteLetra()

        binding.button.setOnClickListener {

            if(cantidadLetras()>0)
                siguienteLetra()
            else
                Toast.makeText(this.context, "Terminamos!!!", Toast.LENGTH_SHORT).show()
            binding.invalidateAll()
        }

        return binding.root
    }
    private fun resetLista(){
        letras= mutableListOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","W","X","Y","Z")
        letras.shuffle()
    }
    private fun siguienteLetra(){
        binding.textViewLetra.text=letras.removeAt(0)
    }
    private fun cantidadLetras():Int{
        return letras.size
    }
}