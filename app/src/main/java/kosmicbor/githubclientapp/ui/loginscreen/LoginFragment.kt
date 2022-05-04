package kosmicbor.githubclientapp.ui.loginscreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.app
import kosmicbor.githubclientapp.databinding.FragmentLoginBinding
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.usecases.LoginScreenUseCase
import kosmicbor.githubclientapp.utils.LoginItemTouchHelper
import kosmicbor.githubclientapp.utils.ResourcesHelper
import javax.inject.Inject


class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var useCase: LoginScreenUseCase

    @Inject
    lateinit var resourcesHelper: ResourcesHelper

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            useCase,
            resourcesHelper
        )
    }

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private val loginController by lazy {
        activity as LoginController
    }

    private lateinit var loginAdapter: LoginRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().app.appComponent.inject(this)

        if (savedInstanceState == null) {
            viewModel.getUsersList()
        }
    }

    @Throws(IllegalStateException::class)
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (activity !is LoginController) {
            throw IllegalStateException(getString(R.string.wrong_activity_error_message))
        }
    }

    override fun onResume() {
        super.onResume()

        //viewModel.getUsersList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initAddButton()
    }

    private fun initAddButton() {
        binding.apply {
            loginAddButton.setOnClickListener {
                viewModel.addNewUser(loginTextInputEdittext.text.toString())
            }
        }
    }

    private fun initViewModel() {
        viewModel.usersListLiveData.observe(viewLifecycleOwner) { usersList ->
            loginAdapter = LoginRecyclerviewAdapter(
                usersList as MutableList<GithubUser>,
                { login -> loginController.openProfileScreen(login) },
                { user -> viewModel.deleteUserFromLocalStorage(user) }
            )

            initRecyclerView()
        }

        viewModel.userLiveData.observe(viewLifecycleOwner) {
            loginAdapter.addUser(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it.getContentIfHandled()?.let { errorMessage ->
                Snackbar.make(
                    binding.root,
                    errorMessage,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.apply {
                loginAddButton.isVisible = !it
                loginGithubLogoImageView.isVisible = !it
                loginOrTextView.isVisible = !it
                loginProgressbar.isVisible = it
                loginRecyclerview.isVisible = !it
                loginTextInputLayout.isVisible = !it
                loginTitleTextView.isVisible = !it
            }
        }
    }

    private fun initRecyclerView() {
        binding.loginRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = loginAdapter
            ItemTouchHelper(LoginItemTouchHelper(loginAdapter))
                .attachToRecyclerView(binding.loginRecyclerview)
        }
    }

    interface LoginController {
        fun openProfileScreen(login: String)
    }
}
