package uz.gita.bookapi.presentation.splash.vm

import kotlinx.coroutines.flow.Flow


/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 13/12/2022, Tuesday, 12:54
 **/
interface SplashViewModel {
    val isFirstFlow: Flow<Boolean>
}