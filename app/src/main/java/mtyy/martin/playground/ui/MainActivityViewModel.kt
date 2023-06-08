package mtyy.martin.playground.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mtyy.martin.playground.util.Event
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val NOT_SHOWING_DETAILS = -1
    }

    // https://developer.android.com/topic/architecture/ui-layer/events#handle-viewmodel-events
    // https://medium.com/androiddevelopers/viewmodel-one-off-event-antipatterns-16a1da869b95
    // no need to emit Event (detail clicked), just emit state (detail is shown)
    // TODO regarding above this should NOT be exposed as EVENT, however i don't have nav component nor i want to manage the FragmentManager.
    private val _showArticleFlow: MutableStateFlow<Event<Int>> = MutableStateFlow(Event(NOT_SHOWING_DETAILS))
    val showArticleFlow: Flow<Event<Int>> = _showArticleFlow

    fun onArticleClicked(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _showArticleFlow.emit(Event(id))
        }
    }

}