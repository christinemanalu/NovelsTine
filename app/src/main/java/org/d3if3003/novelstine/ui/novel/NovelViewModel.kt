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
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
        try {
            data.postValue(NovelApi.service.getNovel())
            Log.d("tes", "${NovelApi.service.getNovel()}")
        } catch (e: Exception) {
            Log.d("NovelViewModel", "Failure: ${e.message}")
        }
    }
    }

    fun getData(): LiveData<List<Novel>> = data
}
