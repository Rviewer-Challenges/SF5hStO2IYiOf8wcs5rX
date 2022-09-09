package com.esaudev.hackathonmoure.presentation.onboarding.page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esaudev.hackathonmoure.R
import com.esaudev.hackathonmoure.databinding.FragmentOnBoardingPageBinding
import com.esaudev.hackathonmoure.domain.extension.showWithFade
import com.esaudev.hackathonmoure.domain.model.OnBoardingPage
import com.esaudev.hackathonmoure.presentation.adapter.BaseListViewHolder

class OnBoardingViewPagerAdapter(
    private val onBoardingPages: List<OnBoardingPage>,
    private val onFinishClickListener: () -> Unit
): RecyclerView.Adapter<BaseListViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = FragmentOnBoardingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolder -> holder.bind(onBoardingPages[position], position)
            else -> {}
        }
    }

    override fun getItemCount(): Int {
        return onBoardingPages.size
    }

    inner class BindViewHolder(private val binding: FragmentOnBoardingPageBinding) : BaseListViewHolder<OnBoardingPage>(binding.root) {
        override fun bind(item: OnBoardingPage, position: Int) = with(binding) {

            tvOnBoardingTitle.text = item.title
            tvOnBoardingSubtitle.text = item.subtitle
            ivOnBoardingPage.setImageResource(item.image)

            if (item.title == itemView.context.getString(R.string.on_boarding__third_title)) {
                bContinue.showWithFade()

                bContinue.setOnClickListener { onFinishClickListener.invoke() }
            }
        }
    }
}