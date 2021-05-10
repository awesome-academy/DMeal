package com.sunasterisk.dmealfoodapp.data.source.ultils

interface OnDataCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(throwable: Throwable)
}
