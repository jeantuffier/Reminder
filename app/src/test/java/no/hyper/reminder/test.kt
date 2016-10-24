package no.hyper.reminder

import org.mockito.Mockito

/**
 * Created by jean on 24.10.2016.
 */

fun <T> mockMethod(methodCall: T) = Mockito.`when`(methodCall)
