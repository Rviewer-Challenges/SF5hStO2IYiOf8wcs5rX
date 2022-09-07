package com.esaudev.hackathonmoure.presentation.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.esaudev.hackathonmoure.data.local.ContentTopics
import com.esaudev.hackathonmoure.databinding.FragmentContentListBinding
import com.esaudev.hackathonmoure.domain.extension.openChromeTab
import com.esaudev.hackathonmoure.presentation.adapter.ContentListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentListFragment : Fragment() {

    private var _binding: FragmentContentListBinding? = null
    private val binding: FragmentContentListBinding
        get() = _binding!!

    private val viewModel: ContentListViewModel by viewModels()

    private val contentTopicListAdapter = ContentListAdapter {
        when(it.title) {
            ContentTopics.contentTopicList[0].title -> {
                requireContext().openChromeTab("https://developer.android.com/develop/ui/views/layout/constraint-layout")
            }
            ContentTopics.contentTopicList[1].title -> {
                requireContext().openChromeTab("https://developer.android.com/develop/ui/views/animations/transitions")
            }
            ContentTopics.contentTopicList[2].title -> {
                requireContext().openChromeTab("https://developer.android.com/topic/libraries/architecture/viewmodel?gclsrc=ds&gclsrc=ds")
            }
            ContentTopics.contentTopicList[3].title -> {
                requireContext().openChromeTab("https://levelup.gitconnected.com/android-recyclerview-animations-in-kotlin-1e323ffd39be")
            }
            ContentTopics.contentTopicList[4].title -> {
                requireContext().openChromeTab("https://firebase.google.com/docs/android/setup?hl=es")
            }
            ContentTopics.contentTopicList[5].title -> {
                requireContext().openChromeTab("https://developer.android.com/studio/debug?hl=es-419")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContentListBinding.inflate(inflater, container, false)

        // Set Ui components
        setupTopicListComponent()
        subscribeViewModel()

        return binding.root
    }

    private fun setupTopicListComponent() {
        binding.rvContentTopicsList.apply {
            adapter = contentTopicListAdapter
        }
    }

    private fun subscribeViewModel() {
        viewModel.contentList.observe(viewLifecycleOwner) {
            contentTopicListAdapter.submitList(it)
        }
    }

}