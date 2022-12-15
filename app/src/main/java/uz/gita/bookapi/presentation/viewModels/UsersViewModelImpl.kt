package uz.gita.bookapi.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.bookapi.data.model.ResultData
import uz.gita.bookapi.data.remote.dto.user.response.AllUsers
import uz.gita.bookapi.domain.repository.UserRepository
import uz.gita.bookapi.presentation.users.vm.UsersViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModelImpl @Inject constructor(
    private val repository: UserRepository
) : UsersViewModel, ViewModel() {
    override val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val errorMsg =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)


    override val getAllUsers =
        MutableSharedFlow<AllUsers>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    init {
        viewModelScope.launch {
            repository.getUsers().collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> it.data?.let { it1 -> getAllUsers.emit(it1) }
                }
            }
        }
    }
}