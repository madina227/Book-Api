package uz.gita.bookapi.presentation.users.vm

import kotlinx.coroutines.flow.Flow
import uz.gita.bookapi.data.remote.dto.user.response.AllUsers

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 13/12/2022, Tuesday, 12:13
 **/
interface UsersViewModel {

    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message: Flow<Boolean>

    val getAllUsers: Flow<AllUsers>
}