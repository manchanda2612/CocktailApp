package com.neeraj.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author Neeraj Manchanda
 * Abstract base class for ViewModels in the MVVM architecture.
 *
 * @param viewState The type representing the state of the view.
 * @param viewIntent The type representing the intents sent to the ViewModel.
 * @param sideEffect The type representing the side effects produced by the ViewModel.
 */
abstract class  BaseViewModel <viewState : ViewState, viewIntent : ViewIntent, sideEffect : SideEffect> : ViewModel() {

    private val initialState : viewState by lazy { createInitialState() }

    protected val state = MutableStateFlow(initialState)
    val viewState : StateFlow<viewState>
        get() = state

    protected val sideEffect = MutableSharedFlow<sideEffect>()
    val sideEffects : SharedFlow <sideEffect>
        get() = sideEffect

    abstract fun sendIntent(intent: ViewIntent)

    abstract fun createInitialState() : viewState
}