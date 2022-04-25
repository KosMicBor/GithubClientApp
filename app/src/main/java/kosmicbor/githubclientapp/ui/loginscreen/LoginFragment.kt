package kosmicbor.githubclientapp.ui.loginscreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.app
import kosmicbor.githubclientapp.data.usacases.AddNewLocalUserUseCaseImpl
import kosmicbor.githubclientapp.data.usacases.GetLocalUsersListUseCaseImpl
import kosmicbor.githubclientapp.data.usacases.RequestUserFromServerUseCaseImpl
import kosmicbor.githubclientapp.databinding.FragmentLoginBinding
import java.lang.IllegalStateException

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels {

        val repo = requireActivity().app.githubRepo

        LoginViewModelFactory(
            RequestUserFromServerUseCaseImpl(repo),
            AddNewLocalUserUseCaseImpl(repo),
            GetLocalUsersListUseCaseImpl(repo)
        )
    }
    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private val loginController by lazy {
        activity as LoginController
    }

    private val loginAdapter: LoginRecyclerviewAdapter = LoginRecyclerviewAdapter {
        loginController.openProfileScreen(it)
    }

    @Throws(IllegalStateException::class)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is LoginController) {
            throw IllegalStateException(getString(R.string.wrong_activity_error_message))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.getUsersList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
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
        viewModel.usersListLiveData.observe(viewLifecycleOwner) {
            loginAdapter.fillUsersList(it)
        }

        viewModel.userLiveData.observe(viewLifecycleOwner) {
            loginAdapter.addUser(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let { it1 -> Snackbar.make(binding.root, it1, Snackbar.LENGTH_SHORT).show() }
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
        }
    }

    interface LoginController {
        fun openProfileScreen(login: String)
    }
}