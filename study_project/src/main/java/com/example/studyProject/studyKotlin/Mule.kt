package com.example.studyProject.studyKotlin


open class Horse {
    fun runFast() {
        println("1234")
    }
}

open class Donkey {
    fun doSomeThing() {
        println("doSomeThing")
    }
}

class Mule {
    inner class HorseC : Horse()
    inner class DonkeyC : Donkey()


}

class CovariantList<out T> {
        fun get(index: @UnsafeVariance T): Int {
        return 1
    }

    fun<T> getADB(){

    }
}

inline fun <reified T> getType(): Class<T> {
    return T::class.java
}

interface CanFly {
    fun fly()
}

interface CanEat {
    fun eat()
}

open class Flyer : CanFly {
    override fun fly() {
        println("i can fly")
    }
}

open class Animal : CanEat {
    override fun eat() {
        println("i can eat")
    }

}

/**
 * 委托:实现多继承
 **/
class Bird(flyer: Flyer, animal: Animal) : CanFly by flyer, CanEat by animal

 class ABC {
    object BCD{

    }
}

data class Birds(val weight: Int, val age: Int, val name: String) {
    var sex = 1
    var a = 1
    operator fun component4(): Int {
        return 3
    }
    operator fun component5():Int{
        return 5
    }
    constructor(weight: Int, age: Int, name: String, sex: Int) : this(weight, age, name) {
        this.sex = sex
    }

    constructor(weight: Int, age: Int, name: String, sex: Int, a: Int) : this(weight, age, name) {
        this.a = a
    }
}
