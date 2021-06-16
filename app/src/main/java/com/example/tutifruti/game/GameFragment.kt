package com.example.tutifruti.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.tutifruti.R
import com.example.tutifruti.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel= ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel= viewModel
        binding.lifecycleOwner=this

        viewModel.temp.observe(viewLifecycleOwner, Observer { newTemp->
            binding.textViewTiempo.text= DateUtils.formatElapsedTime(newTemp)
            binding.invalidateAll()
        })

        viewModel.eventVibracion.observe(viewLifecycleOwner, Observer { vib->
            if(vib != GameViewModel.Vibracion.NO_BUZ){
                vibracion(vib.parametro)
                viewModel.terminoVibracion()
            }
        })

        viewModel.eventFin.observe(viewLifecycleOwner, Observer {termino->
            if(termino) { //si esta en true termino
                findNavController(this).navigate(GameFragmentDirections.actionGameFragmentToFinFragment())
                viewModel.terminoCompleto() //con esto se termina de cerrar
            }
        })
        return binding.root
    }
    private fun vibracion(parametro: LongArray){
        val vib= activity?.getSystemService<Vibrator>()
        vib.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                vib?.vibrate(VibrationEffect.createWaveform(parametro, -1))
             else
                vib?.vibrate(parametro, -1)
        }
    }
}