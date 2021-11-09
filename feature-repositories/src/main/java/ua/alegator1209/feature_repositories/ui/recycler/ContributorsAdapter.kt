package ua.alegator1209.feature_repositories.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_repositories.R
import ua.alegator1209.feature_repositories.databinding.RecyclerItemContributorBinding

internal class ContributorsAdapter :
    RecyclerView.Adapter<ContributorsAdapter.ContributorHolder>() {
    private var contributors: List<User> = listOf()

    override fun getItemCount(): Int = if (contributors.isNotEmpty()) contributors.size + 1 else 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorHolder {
        val binding = RecyclerItemContributorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ContributorHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributorHolder, position: Int) {
        if (position == itemCount - 1) {
            holder.bindMoreContributorsButton()
        } else {
            holder.bindContributor(contributors[position])
        }
    }

    fun updateContributors(newContributors: List<User>) {
        contributors = newContributors
        notifyDataSetChanged()
    }

    inner class ContributorHolder(
        private val binding: RecyclerItemContributorBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindContributor(contributor: User) = with(binding) {
            Glide.with(itemView.context)
                .load(contributor.avatarUrl)
                .placeholder(R.drawable.user_pic_placeholder)
                .circleCrop()
                .into(icon)
        }

        fun bindMoreContributorsButton() = with(binding) {
            icon.setImageResource(R.drawable.ic_contributors_more)
        }
    }
}
