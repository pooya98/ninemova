package com.ninemova.Network.repositoryimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ninemova.Network.repository.LocalDataStoreRepository
import com.ninemova.domain.data.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalDataStoreRepositoryImpl(private val dataStore: DataStore<Preferences>) :
    LocalDataStoreRepository {


    override suspend fun saveUser(user: User) {
        dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = user.id.toString()
            prefs[USER_NAME_KEY] = user.userName ?: ""
            prefs[USER_NICKNAME_KEY] = user.nickName ?: ""
            prefs[USER_PROFILE_IMAGE_KEY] = user.profileImageUrl ?: ""
        }
    }

    override suspend fun deleteUser() {
        dataStore.edit { prefs ->
            with(prefs) {
                remove(USER_ID_KEY)
                remove(USER_NAME_KEY)
                remove(USER_NICKNAME_KEY)
                remove(USER_PROFILE_IMAGE_KEY)
            }
        }
    }

    override suspend fun getUserId(): Int = dataStore.data.map { preferences ->
        preferences[USER_ID_KEY] ?: "-1"
    }.first().toInt()

    override suspend fun getUserName(): String = dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: ""
    }.first()

    override suspend fun getUserNickName(): String = dataStore.data.map { preferences ->
        preferences[USER_NICKNAME_KEY] ?: ""
    }.first()

    override suspend fun getUserProfileImageUrl(): String = dataStore.data.map { preferences ->
        preferences[USER_PROFILE_IMAGE_KEY] ?: ""
    }.first()

    companion object {
        val USER_ID_KEY = stringPreferencesKey("user_id")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val USER_NICKNAME_KEY = stringPreferencesKey("user_nickname")
        val USER_PROFILE_IMAGE_KEY = stringPreferencesKey("user_profile_image")
    }
}
