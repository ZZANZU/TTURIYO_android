package io.github.tturiyo.base.debug

import io.github.tturiyo.base.BuildConfig

fun assertDebug(condition: Boolean, msg: String = "") {
    if (BuildConfig.DEBUG) {
        assert(condition, { msg })
    }
}
