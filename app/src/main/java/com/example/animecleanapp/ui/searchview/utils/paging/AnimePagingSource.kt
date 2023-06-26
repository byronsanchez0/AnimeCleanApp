package com.example.animecleanapp.ui.searchview.utils.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.usecases.GetAnimeListUseCase
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import kotlinx.coroutines.flow.MutableStateFlow

class AnimePagingSource(
    private val getAnimeUseCase: GetAnimeListUseCase,
    private val search: String? = null,
    private val sort: List<AnimeSort>,
    private val type: AnimeType,
    private val loadingUI: MutableStateFlow<Boolean>
) : PagingSource<Int, AnimeModel>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeModel> {
        loadingUI.emit(true)
        return try {
            val page = params.key ?: 1
            val response = getAnimeUseCase.execute(
                page = page,
                search = search,
                sort = sort,
                type = type
            )
            loadingUI.emit(false)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
