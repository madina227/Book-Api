package uz.gita.bookapi.presentation.users

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookapi.R
import uz.gita.bookapi.databinding.ScreenUsersBinding
import uz.gita.bookapi.presentation.users.vm.UsersViewModel
import uz.gita.bookapi.presentation.viewModels.UsersViewModelImpl

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 12/12/2022, Monday, 15:21
 **/
@AndroidEntryPoint
class UsersScreen : Fragment(R.layout.screen_users) {
    private val viewBinding: ScreenUsersBinding by viewBinding(ScreenUsersBinding::bind)

    private val viewModel: UsersViewModel by viewModels<UsersViewModelImpl>()

    private val adapter by lazy { UsersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recyclerView.adapter = adapter
        Log.d("BRRR", "onViewCreated")
        viewModel.getAllUsers.onEach {
            adapter.submitList(it)
            Log.d("BRRR", "submitted it -> $it")

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        Log.d("BRRR", "neytral")
    }

}