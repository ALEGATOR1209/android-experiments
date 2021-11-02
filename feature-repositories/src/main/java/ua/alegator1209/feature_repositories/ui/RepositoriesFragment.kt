package ua.alegator1209.feature_repositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val adapter: RepositoriesAdapter = RepositoriesAdapter()
    private val viewModel by lazy { ViewModelProvider(this)[RepositoriesViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        provider.provideRepositoryComponent().inject(viewModel)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 0) return // scrolling up, no need to load

                val firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition()
                val lastVisibleItemPos = layoutManager.findLastVisibleItemPosition()

                val pageSize = viewModel.pageSize
                val lastPage = layoutManager.itemCount / pageSize - 1

                val half = pageSize / 2
                val lastPageStart = lastPage * pageSize
                val halfOfLastPage = lastPageStart + half
                val scrolledHalfOfLastPage = lastVisibleItemPos >= halfOfLastPage
                if (scrolledHalfOfLastPage) {
                    loadRepos()
                }
            }
        })

        avatar.setOnClickListener { router.goTo(Stage.Profile) }

        loadAvatar()
        loadRepos()
    }

    private fun showError(e: Throwable) {
        e.printStackTrace()
        shortToast(e.localizedMessage ?: "Unknown error")
    }

    private fun loadAvatar() {
        Glide.with(this@RepositoriesFragment)
            .load(viewModel.user.avatarUrl)
            .placeholder(R.drawable.user_pic_placeholder)
            .centerCrop()
            .circleCrop()
            .into(binding.avatar)
    }

    private fun loadRepos() {
        viewModel.loadRepositories()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSuccess(adapter::append)
            ?.doOnError(this@RepositoriesFragment::showError)
            ?.subscribe()
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
