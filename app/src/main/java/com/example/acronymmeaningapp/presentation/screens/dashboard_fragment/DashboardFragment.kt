package com.example.acronymmeaningapp.presentation.screens.dashboard_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.acronymmeaningapp.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val dashboardFragmentViewModel by viewModels<DashboardFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.viewModel = dashboardFragmentViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
