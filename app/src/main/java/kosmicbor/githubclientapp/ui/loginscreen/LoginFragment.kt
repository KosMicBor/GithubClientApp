package kosmicbor.githubclientapp.ui.loginscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.app
import kosmicbor.githubclientapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(requireActivity().app.githubRepo)
    }
    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val loginAdapter: LoginRecyclerviewAdapter = LoginRecyclerviewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.getUsersList()
        }

        initViewModel()
        initRecyclerView()

    }

    private fun initViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            loginAdapter.fillUsersList(it)
        }
    }

    private fun initRecyclerView() {
        binding.fragmentLoginRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = loginAdapter
        }
    }
}