package kosmicbor.githubclientapp.ui.loginscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import kosmicbor.githubclientapp.databinding.FragmentLoginRecyclerviewItemBinding
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.utils.LoginItemTouchHelperAdapter
import kosmicbor.githubclientapp.utils.LoginItemTouchHelperViewHolder


class LoginRecyclerviewAdapter(
    private val usersList: MutableList<GithubUser>,
    private val onItemClickCallback: (String) -> Unit,
    private val onItemRemoveCallback: (GithubUser) -> Unit
) :
    RecyclerView.Adapter<LoginRecyclerviewAdapter.LoginViewHolder>(), LoginItemTouchHelperAdapter {

    companion object {
        private const val ZERO_VAL = 0
    }

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
                onItemClickCallback(usersList[holder.bindingAdapterPosition].login)
            }
        }
    }

    override fun getItemCount(): Int = usersList.size

    fun addUser(newUser: GithubUser?) {
        if (newUser != null && !usersList.contains(newUser)) {
            usersList.add(newUser)
            notifyItemInserted(this.itemCount)
        }
    }

    override fun itemDismiss(position: Int) {
        removeItem(position)
    }

    private fun removeItem(position: Int) {
        val githubUser = usersList[position]
        usersList.removeAt(position)
        onItemRemoveCallback(githubUser)
        notifyItemRemoved(position)
    }

    inner class LoginViewHolder(binding: FragmentLoginRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root), LoginItemTouchHelperViewHolder {
        val userAvatar: ShapeableImageView = binding.loginUserAvatarImageView
        val userName: MaterialTextView = binding.loginUserNameTextView
        val item = binding.root

        override fun onItemClear() {
            itemView.setBackgroundColor(ZERO_VAL)
        }
    }
}