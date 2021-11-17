package ua.alegator1209.feature_repositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core_ui.ui.fragments.PhaseFragment
import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.databinding.FragmentBranchListBinding
import ua.alegator1209.feature_repositories.routing.RepositoryPhase
import ua.alegator1209.feature_repositories.ui.recycler.BranchesAdapter

internal class RepositoryBranchesFragment : PhaseFragment<RepositoryPhase>() {
    private var _binding: FragmentBranchListBinding? = null
    private val binding: FragmentBranchListBinding get() = _binding!!

    private val adapter: BranchesAdapter = BranchesAdapter()
    private val branchesViewModel: BranchesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBranchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        (featureFragment as RepositoryFragment).component.inject(branchesViewModel)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        branchesViewModel.repository.subscribe(
            { title.text = it.fullName },
            {
                showError(it)
                router.back()
            }
        )

        loadBranches()
    }

    private fun loadBranches() {
        branchesViewModel.getBranches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showBranches, this::showError)
    }

    private fun showError(e: Throwable) {
        e.printStackTrace()
        shortToast(e.localizedMessage ?: "Unknown error")
    }

    private fun showBranches(branches: List<Branch>) {
        adapter.update(branches)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
