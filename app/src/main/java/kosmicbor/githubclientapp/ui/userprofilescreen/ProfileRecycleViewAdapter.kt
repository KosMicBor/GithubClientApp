package kosmicbor.githubclientapp.ui.userprofilescreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kosmicbor.githubclientapp.databinding.FragmentProfileRecyclerviewItemBinding
import kosmicbor.githubclientapp.domain.GithubUserRepo

class ProfileRecycleViewAdapter(private val reposList: List<GithubUserRepo>) :
    RecyclerView.Adapter<ProfileRecycleViewAdapter.ProfileViewHolder>() {

    inner class ProfileViewHolder(binding: FragmentProfileRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val repoName = binding.fragmentProfileRepoName
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemBinding = FragmentProfileRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfileViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.repoName.text = reposList[position].name
    }

    override fun getItemCount(): Int = reposList.size
}