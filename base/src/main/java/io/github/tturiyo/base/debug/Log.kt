package io.github.tturiyo.base.debug


object Log {
    const val TAG_LENGTHLIMIT = 23

    private val prefix: String
        get() {
            var result: String = ""

            try {
                val calledMethod = Throwable().stackTrace[3]
                result = "[Line${calledMethod.lineNumber}:${calledMethod.methodName}] "
            } catch (ignore: Exception) {
                assertDebug(true)
            }

            return result
        }

    private val tag: String
        get() {
            val ste = Throwable().stackTrace
            val realMethod = ste[3]

            val fileName: String = realMethod.fileName

            return fileName.subSequence(0, Math.min(fileName.length, TAG_LENGTHLIMIT)).toString()
        }

    fun d(msg: String = "") {
        android.util.Log.d(tag, prefix + msg)
    }

    fun d(argTag: String = tag, msg: String) {
        android.util.Log.d(argTag, prefix + msg)
    }
}
