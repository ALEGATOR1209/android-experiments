package ua.alegator1209.feature_repositories.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.databinding.RecyclerItemBranchBinding

internal class BranchesAdapter : RecyclerView.Adapter<BranchesAdapter.BranchHolder>() {
    private var branches: List<Branch> = listOf()

    override fun getItemCount(): Int = branches.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchHolder {
        val binding = RecyclerItemBranchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BranchHolder(binding)
    }

    override fun onBindViewHolder(holder: BranchHolder, position: Int) {
        holder.bind(branches[position])
    }

    fun update(newBranches: List<Branch>) {
        branches = newBranches
        notifyDataSetChanged()
    }

    inner class BranchHolder(
        private val binding: RecyclerItemBranchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(branch: Branch) {
            binding.title.text = branch.name
            binding.lockIcon.isVisible = branch.protected
        }
    }
}
