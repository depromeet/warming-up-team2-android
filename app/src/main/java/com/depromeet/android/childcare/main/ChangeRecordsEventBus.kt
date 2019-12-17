package com.depromeet.android.childcare.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.Event

// ViewModel 을 Event Bus 처럼 사용
class ChangeRecordsEventBus: ViewModel() {

    private val _changeRecordsEvent = MutableLiveData<Event<Unit>>()
    val changeRecordsEvent: LiveData<Event<Unit>>
        get() = _changeRecordsEvent

    // Trigger the event update
    fun triggerEvent() {
        _changeRecordsEvent.postValue(Event(Unit))
    }
}