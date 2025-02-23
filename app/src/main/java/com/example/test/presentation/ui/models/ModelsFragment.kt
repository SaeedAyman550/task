package com.example.test.presentation.ui.models

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test.R
import com.example.test.databinding.FragmentModelsBinding
import com.example.test.domain.models.ModelsItem
import com.example.test.presentation.utils.LoadingStateAdapter
import com.example.test.presentation.utils.getNavOptionAnimation
import com.example.test.presentation.utils.loadGlideImage
import com.example.test.presentation.utils.navigateToVehiclesScreenUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class ModelsFragment : Fragment(R.layout.fragment_models) {

    private lateinit var binding: FragmentModelsBinding
    private val viewModel: ModelsViewModel by viewModels()
    private val modelAdapter = createModelAdapter()
    private val brandArgs: ModelsFragmentArgs by navArgs()
    private var isGridDesign = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentModelsBinding.bind(view)


        setRecycleAdapter()
        onClickGridIcon()
        handleModelsState()
        loadParentBrandImage()

    }

    private fun setRecycleAdapter() {
        val adapter =
            modelAdapter.withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter(),
                footer = LoadingStateAdapter()
            )
        binding.modelRec.adapter = adapter
    }

    private fun onClickGridIcon() {
        binding.gridIcon.setOnClickListener {
            if (isGridDesign) {
                binding.gridIcon.setImageResource(R.drawable.grid_icon)
                binding.modelRec.layoutManager = GridLayoutManager(requireContext(), 1)
            } else {
                binding.gridIcon.setImageResource(R.drawable.list_icon)
                binding.modelRec.layoutManager = GridLayoutManager(requireContext(), 2)
            }
            isGridDesign = !isGridDesign
            modelAdapter.changeScreen(isGridDesign)
        }
    }

    private fun loadParentBrandImage() {
        val decodeUri = URLDecoder.decode(brandArgs.imageUri, StandardCharsets.UTF_8.toString())
        loadGlideImage(decodeUri, binding.parentBrandImage)
    }

    private fun handleModelsState() {
        lifecycleScope.launch {
            viewModel.modelStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when {
                        state.loading -> {}
                        state.error.isNotEmpty() -> Toast.makeText(
                            requireContext(),
                            state.error,
                            Toast.LENGTH_SHORT
                        ).show()

                        else -> modelAdapter.submitData(state.data)
                    }
                }
        }
    }

    private fun createModelAdapter() = ModelAdapter(createComparator()) { modelItem ->
        navigateToVehicles(modelItem)
    }

    private fun navigateToVehicles(modelItem: ModelsItem) {
        val encodedJson =
            URLEncoder.encode(modelItem.parent_brand_image, StandardCharsets.UTF_8.toString())
        val deepLinkUri =
            Uri.parse(navigateToVehiclesScreenUri(brandArgs.brandId, modelItem.id, encodedJson))
        val navOptions = getNavOptionAnimation()
        findNavController().navigate(deepLinkUri, navOptions)
    }

    private fun createComparator() = object : DiffUtil.ItemCallback<ModelsItem>() {
        override fun areItemsTheSame(oldItem: ModelsItem, newItem: ModelsItem): Boolean {
            return oldItem.identification_attribute_id == newItem.identification_attribute_id
        }

        override fun areContentsTheSame(oldItem: ModelsItem, newItem: ModelsItem): Boolean {
            return oldItem.identification_attribute_id == newItem.identification_attribute_id
        }
    }
}