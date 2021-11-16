package ua.alegator1209.feature_repositories.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.alegator1209.feature_repositories.databinding.RecyclerItemRepositoryTopicBinding

internal class TopicsAdapter : RecyclerView.Adapter<TopicsAdapter.TopicHolder>() {
    private var topics: List<String> = listOf()

    override fun getItemCount(): Int = topics.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHolder {
        val binding = RecyclerItemRepositoryTopicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TopicHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicHolder, position: Int) {
        holder.bind(topics[position])
    }

    fun update(newTopics: List<String>) {
        topics = newTopics
        notifyDataSetChanged()
    }

    inner class TopicHolder(
        private val binding: RecyclerItemRepositoryTopicBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(topic: String) {
            binding.label.text = topic
        }
    }
}
