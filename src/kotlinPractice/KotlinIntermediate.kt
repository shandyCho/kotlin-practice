package com.example.kotlinPractice

import com.example.iDontLikePackage.IDontLikePackageSystem
import com.example.iDontLikePackage.extension


fun intermediate() {
    // 확장함수
    // 확장함수를 추가해서 클래스를 확장할 수 잇음
    // 확장함수는 클래스의 멤버 함수를 호출하는 것과 동일한 방식으로 호출할 수 있음
    // 리시버
    // 리시버는 함수가 호출되는 대상으로, 정보가 공유되는 대상 또는 주체로 볼 수 있음

    val readOnlyShape = listOf<String>("One", "Two", "Three")
    println("readOnlyShape.first(): ${readOnlyShape.first()}")
    // readOnlyShape.first(): One
    // first() 함수를 호출하는 것은 intermediate() 이지만,
    // first() 함수는 readOnlyShape에 속해있음
    // 따라서 readOnlyShape의 first()가 호출이 되는 것으로, 함수가 호출이 되는 대상은 readOnlyShape임
    // 즉 first()의 리시버는 readOnlyShape가 됨

    val ins = IDontLikePackageSystem("s", 1, false)
    ins.extension()
    // this is extension

}