package com.onBit.pixelDemo

import com.example.studyProject.studyDesign.observer.Student
import com.example.studyProject.studyKotlin.ABC
import com.example.studyProject.studyKotlin.ABCD
import com.example.studyProject.studyKotlin.Animal
import com.example.studyProject.studyKotlin.Bird
import com.example.studyProject.studyKotlin.Birds
import com.example.studyProject.studyKotlin.Flyer
import com.example.studyProject.studyKotlin.Lambda
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test1(){
        val birds = Birds(123, 321, "123",123)
        val (_, _, _, sex,a) = birds // 使用解构声明
        println("Sex: $a")


    }
}