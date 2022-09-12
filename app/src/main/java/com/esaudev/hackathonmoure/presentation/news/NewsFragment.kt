package com.esaudev.hackathonmoure.presentation.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.esaudev.hackathonmoure.R
import com.esaudev.hackathonmoure.databinding.FragmentContentListBinding
import com.esaudev.hackathonmoure.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        // Set Ui components
        setupWebView()

        return binding.root
    }

    private fun setupWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl("https://www.android.com/google-features-on-android/fall-2021/")
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)

        }
    }

}