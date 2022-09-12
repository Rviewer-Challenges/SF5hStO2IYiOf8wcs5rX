package com.esaudev.hackathonmoure.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.hackathonmoure.R
import com.esaudev.hackathonmoure.databinding.ItemContentBinding
import com.esaudev.hackathonmoure.domain.model.SingleContentTopic

class ContentListAdapter(
    private val onContentTopicClick: (SingleContentTopic) -> Unit,
): ListAdapter<SingleContentTopic, BaseListViewHolder<*>>(DiffUtilCallback)  {

    private object DiffUtilCallback : DiffUtil.ItemCallback<SingleContentTopic>() {
        override fun areItemsTheSame(oldItem: SingleContentTopic, newItem: SingleContentTopic): Boolean = oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: SingleContentTopic, newItem: SingleContentTopic): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolder -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolder(private val binding: ItemContentBinding) : BaseListViewHolder<SingleContentTopic>(binding.root) {

        override fun bind(item: SingleContentTopic, position: Int) = with(binding) {

            tvContentTitle.text = item.title
            tvContentDesc.text = item.description
            ivContentIcon.setImageResource(item.iconRes)

            when(item.iconRes) {
                R.drawable.ic_beginner -> mcvTopicParent.setCardBackgroundColor(ContextCompat.getColor(root.context, R.color.green_message))
                R.drawable.ic_intermediate -> mcvTopicParent.setCardBackgroundColor(ContextCompat.getColor(root.context, R.color.blue))
                R.drawable.ic_advance -> mcvTopicParent.setCardBackgroundColor(ContextCompat.getColor(root.context, R.color.red))
            }

            mcvTopicParent.setOnClickListener { onContentTopicClick.invoke(item) }
        }
    }
}