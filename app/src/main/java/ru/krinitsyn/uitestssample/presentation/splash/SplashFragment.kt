package ru.krinitsyn.uitestssample.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import ru.krinitsyn.uitestssample.presentation.GitHubNavigator
import ru.krinitsyn.uitestssample.ui.designsystem.DsTitle
import ru.krinitsyn.uitestssample.ui.designsystem.resources.C

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(C.Screen.Splash.name)
                ) {
                    Button(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(all = 16.dp)
                            .testTag(C.Screen.Splash.goToMainButton),
                        onClick = { (requireActivity() as GitHubNavigator).goToMain() }
                    ) {
                        DsTitle(title = "Далее")
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "SplashFragment"
    }
}