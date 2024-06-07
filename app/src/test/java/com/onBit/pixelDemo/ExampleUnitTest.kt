package com.onBit.pixelDemo

import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.example.studyProject.studyDesign.observer.Student
import com.example.studyProject.studyKotlin.ABC
import com.example.studyProject.studyKotlin.ABCD
import com.example.studyProject.studyKotlin.Animal
import com.example.studyProject.studyKotlin.Bird
import com.example.studyProject.studyKotlin.Birds
import com.example.studyProject.studyKotlin.Flyer
import com.example.studyProject.studyKotlin.Lambda
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import org.jetbrains.annotations.TestOnly
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() = runBlocking {
        launch {
            var a=0
            val stateIn = flow {
                emit(a + 1)
            }.stateIn(GlobalScope)

            stateIn.collect {
                println("第一次==>$it")
            }
            stateIn.collect {
                println("第二次==>$it")
            }
        }
    }

    @Test
    fun TestChannel() {
        val channel = Channel<Int>()
        //生产者
        val producer = GlobalScope.launch {
            var i = 0
            while (true) {
                delay(1000)
                channel.send(++i)
                println("send $i")
            }
        }

        //消费者
        val consumer = GlobalScope.launch {
            while (true) {
                val element = channel.receive()
                println("receive $element")
                if (element == 3) {
                    channel.close()
                    println("关闭channel")
                }
            }
        }

        runBlocking {
            joinAll(producer, consumer)
        }
    }

    @Test
    fun `test broadcast`() = runBlocking<Unit> {
        val stateFlow = MutableSharedFlow<Int>(replay = 1)
        GlobalScope.launch {
            var a = 0
            while (true) {
                delay(2000)
                a++
                stateFlow.emit(1)
            }
        }
        val launch1 = GlobalScope.launch {
            stateFlow.collectIndexed { index, value ->
                println("第一个监听==>$value")
            }
        }

        val launch2 = GlobalScope.launch {
            stateFlow.collectIndexed { index, value ->
                println("第二个监听==>$value")
            }
        }

        joinAll(launch1, launch2)


    }


    @Test
    fun test1() {
        val launch = GlobalScope.launch {
            //返回flow的flow
            flow {
                List(5) {
                    emit(it)
                }
            }.map {
                flow {
                    List(it) {
                        emit(it)
                    }
                }
            }
                .flatMapConcat {
                    flow {
                        it.collect {
                            emit(it)
                        }
                    }
                }.collect {
                    println("it==>$it")
                }

        }


        runBlocking {
            joinAll(launch)
        }
    }

    interface Computer {
        val cpu: String

        companion object {
            operator fun invoke(type: String): Computer {
                return when (type) {
                    "" -> {
                        PC("ccc")
                    }

                    else -> {
                        PC("")
                    }
                }
            }
        }
    }

    class PC(override val cpu: String) : Computer

    object ComputerFactory {

    }

}