package com.android.picsumphotos

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.picsumphotos.photolist.screens.NavigationHost
import com.android.picsumphotos.photolist.viewmodel.MainViewModel
import com.android.picsumphotos.ui.theme.PicSumPhotosTheme
import com.android.picsumphotos.ui.theme.SemanticTokenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }

            setOnExitAnimationListener { sp ->
                sp.iconView.animate().rotation(180F).duration = 1500L
                val slideUp = ObjectAnimator.ofFloat(
                    sp.iconView,
                    View.TRANSLATION_Y,
                    0f,
                    -sp.iconView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 1500L
                slideUp.doOnEnd { sp.remove() }
                slideUp.start()
            }
        }

        setContent {
            val isDarkMode = viewModel.darkMode.value
            PicSumPhotosTheme(isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SemanticTokenTheme.colors.background,
                ) {
                    val lazyPagingItems = viewModel.photosFlow.collectAsLazyPagingItems()
                    NavigationHost(lazyPagingItems) {
                        viewModel.toggleDarkMode()
                    }
                }
            }
        }
    }
}
