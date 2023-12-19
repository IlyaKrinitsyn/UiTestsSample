package ru.krinitsyn.uitestssample.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.krinitsyn.uitestssample.data.github.GitHubRepository
import ru.krinitsyn.uitestssample.models.presentation.Gist
import ru.krinitsyn.uitestssample.models.presentation.User
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Ошибка при загрузке гистов", throwable)
    }

    val gistsFlow: StateFlow<List<Gist>>
        get() = _gistsFlow
    private val _gistsFlow = MutableStateFlow<List<Gist>>(emptyList())

    val isGistsLoadingFlow: StateFlow<Boolean>
        get() = _isGistsLoadingFlow
    private val _isGistsLoadingFlow = MutableStateFlow(true)

    fun loadGists() {
        viewModelScope.launch(exceptionHandler) {
//            delay(500L)
            _isGistsLoadingFlow.emit(true)
            val gists = gitHubRepository.getGists()
                .mapNotNull { gist ->
                    gist.description?.let { description ->
                        Gist(
                            description = description,
                            owner = User(
                                login = gist.owner.login,
                                id = gist.owner.id,
                                avatarUrl = gist.owner.avatarUrl,
                                url = gist.owner.url,
                                gistsUrl = gist.owner.gistsUrl,
                            )
                        )
                    }
                }
            _gistsFlow.emit(gists)
            _isGistsLoadingFlow.emit(false)
        }
    }

    private companion object {
        const val TAG = "MainViewModel"
    }

}