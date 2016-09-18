package com.jayrave.falkon.dao.lib

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.assertj.core.api.Assertions.fail
import org.junit.Test
import java.io.Closeable

class CloseableExtnKtTest {

    @Test
    fun testAutoCloseableClosesOnSuccessfulExecution() {
        val closeableMock = mock<Closeable>()
        closeableMock.safeCloseAfterOp {  }
        verify(closeableMock).close()
        verifyNoMoreInteractions(closeableMock)
    }


    @Test
    fun testAutoCloseableClosesEvenIfExceptionIsThrown() {
        val closeableMock = mock<Closeable>()
        var exceptionWasThrown = false
        try {
            closeableMock.safeCloseAfterOp { throw RuntimeException() }
        } catch (e: RuntimeException) {
            exceptionWasThrown = true
        }

        if (!exceptionWasThrown) {
            fail("exception must have been thrown")
        }

        verify(closeableMock).close()
        verifyNoMoreInteractions(closeableMock)
    }
}