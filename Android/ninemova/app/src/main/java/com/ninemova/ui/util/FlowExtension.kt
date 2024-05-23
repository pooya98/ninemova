package com.ninemova.ui.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private fun createTickerFlow(interval: Long): Flow<Unit> = flow {
    while (true) {
        delay(interval)
        emit(Unit)
    }
}

fun runTickerFlow(
    interval: Long,
    scope: CoroutineScope,
    action: () -> Unit,
) {
    val tickerFlow = createTickerFlow(interval)
    scope.launch {
        tickerFlow.collect {
            action.invoke()
        }
    }
}
