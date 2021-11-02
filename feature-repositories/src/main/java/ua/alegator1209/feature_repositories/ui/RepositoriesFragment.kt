package ua.alegator1209.feature_repositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core_ui.BaseFragment
import ua.alegator1209.feature_repositories.R
import ua.alegator1209.feature_repositories.databinding.FragmentRepositoriesBinding
import ua.alegator1209.feature_repositories.di.RepositoryComponentProvider

class RepositoriesFragment : BaseFragment() {
    private var _binding: FragmentRepositoriesBinding? = null
    private val binding: FragmentRepositoriesBinding get() = _binding!!

    private lateinit var provider: RepositoryComponentProvider
    private val viewModel by lazy { ViewModelProvider(this)[RepositoriesViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provider.provideRepositoryComponent().inject(viewModel)

        with(binding) {
            Glide.with(this@RepositoriesFragment)
                .load(viewModel.user.avatarUrl)
                .placeholder(R.drawable.user_pic_placeholder)
                .centerCrop()
                .circleCrop()
                .into(avatar)

            val adapter = RepositoriesAdapter()

            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())

            avatar.setOnClickListener { router.goTo(Stage.Profile) }

            viewModel.loadRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(adapter::append)
                .doOnError(this@RepositoriesFragment::showError)
                .subscribe()
        }
    }

    private fun showError(e: Throwable) {
        e.printStackTrace()
        shortToast(e.localizedMessage ?: "Unknown error")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(provider: RepositoryComponentProvider) = RepositoriesFragment().also {
            it.provider = provider
        }
    }
}
