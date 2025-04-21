package com.example.trafficlight

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class TrafficLightState {
    RED, YELLOW, GREEN
}

class TrafficLightViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val states = listOf(
        TrafficLightState.RED,
        TrafficLightState.YELLOW,
        TrafficLightState.GREEN,
        TrafficLightState.YELLOW
    )
    private val _state = MutableStateFlow(
        states[savedStateHandle.get<Int>("state_index") ?: 0]
    )
    val state: StateFlow<TrafficLightState> = _state.asStateFlow()

    fun changeLight() {
        val currentIndex = savedStateHandle.get<Int>("state_index") ?: 0
        val nextIndex = (currentIndex + 1) % states.size
        savedStateHandle["state_index"] = nextIndex
        _state.value = states[nextIndex]
    }
}