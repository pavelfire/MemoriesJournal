package com.vk.directop.memoriesjournal.core.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object EchoList : Destination

    @Serializable
    data class EchoEdit(val id: String) : Destination
}