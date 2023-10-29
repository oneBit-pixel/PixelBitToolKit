package com.example.studyProject.studyDesign.observer

import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

interface StockUpdateListener {
    fun onRise(price: Int)

    fun onFall(price: Int)
}

class StockDisplay : StockUpdateListener {
    override fun onRise(price: Int) {
        println("onRise==>$price")
    }

    override fun onFall(price: Int) {
        println("onFall==>$price")
    }

}

class StockUpdate {

    var listener = mutableListOf<StockUpdateListener>()

    var price: Int by Delegates.observable(0) { _, old, new ->
        listener.forEach {
            if (new > old) {
                it.onRise(price)
            } else {
                it.onFall(price)
            }
        }
    }

    var value: Int by Delegates.vetoable(0) { property: KProperty<*>, oldValue: Int, newValue: Int ->
        //如果不大于0则否决他
        newValue > 0
    }
}

fun freestyleSwim() {
    println("Swimming freestyle")
}

fun breaststrokeSwim() {
    println("Swimming breaststroke")
}

fun butterflySwim() {
    println("Swimming butterfly")
}

class Swimmer(private val swimStrategy: () -> Unit) {
    fun swim() {
        swimStrategy()
    }
}