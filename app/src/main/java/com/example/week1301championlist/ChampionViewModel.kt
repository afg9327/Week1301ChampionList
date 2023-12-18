package com.example.week1301championlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ChampionViewModel: ViewModel() {
    private val SERVER_URL = "https://port-0-specialback-5yc2g32mlomgpihs.sel5.cloudtype.app/"
    private val championApi: ChampionApi
    private val _championList = MutableLiveData<List<Champion>>()
    val championList : LiveData<List<Champion>> get() = _championList

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        championApi = retrofit.create(ChampionApi::class.java)
        fetchData()
    }
    private fun fetchData(){
        viewModelScope.launch {
            try {
                val response = championApi.getChampions()
                _championList.value = response
            }catch (e: Exception){
                Log.e("fetchData()",e.toString())
            }
        }
    }

}