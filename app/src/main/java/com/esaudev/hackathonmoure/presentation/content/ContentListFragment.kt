package com.esaudev.hackathonmoure.presentation.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.esaudev.hackathonmoure.databinding.FragmentContentListBinding
import com.esaudev.hackathonmoure.presentation.adapter.ContentListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentListFragment : Fragment() {

    private var _binding: FragmentContentListBinding? = null
    private val binding: FragmentContentListBinding
        get() = _binding!!

    private val viewModel: ContentListViewModel by viewModels()

    private val contentTopicListAdapter = ContentListAdapter {

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