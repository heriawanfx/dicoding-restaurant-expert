package com.heriawanfx.restaurant.core.utils

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
fun EditText.getTextStateFlow(): StateFlow<CharSequence> {
    val text = MutableStateFlow("")

    doAfterTextChanged {
        text.value = it?.toString() ?: ""
    }
    return text
}

@FlowPreview
@ExperimentalCoroutinesApi
fun EditText.debounceAfterTextChanged() {
    getTextStateFlow()
        .debounce(300)
        .distinctUntilChanged()
        .flowOn(Dispatchers.Default)
}
