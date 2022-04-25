package kosmicbor.githubclientapp.utils

import androidx.recyclerview.widget.DiffUtil
import kosmicbor.githubclientapp.domain.GithubUser

class DiffUtilCallback(
    private val oldList: List<GithubUser>,
    private val newList: List<GithubUser>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].avatarUrl == newList[newItemPosition].avatarUrl &&
                oldList[oldItemPosition].login == newList[newItemPosition].login

    }
}