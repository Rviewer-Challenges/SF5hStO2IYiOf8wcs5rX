package com.esaudev.hackathonmoure.presentation.chat

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.esaudev.hackathonmoure.databinding.FragmentChatBinding
import com.esaudev.hackathonmoure.domain.extension.dpToPx
import com.esaudev.hackathonmoure.domain.extension.hideKeyboard
import com.esaudev.hackathonmoure.domain.model.Message
import com.esaudev.hackathonmoure.presentation.adapter.MessagesListAdapter
import com.esaudev.hackathonmoure.presentation.adapter.VerticalMarginItemDecoration
import com.esaudev.hackathonmoure.util.Resource
import com.google.firebase.Timestamp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding: FragmentChatBinding
        get() = _binding!!

    private val viewModel: ChatViewModel by viewModels()
    private lateinit var messagesListAdapter: MessagesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)

        // Set Ui components
        subscribeViewModel()
        setupMessageListener()
        setupClickListeners()

        return binding.root
    }

    private fun setupListComponent(senderId: String) {
        messagesListAdapter = MessagesListAdapter(senderId = senderId)
        with(binding) {
            rvMessagesList.apply {
                adapter = messagesListAdapter
                addItemDecoration(VerticalMarginItemDecoration(requireContext().dpToPx(8)))
            }
        }
    }

    private fun setupMessageListener() {
        with(binding) {
            etMessage.addTextChangedListener {
                llSendMessage.isVisible = it.toString().isNotEmpty()
            }
        }
    }

    private fun setupClickListeners() {
        with(binding) {
            llSendMessage.setOnClickListener {
                handleSendMessage()
            }
        }
    }

    private fun handleSendMessage() {
        viewModel.sendMessage(
            Message(
                message = binding.etMessage.text.toString(),
                timestamp = Timestamp.now()
            )
        )
    }

    private fun subscribeViewModel() {
        viewModel.senderId.observe(viewLifecycleOwner) { senderId ->
            setupListComponent(senderId)
        }
        viewModel.messagesListState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> {
                    handleMessages(messages = state.data)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    handleLoading(isLoading = true)
                }
                else -> Unit
            }
        }

        viewModel.sendMessageState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> clearMessage()
                is Resource.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                    .show()
                else -> Unit
            }
        }
    }

    private fun handleMessages(messages: List<Message>) {
        if (messages.isEmpty()) {
            showEmptyScreen()
        } else {
            messagesListAdapter.submitList(messages)
            showMessages()
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            pbMessages.isVisible = isLoading
        }
    }

    private fun showEmptyScreen() {
        handleLoading(isLoading = false)
        with(binding) {
            rvMessagesList.isVisible = false
            tvEmptyMessages.isVisible = true
            ivEmptyMessages.isVisible = true
        }
    }

    private fun showMessages() {
        handleLoading(isLoading = false)
        with(binding) {
            tvEmptyMessages.isVisible = false
            ivEmptyMessages.isVisible = false
            rvMessagesList.isVisible = true
        }
    }

    private fun clearMessage() {
        binding.etMessage.text.clear()
        binding.rvMessagesList.smoothScrollToPosition(0)
    }

}