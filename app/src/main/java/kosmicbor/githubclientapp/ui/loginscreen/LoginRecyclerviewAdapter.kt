package kosmicbor.githubclientapp.ui.loginscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.databinding.FragmentLoginRecyclerviewItemBinding
import kosmicbor.githubclientapp.domain.GithubUserEntity
import kosmicbor.githubclientapp.utils.DiffUtilCallback


class LoginRecyclerviewAdapter(private val onItemClickCallback: (String) -> Unit) :
    RecyclerView.Adapter<LoginRecyclerviewAdapter.LoginViewHolder>() {

    private val usersList = mutableListOf<GithubUserEntity>()

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
            userName.text = usersList[position].login
            item.setOnClickListener {
                onItemClickCallback(usersList[position].login)
            }
        }
    }

    private fun openProfileFragment(userLogin: String, activity: AppCompatActivity) {


    }

    override fun getItemCount(): Int = usersList.size

    fun addUser(it: GithubUserEntity?) {
        if (it != null) {
            usersList.add(it)
        }
        notifyItemInserted(this.itemCount)
    }

    fun fillUsersList(newUsersList: List<GithubUserEntity>) {
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
        val item = binding.root
    }
}