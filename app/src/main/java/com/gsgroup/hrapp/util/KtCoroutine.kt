package com.gsgroup.hrapp.util

import kotlinx.coroutines.*
import timber.log.Timber

/**
 * Created by MahmoudAyman on 7/15/2020.
 **/


class KtCoroutine {

    companion object {
        fun delayJob(delayInSeconds: Long): Job {
            return GlobalScope.launch {
                delay(delayInSeconds * 1000)
                Timber.e("run sepcific action")
            }
        }

        fun repeatJob(interval:Int, action: (Int) -> Unit):Job{
            return GlobalScope.launch {
                repeat(interval, action)
            }
        }

    }
}