package com.neeraj.presentation.base

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BaseMviComponent <ViewState, ViewIntent,SideEffect> {

    fun sendIntent(intent : ViewIntent)

    fun createInitialState() : ViewState

    val viewState : StateFlow<ViewState>

    val sideEffect : SharedFlow<SideEffect>
}