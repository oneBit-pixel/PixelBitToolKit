package com.example.studyProject.studyDesign.builder

class Root(
    val code: String,
    val battery: String,
    val width: Int? = null,
    val height: Int? = null
) {
    init {
        if (width != null && height != null) {
            require(height > width) {
                "asdfasfd"
            }
        }
    }
}