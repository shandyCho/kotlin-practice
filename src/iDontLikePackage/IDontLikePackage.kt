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

}



data class ThisIsDataClass (val data: String, var privateData: String)

fun test2222 () {println("이게된다고 와 짱 좋다 이제 함수 하나 만든답시고 파일 하나 생성할 필요가 없네")}