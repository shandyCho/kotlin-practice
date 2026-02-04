package com.example.iDontLikePackage

// 확장함수
fun IDontLikePackageSystem.extension() {
    println("this is extension")
    // 확장대상 클래스명.확장함수명 의 형태로 지정이 가능하다
    // 확장함수를 사용할 곳, 확장함수의 대상이 되는 확장 대상 클래스와 다른 패키지에 있어도 됨
}

fun  IDontLikePackageSystem.extension2(): String = "<b>$this</b>"
// this는 현재의 receiver를 나타내는 키워드다