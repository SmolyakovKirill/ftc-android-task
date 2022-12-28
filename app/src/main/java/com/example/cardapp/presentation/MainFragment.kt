package com.example.cardapp.presentation

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardapp.R
import com.example.cardapp.data.repository.Repository
import com.example.cardapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(this@MainFragment)[MainViewModel::class.java]
        binding = FragmentMainBinding.inflate(inflater, container, false)
        init()
        viewModel.myCardItem.observe(viewLifecycleOwner) { list ->
            binding.textView3.text = list.body()?.brand.toString()
            binding.textView4.text = list.body()?.country?.name.toString()
            binding.textView5.text = list.body()?.scheme.toString()
        }
        return binding.root
    }

    private fun init() = with(binding){
        val viewModel = ViewModelProvider(this@MainFragment)[MainViewModel::class.java]
        button.setOnClickListener{
            viewModel.getCardInformation(editTextTextPersonName.text.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}