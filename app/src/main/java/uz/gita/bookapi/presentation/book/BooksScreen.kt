package uz.gita.bookapi.presentation.book

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookapi.R
import uz.gita.bookapi.databinding.ScreenBooksBinding
import uz.gita.bookapi.presentation.book.vm.BookViewModel
import uz.gita.bookapi.presentation.viewModels.BookViewModelImpl
import uz.gita.bookapi.utils.InitialDialog

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 12/12/2022, Monday, 15:21
 **/

@AndroidEntryPoint
class BooksScreen : Fragment(R.layout.screen_books) {
    private val viewBinding: ScreenBooksBinding by viewBinding(ScreenBooksBinding::bind)
    private val viewModel: BookViewModel by viewModels<BookViewModelImpl>()
    private val adapter by lazy { BooksAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = InitialDialog(requireContext())

        viewBinding.recyclerView.adapter = adapter

        viewModel.getAllBooks.onEach {
            Log.d("CHANGEF", "screen allBooks-> ${it}")
            adapter.submitList(it)

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.fab.setOnClickListener {
            dialog.show()
        }

        dialog.setOkListener {
            viewModel.postBook(it)
        }

        adapter.setEditClickListener {
            dialog.show()
            dialog.putBook(it)
        }

        adapter.setFavClickListener {
            viewModel.changeFavBook(it)
            Log.d("FAVV", "screen -> ${it.bookId}")
            Log.d("CHANGEF", "screen -> ${it.bookId}")
            Toast.makeText(requireContext(), "fav ${it.bookId}", Toast.LENGTH_SHORT).show()

        }

        adapter.setDeleteClickListener {
            Toast.makeText(requireContext(), "delete ${it.bookId}", Toast.LENGTH_SHORT).show()
            viewModel.deleteBook(it)
        }

        dialog.setPutOkListener {
            viewModel.putBook(it)
            Toast.makeText(requireContext(), "edit ${it.title}", Toast.LENGTH_SHORT).show()
        }


    }
}