package ua.alegator1209.feature_repositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.alegator1209.core_ui.ui.fragments.BaseFragment
import ua.alegator1209.feature_repositories.databinding.FragmentRepositoryDetailsBinding

internal class RepositoryDetailsFragment : BaseFragment() {
    private var _binding: FragmentRepositoryDetailsBinding? = null
    private val binding: FragmentRepositoryDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
