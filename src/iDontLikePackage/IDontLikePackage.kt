package com.example.iDontLikePackage

class IDontLikePackageSystem(val classParameter1: String, var classParameter2: Int, classParameter3: Boolean) {

//    val classParameter1 = classParameter1
//    val classParameter2 = classParameter2
    var classParameter3Copy = classParameter3



    fun iHate(message: String, prefix: String) :String {
        println("$prefix $message, classParameter3Copy: $classParameter3Copy")
        return "Hate you JVM"
    }

    fun useClassParameter() {
        println("$classParameter1, $classParameter2")
    }

}


class ThisIsMultipleClassInSameFile (private val privateParameter: String, var publicParameter: Int, justParameter: Any) {
    var memberVariable: String? = null
    val justParameterCopy = justParameter
    fun memberFunction() {
        println("memberFunction")
        println("$privateParameter, $publicParameter")
        memberVariable?.let { println(it) }
    }
    fun memberFunction2(): String {
        println("memberFunction2")
        println("justParameterCopy: $justParameterCopy")
        return "privateParameter: $privateParameter\npublicParameter: $publicParameter\nmemberVariable: $memberVariable"
    }

}



data class ThisIsDataClass (val data: String, var privateData: String)

fun test2222 () {println("이게된다고 와 짱 좋다 이제 함수 하나 만든답시고 파일 하나 생성할 필요가 없네")}


class WithTestClass {
    private val member1: String
    private val member2: String
    private val member3: Int

    constructor(conVal1: String, conVal2: String, conVal3: Int) {
        member1 = conVal1
        member2 = conVal2
        member3 = conVal3
    }

    fun mf1() {
        println("member1: $member1")
    }
    fun mf2() {
        println("member2: $member2")
    }
    fun mf3() {
        println("member3: $member3")
    }

    fun mf4 (count: Int) {
        var result: String = ""
        for (i in 0..count) {
            result += member1
        }
        println("result $result")
    }
    fun mf5 (): String {
        return member2 + member3
    }
}


class Canvas {
    private var pallete = ""
    fun drawCircle() = println("ㅇ drawing a circle")
    fun drawSquare() = println("ㅁ drawing a square")
    fun prepareTools (pallete: String) {
        this.pallete = pallete
        println("Tool is Already")
    }
    fun whatIsColorOfCanvas(): String {
        return "Canvas`s color is $pallete"
    }
}


class MenuItem(val name: String)

class Menu(val name: String) {
    val items = mutableListOf<MenuItem>()

    fun item(name: String) {
        items.add(MenuItem(name))
    }
}