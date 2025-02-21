package com.example.test.presentation.ui.brands

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
import com.example.test.R
import com.example.test.databinding.FragmentBrandsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BrandsFragment : Fragment(R.layout.fragment_brands) {

    private lateinit var binding: FragmentBrandsBinding
    private val viewModel: BrandViewModel by viewModels()
    private val brandAdapter = BrandAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrandsBinding.bind(view)

        viewModel.getBrands()
        handleBrandsState()
        searchForBrand()


    }

    private fun handleBrandsState() {
        lifecycleScope.launch {

            viewModel.brandStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when {
                        state.loading -> {
                            //handle loading
                        }

                        state.data.isNotEmpty() -> setUpAdapter(state)

                        state.unKnownError.isNotEmpty() ->
                            Toast.makeText(requireContext(), state.unKnownError, Toast.LENGTH_SHORT)
                                .show()

                        state.unauthorizedError ->
                            Toast.makeText(
                                requireContext(),
                                "unauthorizedError",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                        state.notFoundError ->
                            Toast.makeText(
                                requireContext(),
                                "notFoundError",
                                Toast.LENGTH_SHORT)
                                .show()
                    }
                }
        }
    }

    private fun setUpAdapter(state: BrandsState) {
        viewModel.brandList = state.data.toMutableList()
        brandAdapter.updateList(state.data)
        binding.brandRec.adapter = brandAdapter
    }

    private fun searchForBrand() {
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


}