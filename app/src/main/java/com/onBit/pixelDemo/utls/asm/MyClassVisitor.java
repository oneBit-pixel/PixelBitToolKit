package com.onBit.pixelDemo.utls.asm;

import static org.objectweb.asm.Opcodes.ASM5;

import com.onBit.pixelDemo.utls.asm.MyMethodVisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        if(name.equals("getAge")){
            return new MyMethodVisitor(methodVisitor);
        }else {
            return methodVisitor;
        }
    }
}
