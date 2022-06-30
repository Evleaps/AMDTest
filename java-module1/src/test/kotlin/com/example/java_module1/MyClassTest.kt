package com.example.java_module1

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MyClassTest {

    @Test
    fun test1() {
        Thread.sleep(5000)
        println("ROMAN: java module 1")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test2() {
        Thread.sleep(5000)
        println("ROMAN: java module 2")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test3() {
        Thread.sleep(5000)
        println("ROMAN: java module 3")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test4() {
        Thread.sleep(5000)
        println("ROMAN: java module 4")
        assertEquals(4, 2 + 2)
    }
}