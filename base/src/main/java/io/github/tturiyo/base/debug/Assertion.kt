package io.github.tturiyo.base.debug

import io.github.tturiyo.base.BuildConfig

fun assertDebug(condition: Boolean) {
    if (BuildConfig.DEBUG) {
        assert(condition)
    }
}
