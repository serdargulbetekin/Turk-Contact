package com.example.turk_contacts.contact

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.turk_contacts.api.ContactApi
import retrofit2.HttpException
import java.io.IOException

class ContactPagingSource constructor(private val contactApi: ContactApi) :
    PagingSource<Int, ContactItem>() {
    override fun getRefreshKey(state: PagingState<Int, ContactItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContactItem> {
        val page = params.key ?: PAGE_INDEX

        return try {
            val response = contactApi.getContacts(page, params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val PAGE_INDEX = 1
    }
}