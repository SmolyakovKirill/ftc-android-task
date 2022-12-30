package com.example.cardapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.cardapp.data.db.MainDb
import com.example.cardapp.data.db.RequestCard
import com.example.cardapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(this@MainFragment)[MainViewModel::class.java]
        val db = context?.let { MainDb.getDb(it) }
        binding = FragmentMainBinding.inflate(inflater, container, false)
        db?.getDao()?.getAllRequests()?.asLiveData()?.observe(viewLifecycleOwner){ list ->
            binding.tvHistory.text = ""
            list.forEach {
                val text = "Scheme: ${it.scheme} Bank: ${it.bank}\n"
                binding.tvHistory.append(text)
            }
        }
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
            binding.tvLatitudeValue.text = list.body()?.country?.latitude.toString()
            binding.tvLongitudeValue.text = list.body()?.country?.longitude.toString()
            val request = RequestCard(null,
                binding.tvSchemeValue.text.toString(),
                binding.tvTypeValue.text.toString(),
                binding.tvCountryValue.text.toString(),
                binding.tvBankName.text.toString(),
                binding.tvPrepaidValue.text.toString())
            Thread{
                if (db?.getDao()?.getTableSize()!! > 15){
                    db?.getDao()?.deleteLastRequest()
                }
                db?.getDao()?.insertItem(request)
            }.start()
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