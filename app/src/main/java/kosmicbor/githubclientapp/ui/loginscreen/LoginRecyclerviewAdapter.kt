package kosmicbor.githubclientapp.ui.loginscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.databinding.FragmentLoginRecyclerviewItemBinding
import kosmicbor.githubclientapp.domain.User
import kosmicbor.githubclientapp.utils.DiffUtilCallback

class LoginRecyclerviewAdapter : RecyclerView.Adapter<LoginRecyclerviewAdapter.LoginViewHolder>() {

    private val usersList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginViewHolder {

        val itemBinding = FragmentLoginRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoginViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) {
        holder.apply {
            userAvatar.load(usersList[position].avatarUrl)
            userName.text = usersList[position].name
        }
    }

    override fun getItemCount(): Int = usersList.size


    fun fillUsersList(newUsersList: List<User>) {
        val diffUtil = DiffUtilCallback(usersList, newUsersList)
        val result = DiffUtil.calculateDiff(diffUtil)
        usersList.clear()
        usersList.addAll(newUsersList)
        result.dispatchUpdatesTo(this)
    }

    inner class LoginViewHolder(binding: FragmentLoginRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val userAvatar: ShapeableImageView = binding.fragmentLoginUserAvatar
        val userName: MaterialTextView = binding.fragmentLoginUserName
    }
}