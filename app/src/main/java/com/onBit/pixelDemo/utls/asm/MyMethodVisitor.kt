package com.onBit.pixelDemo.utls.asm

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.ASM5
import org.objectweb.asm.Opcodes.GETSTATIC
import org.objectweb.asm.Opcodes.INVOKEVIRTUAL


class MyMethodVisitor(mv: MethodVisitor?) :
    MethodVisitor(ASM5, mv) {
    override fun visitCode() {
        mv.visitCode()
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        mv.visitLdcInsn("getAge")
        mv.visitMethodInsn(
            INVOKEVIRTUAL,
            "java/io/PrintStream",
            "println",
            "(Ljava/lang/String;)V",
            false
        )
    }

    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
        super.visitMaxs(maxStack + 1, maxLocals)
    }
}