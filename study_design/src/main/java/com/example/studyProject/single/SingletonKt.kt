package com.example.studyProject.single

class SingletonKt private constructor() {

    companion object {
        @Volatile
        private var instance: SingletonKt? = null

        fun getInstance(): SingletonKt {
            return instance ?: synchronized(this) {
                instance ?: SingletonKt().also {
                    instance = it
                }
            }
        }

    }

}