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

        this.retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
        initAddButton()

    }

    private fun initAddButton() {
        binding.apply {
            fragmentLoginAddButton.setOnClickListener {
                viewModel.addNewUser(fragmentLoginTextInputEdittext.text.toString())
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
                fragmentLoginAddButton.isVisible = !it
                fragmentLoginGithubLogo.isVisible = !it
                fragmentLoginOr.isVisible = !it
                fragmentLoginProgressbar.isVisible = it
                fragmentLoginRecyclerview.isVisible = !it
                fragmentLoginTextInputLayout.isVisible = !it
                fragmentLoginTitle.isVisible = !it
            }
        }
    }

    private fun initRecyclerView() {
        binding.fragmentLoginRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = loginAdapter
        }
    }

    interface LoginController {
        fun openProfileScreen(login: String)
    }
}