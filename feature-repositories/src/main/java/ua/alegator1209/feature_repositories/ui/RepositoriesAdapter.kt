package ua.alegator1209.feature_repositories.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.databinding.RecyclerItemRepositoryBinding

internal class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.RepositoryHolder>() {
    private var dataset: List<Repository> = listOf()

    override fun getItemCount(): Int = dataset.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        val binding = RecyclerItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RepositoryHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        holder.bind(dataset[position])
    }

    fun append(newData: List<Repository>) {
        if (newData.isEmpty()) return

        val lastIndex = dataset.lastIndex
        dataset = dataset + newData
        notifyItemRangeInserted(lastIndex + 1, newData.size)
    }

    inner class RepositoryHolder(
        private val binding: RecyclerItemRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository) = with(binding) {
            title.text = repository.fullName
            lockIcon.isVisible = repository.isPrivate
            starsCount.text = repository.stargazersCount.toString()
            forkCount.text = repository.forksCount.toString()
            desc.text = repository.description
        }
    }
}
