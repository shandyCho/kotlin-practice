package com.example.iDontLikePackage

// 매개변수에 기본 값을 설정하여 해당 매개변수에 값이 할당되지 않았을 때, 기본적으로 할당될 값을 지정해줄 수 있음
fun whatThe (message: String, count:Int, defalutValuee: String = "default") :String {
    // 매개변수명을 호출할 때 사용할 수 있음
    println("$message count is $count")
    println("defalutValuee is $defalutValuee")
    return "is it possible?"
}

// 아무것도 반환 안하는줄 알지만 Unit 타입을 반환함
// 코틀린의 함수는 기본적으로 return이 생략되고 return type이 명시되지 않으면 Unit을 반환함
fun returnUnitType () {
    println("If function do not declared return type, it will be returned Unit Type")
}

// 간단한 함수의 경우 {} 블럭 없이도 함수 작성 가능함
// return type은 타입 추론으로 정해지나, 가독성을 위해서 return type 명시하는 것이 좋음
// 또한 함수 본문이 {} 로 둘러싸일 경우, Unit 타입 반환할 거 아니면 무조건 반환 타입 선언해야함
fun singleExpressionFunction (str: String, vec: Int) :String = str + vec.toString()

// 함수에서 early return 사용 가능함


fun toSecond (time: String): (Int) -> Int = when (time) {
    "hour" -> {value -> value * 60 * 60}
    "minute" -> {value -> value * 60}
    "second" -> {value -> value}
    else -> {value -> value}
}

