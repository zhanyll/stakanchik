package com.example.stakanchik.ui.base

sealed class BaseEvent {
    class ShowToast(val message: String): BaseEvent()
}

sealed class AuthEvent: BaseEvent() {
    object OnAuthSuccess: BaseEvent()
    class ShowTimer(val time: Long): BaseEvent()
}