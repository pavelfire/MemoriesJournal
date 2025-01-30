package com.vk.directop.memoriesjournal.echo_list

import com.vk.directop.memoriesjournal.echo_list.models.ItemUi

sealed interface EchoListAction {
    data class OnItemClick(val record: ItemUi) : EchoListAction
    data class OnPlayClick(val filePath: String) : EchoListAction
    data object OnStartRecord : EchoListAction
    data object OnStopRecord : EchoListAction
    data class OnSaveRecord(val description: String) : EchoListAction
}