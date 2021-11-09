package ua.alegator1209.feature_repositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core_ui.ui.fragments.PhaseFragment
import ua.alegator1209.feature_repositories.R
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.databinding.FragmentRepositoriesBinding
import ua.alegator1209.feature_repositories.routing.RepositoryPhase
import ua.alegator1209.feature_repositories.ui.recycler.RepositoriesAdapter
import kotlin.math.max

internal class RepositoryListFragment : PhaseFragment<RepositoryPhase>() {
    private var _binding: FragmentRepositoriesBinding? = null
    private val binding: FragmentRepositoriesBinding get() = _binding!!

    private val adapter: RepositoriesAdapter = RepositoriesAdapter()
    private val viewModel: RepositoryViewModel by featureViewModel()

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

        adapter.onRepositoryClicked = this@RepositoryListFragment::selectRepository
        val layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 0) return // scrolling up, no need to load

                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                val pageSize = viewModel.pageSize
                val lastPage = getLastPageNum(layoutManager.itemCount)

                val lastPageStart = lastPage * pageSize
                val lastPageEnd = lastPageStart + pageSize - 1
                val halfOfLastPage = (lastPageEnd + lastPageStart) / 2
                val scrolledHalfOfLastPage = lastVisibleItem >= halfOfLastPage

                if (scrolledHalfOfLastPage || lastVisibleItem == layoutManager.itemCount - 1) {
                    loadRepos(lastPage + 1, fromIndex = layoutManager.itemCount % pageSize)
                }
            }
        })

        avatar.setOnClickListener { appRouter.goTo(Stage.Profile) }

        loadAvatar()
        loadRepos(page = 0)
    }

    private fun getLastPageNum(itemsCount: Int): Int {
        val pageSize = viewModel.pageSize
        val lastPage = (itemsCount - 1) / pageSize

        return if (itemsCount % pageSize != 0) {
            max(lastPage - 1, 0)
        } else {
            lastPage
        }
    }

    private fun showError(e: Throwable) {
        e.printStackTrace()
        shortToast(e.localizedMessage ?: "Unknown error")
    }

    private fun loadAvatar() {
        Glide.with(this)
            .load(viewModel.user.avatarUrl)
            .placeholder(R.drawable.user_pic_placeholder)
            .centerCrop()
            .circleCrop()
            .into(binding.avatar)
    }

    private fun loadRepos(page: Int, fromIndex: Int = 0) {
        viewModel.loadRepositories(page, fromIndex)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSuccess(adapter::append)
            ?.doOnError(this::showError)
            ?.subscribe()
    }

    private fun selectRepository(repository: Repository) {
        viewModel.selectRepository(repository)
        router.goTo(RepositoryPhase.Detailed)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
