package com.example.iDontLikePackage

object CreatedObject {
    var m1: String = ""
    private var m2: Int = 2

    fun objectFunction (rrr: String) {
        println("와 짱신기해요 $rrr $m2 근데 Object라고 따로 분리를 시켜버렸네?")
    }
}

object CreatedObject2 {
    var m1: String = ""
    private var m2: Int = 2

    fun objectFunction (rrr: String) {
        println("와 짱신기해요 $rrr $m2 근데 Object라고 따로 분리를 시켜버렸네?")
    }
}

object CreatedObject3 {
    var m1: String = ""
    private var m2: Int = 2

    fun objectFunction (rrr: String) {
        println("와 짱신기해요 $rrr $m2 근데 Object라고 따로 분리를 시켜버렸네?")
    }
}

data object DataOject {
    var m1: String = "EEEEEE"
    var m2: Int = 2
}

data object DataOject2 {
    var m1: String = "EEEEEE"
    var m2: Int = 2
}


class WithCompanionObject {
    companion object Obj{
        fun printString (str: String) {
            println("String: $str")
        }
    }
}