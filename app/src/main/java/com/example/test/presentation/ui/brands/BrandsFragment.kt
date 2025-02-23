package com.example.test.presentation.ui.brands

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.FragmentBrandsBinding
import com.example.test.domain.models.BrandsItem
import com.example.test.presentation.utils.getNavOptionAnimation
import com.example.test.presentation.utils.hideKeyboard
import com.example.test.presentation.utils.navigateToModelScreenUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class BrandsFragment : Fragment(R.layout.fragment_brands) {

    private lateinit var binding: FragmentBrandsBinding
    private val viewModel: BrandViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrandsBinding.bind(view)

        val brandAdapter = createAdapter()
        handleBrandsState(brandAdapter)
        searchForBrand(brandAdapter)
        hideKeyboardWhenScroll()


    }


    private fun hideKeyboardWhenScroll() {
        binding.brandRec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                requireActivity().hideKeyboard()

            }
        })
    }

    private fun createAdapter() = BrandAdapter { brandItem ->
        navigateToModelsScreen(brandItem)
    }

    private fun navigateToModelsScreen(brandItem: BrandsItem) {
        val encodedJson = URLEncoder.encode(brandItem.image, StandardCharsets.UTF_8.toString())
        val deepLinkUri = Uri.parse(navigateToModelScreenUri(brandItem.id, encodedJson))
        val navOptions = getNavOptionAnimation()
        findNavController().navigate(deepLinkUri, navOptions)
    }

    private fun handleBrandsState(brandAdapter: BrandAdapter) {
        lifecycleScope.launch {
            viewModel.brandStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when {
                        state.loading -> { }
                        state.data.isNotEmpty() ->
                            setUpAdapter(state, brandAdapter = brandAdapter)
                        state.unKnownError.isNotEmpty() ->
                            Toast.makeText(requireContext(), state.unKnownError, Toast.LENGTH_SHORT).show()
                        state.unauthorizedError ->
                            Toast.makeText(requireContext(), "unauthorizedError", Toast.LENGTH_SHORT).show()
                        state.notFoundError -> Toast.makeText(requireContext(), "notFoundError", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun setUpAdapter(state: BrandsState,brandAdapter:BrandAdapter) {
        viewModel.brandList = state.data.toMutableList()
        brandAdapter.updateList(state.data)
        binding.brandRec.adapter = brandAdapter
    }

    private fun searchForBrand(brandAdapter:BrandAdapter) {
        binding.brandEditSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val l = viewModel.brandList.filter { brandsData ->
                    brandsData.name.contains(s.toString(), ignoreCase = true)
                }
                brandAdapter.updateList(l)
            }
        })
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