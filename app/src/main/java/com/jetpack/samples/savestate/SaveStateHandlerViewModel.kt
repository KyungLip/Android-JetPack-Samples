package com.jetpack.samples.savestate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SaveStateHandlerViewModel(private val state: SavedStateHandle) : ViewModel() {
    var tag: String
        get() = state["tag"] ?: ""
        set(value) {
            state["tag"] = value
        }
}