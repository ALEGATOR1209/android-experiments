package ua.alegator1209.feature_repositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core_ui.ui.fragments.PhaseFragment
import ua.alegator1209.feature_repositories.R
import ua.alegator1209.feature_repositories.core.domain.model.Contributor
import ua.alegator1209.feature_repositories.core.domain.model.Language
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.databinding.FragmentRepositoryDetailsBinding
import ua.alegator1209.feature_repositories.routing.RepositoryPhase
import ua.alegator1209.feature_repositories.ui.recycler.ContributorsAdapter
import ua.alegator1209.feature_repositories.ui.recycler.TopicsAdapter

internal class RepositoryDetailsFragment : PhaseFragment<RepositoryPhase>() {
    private var _binding: FragmentRepositoryDetailsBinding? = null
    private val binding: FragmentRepositoryDetailsBinding get() = _binding!!

    private val viewModel: RepositoryViewModel by featureViewModel()

    private val contributorsAdapter = ContributorsAdapter()
    private val topicsAdapter = TopicsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerContributors.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = contributorsAdapter
        }

        binding.recyclerTopics.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = topicsAdapter
        }

        viewModel.selectedRepository
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showRepository, this::showError)

        viewModel.getTopicsForSelectedRepository()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showTopics, this::showError)

        viewModel.getContributorsForSelectedRepository()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showContributors, this::showError)

        viewModel.getLanguagesForSelectedRepository()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showLanguages, this::showError)
    }

    private fun showRepository(repository: Repository) = with(binding) {
        title.text = repository.name
        desc.text = repository.description
        privateLabel.text = if (repository.isPrivate) {
            getString(R.string.private_label)
        } else {
            getString(R.string.public_label)
        }

        lockIcon.isVisible = repository.isPrivate

        starsCount.text = repository.stargazersCount.toString()
        forkCount.text = repository.forksCount.toString()
    }

    private fun showContributors(contributors: List<Contributor>) {
        contributorsAdapter.updateContributors(contributors)
    }

    private fun showLanguages(languages: List<Language>) {
        binding.languagesBar.setLanguages(languages)
    }

    private fun showTopics(topics: List<String>) {
        topicsAdapter.update(topics)
    }

    private fun showError(cause: Throwable) {
        cause.printStackTrace()
        shortToast(cause.localizedMessage ?: "Unexpected error")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
