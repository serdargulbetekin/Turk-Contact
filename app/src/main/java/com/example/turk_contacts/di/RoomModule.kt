package com.example.turk_contacts.di

import android.content.Context
import androidx.room.Room
import com.example.turk_contacts.room.ContactDao
import com.example.turk_contacts.room.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    fun provideChannelDao(contactDatabase: ContactDatabase): ContactDao {
        return contactDatabase.contactDao()
    }

    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext appContext: Context): ContactDatabase {
        return Room.databaseBuilder(
            appContext,
            ContactDatabase::class.java,
            "Contact"
        ).build()
    }
}