package uz.gita.bookapi.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.bookapi.data.remote.dto.user.response.GetUsersResponse
import uz.gita.bookapi.databinding.ItemUserBinding

/**
 * @author : Madina Agzamova
 * @mailto : madina.agzamova.dev@gmail.com
 * @created : 13/12/2022, Tuesday, 12:23
 **/
class UsersAdapter : ListAdapter<GetUsersResponse, UsersAdapter.UserViewHolder>(UserCallBack) {

    object UserCallBack : DiffUtil.ItemCallback<GetUsersResponse>() {
        override fun areItemsTheSame(
            oldItem: GetUsersResponse,
            newItem: GetUsersResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetUsersResponse,
            newItem: GetUsersResponse
        ): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.firstName == newItem.firstName &&
                    oldItem.lastName == newItem.lastName
        }

    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.tvName.text = item.firstName
            binding.tvLastname.text = item.lastName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }
}