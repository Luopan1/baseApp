package www.test720.a360video.network

/**
 * Log统一管理类
 */
class L
/**
 * 不要让任何人实例化这个类。
 */
private constructor() {
    init {
        throw Error("Do not need instantiate!")
    }

    companion object {

        /**
         * 主开关。捕捉错误信息你需要设置这个值低于log.warn
         */
        val DEBUG_LEVEL = 0

        /**
         * 系统L开关。当它是真的时，你可以看到LOG。
         * 否则，你不能。
         */
        val DEBUG_SYSOUT = true

        /**
         * Send a [android.util.Log.VERBOSE] log message.
         *
         * @param obj
         */
        fun v(obj: Any?) {
            if (android.util.Log.VERBOSE > DEBUG_LEVEL) {
                val tag = className
                val msg = obj?.toString() ?: "obj == null"
                android.util.Log.v(tag, msg)
            }
        }

        /**
         * Send a [.DEBUG_LEVEL] log message.
         *
         * @param obj
         */
        fun d(obj: Any?) {
            if (android.util.Log.DEBUG > DEBUG_LEVEL) {
                val tag = className
                val msg = obj?.toString() ?: "obj == null"
                android.util.Log.d(tag, msg)
            }
        }

        /**
         * Send an [android.util.Log.INFO] log message.
         *
         * @param obj
         */
        fun i(obj: Any?) {
            if (android.util.Log.INFO > DEBUG_LEVEL) {
                val tag = className
                val msg = obj?.toString() ?: "obj == null"
                android.util.Log.i(tag, msg)
            }
        }

        /**
         * Send a [android.util.Log.WARN] log message.
         *
         * @param obj
         */
        fun w(obj: Any?) {
            if (android.util.Log.WARN > DEBUG_LEVEL) {
                val tag = className
                val msg = obj?.toString() ?: "obj == null"
                android.util.Log.w(tag, msg)
            }
        }

        /**
         * Send an [android.util.Log.ERROR] log message.
         *
         * @param obj
         */
        fun e(obj: Any?) {
            if (android.util.Log.ERROR > DEBUG_LEVEL) {
                val tag = className
                val msg = obj?.toString() ?: "obj == null"
                android.util.Log.e(tag, msg)
            }
        }

        /**
         * What a Terrible Failure: Report a condition that should never happen. The
         * error will always be logged at level ASSERT with the call stack.
         * Depending on system configuration, a report may be added to the
         * [android.os.DropBoxManager] and/or the process may be terminated
         * immediately with an error dialog.
         *
         * @param obj
         */
        fun wtf(obj: Any?) {
            if (android.util.Log.ASSERT > DEBUG_LEVEL) {
                val tag = className
                val msg = obj?.toString() ?: "obj == null"
                android.util.Log.wtf(tag, msg)
            }
        }

        /**
         * Send a [android.util.Log.VERBOSE] log message.
         *
         * @param tag Used to identify the source of a log message. It usually
         * identifies the class or activity where the log call occurs.
         * @param msg The message you would like logged.
         */
        fun v(tag: String, msg: String) {
            if (android.util.Log.VERBOSE > DEBUG_LEVEL) {
                android.util.Log.v(tag, msg)
            }
        }

        /**
         * Send a [.DEBUG_LEVEL] log message.
         *
         * @param tag Used to identify the source of a log message. It usually
         * identifies the class or activity where the log call occurs.
         * @param msg The message you would like logged.
         */
        fun d(tag: String, msg: String) {
            if (android.util.Log.DEBUG > DEBUG_LEVEL) {
                android.util.Log.d(tag, msg)
            }
        }

        /**
         * Send an [android.util.Log.INFO] log message.
         *
         * @param tag Used to identify the source of a log message. It usually
         * identifies the class or activity where the log call occurs.
         * @param msg The message you would like logged.
         */
        fun i(tag: String, msg: String) {
            if (android.util.Log.INFO > DEBUG_LEVEL) {
                android.util.Log.i(tag, msg)
            }
        }

        /**
         * Send a [android.util.Log.WARN] log message.
         *
         * @param tag Used to identify the source of a log message. It usually
         * identifies the class or activity where the log call occurs.
         * @param msg The message you would like logged.
         */
        fun w(tag: String, msg: String) {
            if (android.util.Log.WARN > DEBUG_LEVEL) {
                android.util.Log.w(tag, msg)
            }
        }

        /**
         * Send an [android.util.Log.ERROR] log message.
         *
         * @param tag Used to identify the source of a log message. It usually
         * identifies the class or activity where the log call occurs.
         * @param msg The message you would like logged.
         */
        fun e(tag: String, msg: String) {
            if (android.util.Log.ERROR > DEBUG_LEVEL) {
                android.util.Log.e(tag, msg)
            }
        }

        /**
         * What a Terrible Failure: Report a condition that should never happen. The
         * error will always be logged at level ASSERT with the call stack.
         * Depending on system configuration, a report may be added to the
         * [android.os.DropBoxManager] and/or the process may be terminated
         * immediately with an error dialog.
         *
         * @param tag Used to identify the source of a log message.
         * @param msg The message you would like logged.
         */
        fun wtf(tag: String, msg: String) {
            if (android.util.Log.ASSERT > DEBUG_LEVEL) {
                android.util.Log.wtf(tag, msg)
            }
        }

        /**
         * Send a [android.util.Log.VERBOSE] log message. And just print method name and
         * position in black.
         */
        fun print() {
            if (android.util.Log.VERBOSE > DEBUG_LEVEL) {
                val tag = className
                val method = callMethodAndLine()
                android.util.Log.v(tag, method)
                if (DEBUG_SYSOUT) {
                    println("$tag  $method")
                }
            }
        }

        /**
         * Send a [.DEBUG_LEVEL] log message.
         *
         * @param object The object to print.
         */
        fun print(`object`: Any?) {
            if (android.util.Log.DEBUG > DEBUG_LEVEL) {
                val tag = className
                val method = callMethodAndLine()
                var content = ""
                if (`object` != null) {
                    content = (`object`.toString() + "                    ----    "
                            + method)
                } else {
                    content = " ##                 ----    $method"
                }
                android.util.Log.d(tag, content)
                if (DEBUG_SYSOUT) {
                    println("$tag  $content  $method")
                }
            }
        }

        /**
         * Send an [android.util.Log.ERROR] log message.
         *
         * @param object The object to print.
         */
        fun printError(`object`: Any?) {
            if (android.util.Log.ERROR > DEBUG_LEVEL) {
                val tag = className
                val method = callMethodAndLine()
                var content = ""
                if (`object` != null) {
                    content = (`object`.toString() + "                    ----    "
                            + method)
                } else {
                    content = " ##                     ----    $method"
                }
                android.util.Log.e(tag, content)
                if (DEBUG_SYSOUT) {
                    System.err.println("$tag  $method  $content")
                }
            }
        }

        /**
         * Print the array of stack trace elements of this method in black.
         *
         * @return
         */
        fun printCallHierarchy() {
            if (android.util.Log.VERBOSE > DEBUG_LEVEL) {
                val tag = className
                val method = callMethodAndLine()
                val hierarchy = callHierarchy
                android.util.Log.v(tag, method + hierarchy)
                if (DEBUG_SYSOUT) {
                    println("$tag  $method$hierarchy")
                }
            }
        }

        /**
         * Print debug log in blue.
         *
         * @param object The object to print.
         */
        fun printMyLog(`object`: Any?) {
            if (android.util.Log.DEBUG > DEBUG_LEVEL) {
                val tag = "MYLOG"
                val method = callMethodAndLine()
                var content = ""
                if (`object` != null) {
                    content = (`object`.toString() + "                    ----    "
                            + method)
                } else {
                    content = " ##                 ----    $method"
                }
                android.util.Log.d(tag, content)
                if (DEBUG_SYSOUT) {
                    println("$tag  $content  $method")
                }
            }
        }

        private val callHierarchy: String
            get() {
                var result = ""
                val trace = Exception().stackTrace
                for (i in 2 until trace.size) {
                    result += ("\r\t" + trace[i].className + ""
                            + trace[i].methodName + "():"
                            + trace[i].lineNumber)
                }
                return result
            }

        private val className: String
            get() {
                var result = ""
                val thisMethodStack = Exception().stackTrace[2]
                result = thisMethodStack.className
                return result
            }

        /**
         * Realization of double click jump events.
         *
         * @return
         */
        private fun callMethodAndLine(): String {
            var result = "at "
            val thisMethodStack = Exception().stackTrace[2]
            result += thisMethodStack.className + ""
            result += thisMethodStack.methodName
            result += "(" + thisMethodStack.fileName
            result += ":" + thisMethodStack.lineNumber + ")  "
            return result
        }
    }

}
