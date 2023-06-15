package org.d3if3003.novelstine.ui.novel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3003.novelstine.R
import org.d3if3003.novelstine.model.Novel
import org.d3if3003.novelstine.network.NovelApi

class NovelViewModel : ViewModel() {

    private val data = MutableLiveData<List<Novel>>()
    private val status = MutableLiveData<NovelApi.ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(NovelApi.ApiStatus.LOADING)
        try {
            data.postValue(NovelApi.service.getNovel())
            status.postValue(NovelApi.ApiStatus.SUCCESS)
        } catch (e: Exception) {
            Log.d("MainViewModel", "Failure: ${e.message}")
            status.postValue(NovelApi.ApiStatus.FAILED)
        }
    }
    }

    fun getData(): LiveData<List<Novel>> = data
    fun getStatus(): LiveData<NovelApi.ApiStatus> = status

}