package com.example.iDontLikePackage

abstract class AbstractClass1(val param1: String, var param2: String) {
    val member1: Int = 0
    abstract val member2: Int
    fun action1() {
        println("action1")
    }
    abstract fun action2()
}


class InheritedClass1 (param1: String, val param3: String, param2: String) : AbstractClass1(param1, param2) {
    override val member2: Int = 1
    override fun action2() {
        println("Inherited Class's Action2")
    }
}

class InheritedClass2 (param1: String, val param3: String, param2: String) : AbstractClass1(param1, param2) {
    override val member2: Int = 30
    override fun action2() {
        println("Inherited Class's Action1313113 | param3: $param3")
    }
    fun inheritedClassFunction() {
        println("Inherited Class's InheritedClassFunction")
    }
    // 부모 클래스의 생성자 매개변수에 더해서 자식 클래스만의 생성자 매개변수가 추가될 수 있다
    // 함수 등의 추가도 가능하다 물론 부모 클래스에서는 자식 클래스 것을 못쓴다
//    override val member1: Int = 42
    // 부모 클래스의 추상 요소가 아닌 요소를 override 하고 싶으면 부모 클래스의 요소에 open 키워드를 붙여줘야 한다

//    override fun action1() {
//        println("Inherited Class's Action1313113 | param3: $param3")
//    }
    // 이는 부모 클래스의 함수를 override 하고자 할 때도 동일하게 적용되는 규칙이다
}