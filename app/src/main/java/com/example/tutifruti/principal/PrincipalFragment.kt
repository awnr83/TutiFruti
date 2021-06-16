package com.example.tutifruti.principal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.tutifruti.R
import com.example.tutifruti.databinding.FragmentPrincipalBinding

class PrincipalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentPrincipalBinding= DataBindingUtil.inflate(inflater,
            R.layout.fragment_principal,container, false)

        binding.buttonJugar.setOnClickListener {  findNavController(this).navigate(PrincipalFragmentDirections.actionPrincipalFragmentToGameFragment()) }

        return binding.root
    }
}