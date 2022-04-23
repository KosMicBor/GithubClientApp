package kosmicbor.githubclientapp.utils

import androidx.recyclerview.widget.DiffUtil
import kosmicbor.githubclientapp.domain.GithubUserEntity
import kosmicbor.githubclientapp.domain.User

class DiffUtilCallback(
    private val oldList: List<GithubUserEntity>,
    private val newList: List<GithubUserEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].avatarUrl == newList[newItemPosition].avatarUrl &&
                oldList[oldItemPosition].name == newList[newItemPosition].name &&
                oldList[oldItemPosition].login == newList[newItemPosition].login &&
                oldList[oldItemPosition].email == newList[newItemPosition].email
    }
}