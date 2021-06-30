package com.gsgroup.hrapp.util

import kotlinx.coroutines.*
import timber.log.Timber

/**
 * Created by MahmoudAyman on 7/15/2020.
 **/


class KtCoroutine {

    companion object {
        fun delayJob(delayInSeconds: Long, action: () -> Unit): Job {
            return GlobalScope.launch {
                delay(delayInSeconds * 1000)
                action.invoke()
            }
        }

        fun repeatJob(interval:Int, action: (Int) -> Unit):Job{
            return GlobalScope.launch {
                repeat(interval, action)
            }
        }

    }
}