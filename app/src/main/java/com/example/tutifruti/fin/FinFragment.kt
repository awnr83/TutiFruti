package com.example.tutifruti.fin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.tutifruti.R
import com.example.tutifruti.databinding.FragmentFinBinding

class FinFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentFinBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_fin, container, false)

        binding.buttonSalir.setOnClickListener { findNavController(this).navigate(FinFragmentDirections.actionFinFragmentToPrincipalFragment()) }
        return binding.root
    }
}