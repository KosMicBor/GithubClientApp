package kosmicbor.githubclientapp.ui.userprofilescreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.app
import kosmicbor.githubclientapp.databinding.FragmentProfileBinding
import kosmicbor.githubclientapp.domain.User
import kosmicbor.githubclientapp.ui.loginscreen.LoginViewModelFactory

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object {

        private const val USER_ID = "USER_ID"

        fun newInstance(bundle: Bundle): ProfileFragment {

            val fragment = ProfileFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(requireActivity().app.githubRepo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getInt(USER_ID)

        userId?.let { viewModel.getUser(it) }

        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            initDraw(it)
        }
    }

    private fun initDraw(user: User?) {

        user?.let {
            binding.apply {

                fragmentProfileUserName.text = it.name

                fragmentProfileUserAvatar.load(user.avatarUrl) {
                    transformations(CircleCropTransformation())
                }

                fragmentProfileRecyclerview.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = ProfileRecycleViewAdapter(it.reposList)
                }
            }
        }
    }
}