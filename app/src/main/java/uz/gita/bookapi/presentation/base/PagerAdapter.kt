package uz.gita.bookapi.presentation.screen.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.bookapi.presentation.book.BooksScreen
import uz.gita.bookapi.presentation.users.UsersScreen

/**
Created: Bekzod Yusupov
Project: book api
Date: 2022/12/06
Time: 16:45
 */
class PagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BooksScreen()
            else -> UsersScreen()
        }
    }
}