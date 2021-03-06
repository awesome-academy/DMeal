@file:Suppress("DEPRECATION")

package com.sunasterisk.dmealfoodapp.data.source.ultils

import android.os.AsyncTask
import com.sunasterisk.dmealfoodapp.R
import java.lang.Exception

class LoadDataAsync<T, P>(
    private val callback: OnDataCallback<P>,
    private val handler: (T) -> P?
) : AsyncTask<T, Unit, P?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: T): P? =
        try {
            handler(params[0])
        } catch (e: Exception) {
            callback.onFailure(e)
            null
        }

    override fun onPostExecute(result: P?) {
        super.onPostExecute(result)
        result?.let {
            callback.onSuccess(it)
        } ?: callback.onFailure(
            exception ?: Exception((R.string.title_exception).toString())
        )
    }
}
