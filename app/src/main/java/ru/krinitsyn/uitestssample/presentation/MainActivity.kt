package ru.krinitsyn.uitestssample.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.krinitsyn.uitestssample.R
import ru.krinitsyn.uitestssample.presentation.main.MainFragment
import ru.krinitsyn.uitestssample.presentation.main.MainScreen
import ru.krinitsyn.uitestssample.presentation.main.MainViewModel
import ru.krinitsyn.uitestssample.presentation.splash.SplashFragment
import ru.krinitsyn.uitestssample.ui.theme.UiTestsSampleTheme

@AndroidEntryPoint
class MainActivity : FragmentActivity(), GitHubNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.empty_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.root, SplashFragment(), SplashFragment.TAG)
                commit()
            }
        }

//        val viewModel by viewModels<MainViewModel>()
//
//        setContent {
//            UiTestsSampleTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MainScreen(viewModel = viewModel)
//                }
//            }
//        }
    }

    override fun goToMain() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.root, MainFragment(), MainFragment.TAG)
            commit()
        }
    }
}
