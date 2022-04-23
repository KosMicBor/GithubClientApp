package kosmicbor.githubclientapp.utils

interface RequestCallback<T> {
    fun onSuccess(value: T)
    fun onError(t: Throwable)
}