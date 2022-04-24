package kosmicbor.githubclientapp.ui.userprofilescreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.app
import kosmicbor.githubclientapp.data.usacases.RequestUserFromServerUseCaseImpl
import kosmicbor.githubclientapp.data.usacases.RequestUserReposFromServerUseCaseImpl
import kosmicbor.githubclientapp.databinding.FragmentProfileBinding
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.GithubUserRepo

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object {

        private const val USER_LOGIN = "USER_LOGIN"

        fun newInstance(bundle: Bundle): ProfileFragment {

            val fragment = ProfileFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var userLogin: String? = ""
    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels {

        val repo = requireActivity().app.githubRepo

        ProfileViewModelFactory(
            RequestUserFromServerUseCaseImpl(repo),
            RequestUserReposFromServerUseCaseImpl(repo)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.retainInstance = true

        if (savedInstanceState == null) {
            userLogin = arguments?.getString(USER_LOGIN)
            userLogin?.let { viewModel.saveLogin(it) }
        } else {
            userLogin = viewModel.savedLogin
        }

        userLogin?.let { viewModel.getCurrentUser(it) }

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let { it1 -> Snackbar.make(binding.root, it1, Snackbar.LENGTH_SHORT).show() }
        }

        viewModel.reposLiveData.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.apply {
                fragmentProfileRecyclerview.isVisible = !it
                fragmentProfileUserAvatar.isVisible = !it
                fragmentProfileUserName.isVisible = !it
                fragmentProfileReposTitle.isVisible = !it
                fragmentProfileProgressbar.isVisible = it
            }
        }

        viewModel.userLiveDataDTO.observe(viewLifecycleOwner) {
            initProfileDraw(it)
        }
    }

    private fun initProfileDraw(userEntityDTO: GithubUser?) {
        userEntityDTO?.let {
            binding.apply {
                fragmentProfileUserName.text = userEntityDTO.login
                fragmentProfileUserAvatar.load(userEntityDTO.avatarUrl) {
                    transformations(CircleCropTransformation())
                }
            }

            viewModel.getUserRepos(it.login)
        }
    }

    private fun initRecyclerView(reposList: List<GithubUserRepo>) {

        reposList.let {
            binding.apply {

                fragmentProfileRecyclerview.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = ProfileRecycleViewAdapter(it)
                }
            }
        }
    }
}