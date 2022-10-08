package com.example.kmmnoteapp.android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kmmnoteapp.DatabaseDriverFactory
import com.example.kmmnoteapp.HttpClientFactory
import com.example.kmmnoteapp.android.databinding.FragmentHomeBinding
import com.example.kmmnoteapp.android.ui.MyViewModelFactory
import com.example.kmmnoteapp.android.ui.adapter.ImageAdapter
import com.example.kmmnoteapp.android.ui.home.HomeViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    private val imageAdapter = ImageAdapter {
        Log.d("asd", "select image at position: $it")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = ImageRepositoryImpl(
            databaseDriverFactory = DatabaseDriverFactory(requireContext()),
            httpClientFactory = HttpClientFactory()
        )

        val viewModelFactory = MyViewModelFactory(repo)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[HomeViewModel::class.java]

        initView()
        initEvent()
        observeViewModel()
        viewModel.loadRandomImage()
    }

    private fun initView() {
        binding.apply {
            recyclerView.adapter = imageAdapter
        }
    }

    private fun initEvent() {
        binding.favoriteButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.imageList.observe(viewLifecycleOwner) {
            Log.d("asd", "observed data: size = ${it.size}")
            imageAdapter.loadData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}