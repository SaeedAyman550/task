package com.example.test.presentation.ui.vehicles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.test.R
import com.example.test.databinding.FragmentVehiclesBinding
import com.example.test.presentation.utils.loadGlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class VehiclesFragment : Fragment(R.layout.fragment_vehicles) {

    private lateinit var binding: FragmentVehiclesBinding
    private val viewModel: VehiclesViewModel by viewModels()
    private val modelArgs: VehiclesFragmentArgs by navArgs()
    private val adapter=VehiclesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentVehiclesBinding.bind(view)

        loadParentBrandImage()
        handleVehiclesState()
        binding.vehiclesRec.adapter=adapter

    }

    private fun handleVehiclesState() {
        lifecycleScope.launch {
            viewModel
                .vehiclesStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when {
                        state.loading -> {}
                        state.data.isNotEmpty() -> adapter.updateList(state.data)
                        state.error.isNotEmpty() -> {}
                    }
                }

        }
    }

    private fun loadParentBrandImage() {
        val decodeUri = URLDecoder.decode(modelArgs.imageUri, StandardCharsets.UTF_8.toString())
        loadGlideImage(decodeUri, binding.parentBrandImage)
    }

    override fun onPause() {
        super.onPause()
        viewModel.nestedScrollPosition= binding.nestedScroll.scrollY
    }

    override fun onResume() {
        super.onResume()
        binding.nestedScroll.post {
            binding.nestedScroll.scrollTo(0, viewModel.nestedScrollPosition)
        }
    }

}