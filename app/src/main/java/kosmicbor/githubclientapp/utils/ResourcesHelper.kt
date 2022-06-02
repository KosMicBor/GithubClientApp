package kosmicbor.githubclientapp.utils

import android.content.Context

class ResourcesHelper(private val context: Context) {

    fun getMessage(id: Int): String? {
        return context.getString(id)
    }
}