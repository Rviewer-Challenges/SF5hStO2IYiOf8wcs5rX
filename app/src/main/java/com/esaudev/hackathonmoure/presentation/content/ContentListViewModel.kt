package com.esaudev.hackathonmoure.presentation.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esaudev.hackathonmoure.data.local.ContentTopics
import com.esaudev.hackathonmoure.domain.model.SingleContentTopic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentListViewModel @Inject constructor(

) : ViewModel() {

    private val _contentList: MutableLiveData<List<SingleContentTopic>> = MutableLiveData()
    val contentList: LiveData<List<SingleContentTopic>>
        get() = _contentList

    init {
        getContentList()
    }

    private fun getContentList() {
        viewModelScope.launch {
            _contentList.value = ContentTopics.contentTopicList
        }
    }



}