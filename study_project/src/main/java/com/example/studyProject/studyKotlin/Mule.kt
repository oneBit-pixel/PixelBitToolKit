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