package com.example.test.domain.paging_sourse

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test.domain.models.ModelsData
import com.example.test.domain.repository.TestRepo

class ModelsPagingSource(
    private val repository: TestRepo,
    private val brandId: Int,
) : PagingSource<Int, ModelsData>() {
    override fun getRefreshKey(state: PagingState<Int, ModelsData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ModelsData> {
        return try {
            val page = params.key ?: 1
            val response = repository.getModels(brand = brandId,page= page)

            LoadResult.Page(
                data = response.data?.data?: emptyList(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data?.data.isNullOrEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}