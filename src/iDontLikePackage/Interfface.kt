package com.example.iDontLikePackage

import kotlin.math.absoluteValue

interface Interfface {
    val mem1: Int
    val mem2: String
    fun mfun1()
    fun mfun2(berg: String, berg2: Int): String
}

interface Interfface2 {
    val mem3: String
    val mem4: MutableList<String>
    fun mfun3() {println("호출 가능함?")}
    fun mfun4(): List<String>
}


class InheritedClassFromInterface() : Interfface {
    override var mem1: Int = 0
    override var mem2: String = ""

    constructor(p1: Int, p2: String) : this() {
        mem1 = if (p1 >= 0) p1 else p1.absoluteValue
        mem2 = p2.toCharArray()
            .map ({ x: Char ->
                if (x.isLowerCase()) x.uppercaseChar() else x.lowercaseChar()
            })
            .joinToString("")
    }

    override fun mfun1() {
        TODO("Not yet implemented")
    }
    override fun mfun2(berg: String, berg2: Int): String {
        prvtfn()
        return "${berg}burg $berg2"
    }

    private fun prvtfn() {
        println("mem1: $mem1 | mem2: $mem2")
    }

}

// 두개 이상의 인터페이스를 상속 받을 경우 그 인터페이스의 요소들을 모두 구현해줘야 한다
class DoubleInheritedClassFromInterface(p1: Int, p2: String, p3: String, p4: MutableList<String>): Interfface, Interfface2 {

    override var mem1: Int = p1.absoluteValue
    override var mem2: String = p2.toCharArray().map({x -> if (x.isLowerCase()) x.uppercaseChar() else x.lowercaseChar()}).joinToString("")
    override var mem3: String = p3.reversed()
    override var mem4: MutableList<String> = preCoordinate(p4)


    private fun preCoordinate(p4: MutableList<String>): MutableList<String> {
        // 클래스 매개변수 p4에서 대문자가 포함된 String이 있으면 해당 String은 리스트에서 지우기
        p4.removeIf {
                x -> x.toCharArray() // mutableList의 원소 String을 charArray로 만들기
            .map { c -> c.isUpperCase()} // charArray의 원소 c에 대하여 대문자인지 판별
            .contains(true)
        }
        return p4
    }

    override fun mfun1() {
        val v = "mem1: $mem1 \nmem2: $mem2 \nmem3: $mem3 \nmem4: $mem4"
        println(v)
    }
    override fun mfun2(berg: String, berg2: Int): String {
        prvtfn()
        return "${berg}burg $berg2"
    }

    private fun prvtfn() {
        println("mem1: $mem1 | mem2: $mem2")
    }

//    fun mfun3() {println("호출 가능함?")}
    override fun mfun4(): List<String> {
        return mem4.toList()
    }
}

interface Delegation {
    val v1: String
    fun f1 ()
    fun f2 ()
    fun f3 ()
    fun f4 ()
}

class DelegatedClass1: Delegation {
    override val v1: String = "SSSSSSSSSSSSSSSSSSS"
    override fun f1() {
        println("v1: $v1")
    }
    override fun f2() {
        println("delegatedClass2")
    }
    override fun f3() {
        println("delegatedClass3")
    }
    override fun f4() {
        println("delegatedClass4")
    }
}

class DelegatedClass1_1: Delegation {
    override val v1: String = "RRRRRRRRRRRRRRR"
    override fun f1() {
        println("v1 1_!_!_!_!_!_!_!___!_!_!__!_!_!_!_!__!_!_: $v1")
    }
    override fun f2() {
        println("delegatedClass2_1")
    }
    override fun f3() {
        println("delegatedClass3_1")
    }
    override fun f4() {
        println("delegatedClass4_1")
    }
}

class DelegatedClass2 (val delegation: Delegation): Delegation {
    override val v1: String = "FFFFFFFFF"
    override fun f1() {
        delegation.f1()
    }
    override fun f2() {
        delegation.f2()
    }
    override fun f3() {
        delegation.f3()
    }
    override fun f4() {
        delegation.f4()
    }
}

// 위임을 통해 매개변수로 받은 구현체에 override 할 요소 이외의 구현 책임을 넘길 수 있음
class DelegatedClass3 (val delegation: Delegation): Delegation by delegation {
    override val v1: String = "FFFFFFFFF"
    override fun f1() {
        println("IT IS DELEGATED")
    }
}