package com.citymobil.amdtest

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test1() {
        Thread.sleep(2000)
        println("ROMAN: app module 1")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test2() {
        Thread.sleep(2000)
        println("ROMAN: app module 2")
        assertEquals(4, 2 + 2)
    }
}