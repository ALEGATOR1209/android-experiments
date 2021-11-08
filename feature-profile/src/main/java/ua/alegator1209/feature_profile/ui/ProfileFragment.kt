package ua.alegator1209.feature_profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.core_ui.ui.fragments.FeatureSolitaryFragment
import ua.alegator1209.feature_profile.R
import ua.alegator1209.feature_profile.databinding.FragmentProfileBinding
import ua.alegator1209.feature_profile.di.ProfileComponentProvider

class ProfileFragment : FeatureSolitaryFragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModel()
    private val provider: ProfileComponentProvider by provider()

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
            .doOnNext(this::showUser)
            .doOnError(this::showError)
            .subscribe()

        binding.logoutBtn.setOnClickListener { logout() }
    }

    private fun showUser(user: User) = with(binding) {
        username.text = user.name
        email.text = user.email
        company.text = user.company
        blog.text = user.blog
        location.text = user.location
        publicGists.text = user.publicGists.toString()
        publicRepos.text = user.publicRepos.toString()

        Glide.with(this@ProfileFragment)
            .load(user.avatarUrl)
            .placeholder(R.drawable.user_pic_placeholder)
            .centerCrop()
            .circleCrop()
            .into(profilePic)
    }

    private fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        shortToast(throwable.localizedMessage ?: "Unexpected error")
    }

    private fun logout() {
        viewModel.logout()
            .doOnError(this::showError)
            .doOnComplete { appRouter.returnTo(Stage.Login) }
            .subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
