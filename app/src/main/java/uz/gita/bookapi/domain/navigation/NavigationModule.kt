package uz.gita.bookapi.domain.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun navigation(navigator: NavigationDispatcher): Navigator

    @Binds
    fun navigatorHandler(navigatorHandler: NavigationDispatcher): NavigationHandler
}