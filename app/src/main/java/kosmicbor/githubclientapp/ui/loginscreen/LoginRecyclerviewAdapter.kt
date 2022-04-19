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
import kosmicbor.githubclientapp.domain.User
import kosmicbor.githubclientapp.ui.userprofilescreen.ProfileFragment
import kosmicbor.githubclientapp.utils.DiffUtilCallback


class LoginRecyclerviewAdapter : RecyclerView.Adapter<LoginRecyclerviewAdapter.LoginViewHolder>() {

    companion object {
        private const val USER_ID = "USER_ID"
    }

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
                val activity = it.context as AppCompatActivity
                openProfileFragment(usersList[position].id, activity)

            }
        }
    }

    private fun openProfileFragment(userId: Int, activity: AppCompatActivity) {

        val bundle = Bundle()

        bundle.putInt(USER_ID, userId)

        activity.supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.main_container, ProfileFragment.newInstance(bundle))
            .addToBackStack("ProfileScreen")
            .commit()
    }

    override fun getItemCount(): Int = usersList.size


//    fun fillUsersList(newUsersList: List<User>) {
//        val diffUtil = DiffUtilCallback(usersList, newUsersList)
//        val result = DiffUtil.calculateDiff(diffUtil)
//        usersList.clear()
//        usersList.addAll(newUsersList)
//        result.dispatchUpdatesTo(this)
//    }

    fun addUser(it: GithubUserEntity?) {
        if (it != null) {
            usersList.add(it)
        }
        notifyDataSetChanged()
    }

    inner class LoginViewHolder(binding: FragmentLoginRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val userAvatar: ShapeableImageView = binding.fragmentLoginUserAvatar
        val userName: MaterialTextView = binding.fragmentLoginUserName
        val item = binding.root
    }
}