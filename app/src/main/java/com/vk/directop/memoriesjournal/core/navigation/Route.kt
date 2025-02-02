package com.vk.directop.memoriesjournal.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object EchoList : Route

    @Serializable
    data object EchoEdit : Route
}