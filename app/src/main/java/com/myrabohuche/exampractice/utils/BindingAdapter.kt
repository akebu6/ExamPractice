package com.myrabohuche.exampractice.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myrabohuche.exampractice.model.Book
import com.myrabohuche.exampractice.ui.fragment.library.LibraryAdapter

@BindingAdapter("listData")
fun bindBooksRecyclerView(booksRecyclerView: RecyclerView, data: List<Book>?) {
    val adapter = booksRecyclerView.adapter as LibraryAdapter
    adapter.submitList(data)

}