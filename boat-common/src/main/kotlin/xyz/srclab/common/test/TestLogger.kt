package xyz.srclab.common.test

import xyz.srclab.common.base.Current
import xyz.srclab.common.base.Format.Companion.printfFormat
import xyz.srclab.common.reflect.contractSignatureName
import java.io.PrintStream

interface TestLogger {

    fun log(message: Any?)

    companion object {

        @JvmField
        val DEFAULT: TestLogger = TestLoggerImpl(System.out)

        fun withPrintStream(printStream: PrintStream): TestLogger {
            return TestLoggerImpl(printStream)
        }

        private class TestLoggerImpl(private val printStream: PrintStream) : TestLogger {
            override fun log(message: Any?) {
                val callFrame = Current.callerFrame(javaClass.name, "log")
                printStream.println(
                    "%${CLASS_NAME_MAX_LENGTH}s(%4d): %s".printfFormat(
                        callFrame?.className?.contractSignatureName(CLASS_NAME_MAX_LENGTH),
                        callFrame?.lineNumber,
                        message
                    )
                )
            }

            companion object {
                private const val CLASS_NAME_MAX_LENGTH = 50
            }
        }
    }
}