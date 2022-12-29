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
            binding.tvSchemeValue.text = list.body()?.scheme
            binding.tvTypeValue.text = list.body()?.type
            binding.tvBankName.text = list.body()?.bank?.name
            binding.tvBankSite.text = list.body()?.bank?.url
            binding.tvBankNumber.text = list.body()?.bank?.phone
            binding.tvBrandValue.text = list.body()?.brand
            binding.tvPrepaidValue.text = list.body()?.brand
            binding.tvLengthValue.text = list.body()?.number?.length.toString()
            binding.tvLunhValue.text = list.body()?.number?.luhn.toString()
            binding.tvCountryValue.text = list.body()?.country?.name
            binding.tvEmoji.text = list.body()?.country?.emoji
            binding.tvLatitude.text = list.body()?.country?.latitude.toString()
            binding.tvLongitude.text = list.body()?.country?.longitude.toString()
        }
        return binding.root
    }

    private fun init() = with(binding){
        val viewModel = ViewModelProvider(this@MainFragment)[MainViewModel::class.java]
        btGetCardInform.setOnClickListener{
            if(etCardNumber.text.isNotEmpty())
                viewModel.getCardInformation(etCardNumber.text.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}