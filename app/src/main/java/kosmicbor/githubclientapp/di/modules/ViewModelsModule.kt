package kosmicbor.githubclientapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kosmicbor.githubclientapp.di.qualifiers.LoginViewModelFactoryQualifier
import kosmicbor.githubclientapp.di.qualifiers.ProfileViewModelFactoryQualifier
import kosmicbor.githubclientapp.ui.loginscreen.LoginViewModel
import kosmicbor.githubclientapp.ui.loginscreen.LoginViewModelFactory
import kosmicbor.githubclientapp.ui.userprofilescreen.ProfileViewModel
import kosmicbor.githubclientapp.ui.userprofilescreen.ProfileViewModelFactory
import kotlin.reflect.KClass

@Module
abstract class ViewModelsModule {

    @Binds
    @LoginViewModelFactoryQualifier
    internal abstract fun bindLoginViewModelFactory(factory: LoginViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ProfileViewModelFactoryQualifier
    internal abstract fun bindProfileViewModelFactory(factory: ProfileViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun postLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun postProfileViewModel(viewModel: ProfileViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)