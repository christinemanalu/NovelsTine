package org.d3if3003.novelstine.introd

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3003.novelstine.db.User
import org.d3if3003.novelstine.db.UserDao

class IntrodViewModel(private val db: UserDao) : ViewModel() {
    private val userLiveData = MutableLiveData<User?>()

    fun getNama(nama: String) {
        userLiveData.value = User(nama = nama)
        val dataUser = User(
            nama = nama
        )
        userLiveData.value = dataUser
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//            val dataUser = User(
//                nama = nama
//            )
                db.insert(dataUser)
            }
        }
    }
    fun getHasilUser(): LiveData<User?> = userLiveData
}

class IntrodViewModelFactory(
    private val db: UserDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IntrodViewModel::class.java)) {
            return IntrodViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") }
}