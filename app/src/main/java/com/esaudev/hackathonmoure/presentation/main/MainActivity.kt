package com.esaudev.hackathonmoure.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.esaudev.hackathonmoure.R
import com.esaudev.hackathonmoure.databinding.ActivityMainBinding
import com.esaudev.hackathonmoure.domain.extension.gone
import com.esaudev.hackathonmoure.domain.extension.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
        if (controller.currentDestination?.id == R.id.onBoardingFragment) {
            hideBottomMenu()
        } else {
            showBottomMenu()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        addInitialDataListener()
        addObservers()
        loadAppView()
    }

    private fun addObservers() {
        viewModel.onBoardingSeen.observe(this) { onBoardingSeen ->

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fMainContent) as NavHostFragment
            val navController = navHostFragment.navController
            val navGraph =
                findNavController(R.id.fMainContent).navInflater.inflate(R.navigation.main_nav_graph)
            navGraph.setStartDestination(
                if (onBoardingSeen) {
                    R.id.bottom_nav_main_nav_graph
                } else {
                    R.id.onBoardingFragment
                }
            )
            navController.graph = navGraph
        }

    }

    private fun addInitialDataListener() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener {
            return@addOnPreDrawListener viewModel.senderIdState.value ?: false
        }
    }

    private fun loadAppView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findNavController(R.id.fMainContent).addOnDestinationChangedListener(listener)

        binding.bnvMainMenu.setupWithNavController(findNavController(R.id.fMainContent))


    }

    private fun hideBottomMenu() {
        binding.bnvMainMenu.gone()
    }

    private fun showBottomMenu() {
        binding.bnvMainMenu.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        findNavController(R.id.fMainContent).removeOnDestinationChangedListener(listener)
    }
}