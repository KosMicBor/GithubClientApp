package kosmicbor.githubclientapp.ui.loginscreen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.app
import kosmicbor.githubclientapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(requireActivity().app.githubRepo)
    }
    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val loginAdapter: LoginRecyclerviewAdapter = LoginRecyclerviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (savedInstanceState == null) {
//            viewModel.getUsersList()
//        }
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
}