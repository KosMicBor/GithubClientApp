package kosmicbor.githubclientapp.utils

open class EventChecker<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            return content
        }
    }

    fun peekContent(): T = content
}