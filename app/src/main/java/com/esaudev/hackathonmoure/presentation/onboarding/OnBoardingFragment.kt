package com.esaudev.hackathonmoure.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.esaudev.hackathonmoure.R
import com.esaudev.hackathonmoure.databinding.FragmentNewsBinding
import com.esaudev.hackathonmoure.databinding.FragmentOnBoardingBinding
import com.esaudev.hackathonmoure.domain.model.OnBoardingPage
import com.esaudev.hackathonmoure.presentation.main.MainActivity
import com.esaudev.hackathonmoure.presentation.onboarding.page.OnBoardingViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding: FragmentOnBoardingBinding
        get() = _binding!!

    private var onBoardingPages = emptyList<OnBoardingPage>()
    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        // Set Ui components
        initPages()
        initViewPager()

        return binding.root
    }

    private fun initPages() {
        onBoardingPages = listOf(
            OnBoardingPage(
                title = getString(R.string.on_boarding__first_title),
                subtitle = getString(R.string.on_boarding__first_subtitle),
                image = R.drawable.ic_on_boarding_news
            ),
            OnBoardingPage(
                title = getString(R.string.on_boarding__second_title),
                subtitle = getString(R.string.on_boarding__second_subtitle),
                image = R.drawable.study
            ),
            OnBoardingPage(
                title = getString(R.string.on_boarding__third_title),
                subtitle = getString(R.string.on_boarding__third_subtitle),
                image = R.drawable.ic_help
            )
        )
    }

    private fun initViewPager() {
        with(binding) {
            vpOnBoarding.adapter = OnBoardingViewPagerAdapter(onBoardingPages, onFinishClickListener = {
                saveOnBoardingState()
            })
            vpOnBoarding.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            ciOnBoarding.setViewPager(vpOnBoarding)
        }
    }

    private fun saveOnBoardingState() {
        viewModel.saveOnBoardingState()
        findNavController().navigate(R.id.action_onBoardingFragment_to_bottomMainNav)
    }

}