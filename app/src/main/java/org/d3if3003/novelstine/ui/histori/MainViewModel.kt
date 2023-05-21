package org.d3if3003.novelstine.ui.histori

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3003.novelstine.R
import org.d3if3003.novelstine.model.Novel

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<List<Novel>>()
    init {
        data.value = initData()
    }

    private fun initData(): List<Novel> { return listOf(
        Novel("Dilan 1990","Pidi Baiq",
            "Romance", 2014,  R.drawable.novel_dilan_1990),
        Novel("Danur","Risa Saraswati",
            "Horor", 2011, R.drawable.novel_danur),
        Novel("Mariposa","Luluk HF", "Romance",
            2018,R.drawable.mariposa),
        Novel("Kisah Tanah Jawa","Mada Zidan", "Horor",
            2018,R.drawable.novel_kisah_tanah_jawa),
        Novel("00.00","Anugrah Ameylia Falensia", "Romance",
            2021,R.drawable.novel_sad_ending),
    )
    }
    fun getData(): LiveData<List<Novel>> = data
}
