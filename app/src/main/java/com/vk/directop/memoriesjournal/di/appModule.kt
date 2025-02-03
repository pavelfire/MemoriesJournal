package com.vk.directop.memoriesjournal.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.vk.directop.memoriesjournal.core.data.AudioRecordsDatabase
import com.vk.directop.memoriesjournal.core.data.AudioRepository
import com.vk.directop.memoriesjournal.core.data.AudioUseCase
import com.vk.directop.memoriesjournal.core.data.DatabaseFactory
import com.vk.directop.memoriesjournal.core.data.playback.AndroidAudioPlayer
import com.vk.directop.memoriesjournal.core.data.recorder.AndroidAudioRecorder
import com.vk.directop.memoriesjournal.core.navigation.DefaultNavigator
import com.vk.directop.memoriesjournal.core.navigation.Destination
import com.vk.directop.memoriesjournal.core.navigation.Navigator
import com.vk.directop.memoriesjournal.echo_edit.EchoEditViewModel
import com.vk.directop.memoriesjournal.echo_list.EchoListScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.io.File

val appModule = module {
    single<Navigator> {
        DefaultNavigator(startDestination = Destination.EchoList)
    }

    singleOf(::AudioRepository)

    single { DatabaseFactory(androidApplication()) }

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<AudioRecordsDatabase>().echoDao }

    singleOf(::AudioUseCase)
    singleOf(::AndroidAudioRecorder)
    singleOf(::AndroidAudioPlayer)

    single { File(androidApplication().cacheDir, "audio_records") }

    viewModelOf(::EchoListScreenViewModel)
    viewModelOf(::EchoEditViewModel)
}