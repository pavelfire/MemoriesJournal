package com.vk.directop.memoriesjournal.echo_list

sealed interface EchoListAction {
    data class OnItemClick(val record: EchoListItem) : EchoListAction
    data object OnStartRecord : EchoListAction
    data object OnStopRecord : EchoListAction
    data class OnSaveRecord(val description: String) : EchoListAction
}