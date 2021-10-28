package ua.alegator1209.feature_profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.core_ui.BaseFragment
import ua.alegator1209.feature_profile.databinding.FragmentProfileBinding
import ua.alegator1209.feature_profile.di.ProfileComponentProvider

class ProfileFragment : BaseFragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[ProfileViewModel::class.java] }
    private var provider: ProfileComponentProvider = ProfileComponentProvider {
        error("Provider not set!")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provider.provideProfileComponent().inject(viewModel)

        viewModel.loadUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(this::showUser)
            .doOnError(this::showError)
            .subscribe()
    }

    private fun showUser(user: User) = with(binding) {
        username.text = user.name
        email.text = user.email
        company.text = user.company
        blog.text = user.blog
        location.text = user.location
        publicGists.text = user.publicGists.toString()
        publicRepos.text = user.publicRepos.toString()
    }

    private fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        shortToast(throwable.localizedMessage ?: "Unexpected error")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(provider: ProfileComponentProvider) = ProfileFragment().also {
            it.provider = provider
        }
    }
}
