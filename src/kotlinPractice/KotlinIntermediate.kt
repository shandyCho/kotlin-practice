package com.example.kotlinPractice

import com.example.iDontLikePackage.Canvas
import com.example.iDontLikePackage.IDontLikePackageSystem
import com.example.iDontLikePackage.Menu
import com.example.iDontLikePackage.ThisIsMultipleClassInSameFile
import com.example.iDontLikePackage.WithTestClass
import com.example.iDontLikePackage.extension
import com.example.iDontLikePackage.extension2


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
    println(ins.extension2())
    // <b>com.example.iDontLikePackage.IDontLikePackageSystem@7a81197d</b>


    // 확장지향 설계
    // 뭐만하면 ~지향 설계래 미친ㄴ놈들...

    // 확장함수를 어디서든 정의할 수 있다는 특성을 이용하여 확장 지향 설계가 가능함
    // 핵심 기능과 유용하나 필수적이지는 않은 기능들의 분리를 가능하게 하여 코드의 가독성과 유지보수성을 높여준다
    // 핵심 기능은 클래스 안에 짱박아두고, 나머지는 add-on으로 나눠서 받을 수 있게 한다?
    // rust crate의 feature flag 같은건가


    // Scope functions
    // 자바와 동일하게 전역 스코프와 로컬 스코프 존재함
    // 코틀린에서는 객체 주변에 임시 범위 생성 후 일부 코드를 실행할 수 있는 Scope Function이 존재함
    // Scope Function은 임시 범위 내에서 객체 이름을 참조할 필요가 없기 때문에 코드를 더욱 간결하게 만들 수 있음
    // Scope Function에서 객체에 접근하는 방법은 두가지 있음
    // 1. this 키워드를 통해 참조
    // 2. it 키워드를 통해 인자로 사용
    // 5가지 Scope Function이 존재함
    // let, apply, run, also, with
    // 각 스코프 함수는 람다 표현식을 인수로 받아서 해당 객체나 람다 표현식의 결과를 반환함

    // let Scope Function
    // 코드에서 null 검사를 수행하고 나중에 반환된 객체로 추가 작업을 수행하고자 할 경우 사용함

    fun sendNotification(address: String): String {
        println("send $address")
        return "Notification Sent"
    }

    fun getNextAddress(): String {
        return "NextAddress@address.org"
    }

    var address: String? = getNextAddress()
//    val confirm = sendNotification(address)
    // Argument type mismatch: actual type is 'String?', but 'String' was expected.

    val confirm = if (address != null) {
        sendNotification(address)
    } else { null }
    // let을 사용하지 않고는 if - else 를 사용해서 null 검사를 수행할 수 있다
    address = null
    val confirm2 = address?.let { sendNotification(it) }
    // let 을 사용해서 간단하게 null 검사 후 람다 표현식을 통해 해당 데이터를 사용할 수 있다
    // address에서 null이 반환될 수 있음을 명시적으로 알리며 address에 접근 (address?.)
    // ?. 부분에서 null이 반환될 경우, confirm2는 null이 되고, let 이후의 코드는 실행되지 않음
    // let 함수를 통해 해당 함수의 스코프에서 address의 값을 사용할 수 있음
    // it 을 통해 let 스코프 안에서 address 의 데이터를 사용할 수 있음


    // apply Scope Function
    // 클래스 인스턴스와 같은 객체를 코드 후반부가 아닌 생성 시점에 초기화 할 수 있음
    // 코드의 가독성과 관리 용이성을 높여줌
    val ins1 = ThisIsMultipleClassInSameFile(privateParameter = "PRIVATE_PARAMETER", publicParameter = 3, justParameter = 3.4)
    ins1.memberVariable = "This is not null"
    ins1.memberFunction()
    // PRIVATE_PARAMETER, 3
    // This is not null

    // 일반적으로는 클래스 생성 후 변수에 할당 -> 멤버 프로퍼티들 세팅 작업을 하나, apply 함수로 이를 연결할 수 있음

    val ins2 = ThisIsMultipleClassInSameFile(privateParameter = "qwerty", publicParameter = 1, justParameter = -1)
        .apply {
            memberVariable = "This is allocated in apply block"
        }

    ins2.memberFunction()
    // qwerty, 1
    // This is allocated in apply block

    // apply 함수를 통해서 인스턴스 생성 직후 해당 인스턴스에서 해줄 작업들을 연달아 할 수 있게끔 해줄 수 있는듯

    // run Scope Function
    // run은 apply와 마찬가지로 인스턴스 생성 직후 사용할 수 있으나,
    // 코드의 특정 시점에 객체를 초기화 하고 즉시 결과를 계산할 때 사용할 수 있음
    println("======================================================================================")
    val ins3 = ThisIsMultipleClassInSameFile(privateParameter = "qazwsx", publicParameter = 10, justParameter = 5.333221)
    .apply {
        memberVariable = "2......This is allocated in apply block"
    }

    val ins3Result = ins3.run {
        memberFunction()
        memberFunction2()
    }
    println("ins3Result: $ins3Result")
    /*
    memberFunction
    qazwsx, 10
    2......This is allocated in apply block
    memberFunction2
    justParameterCopy: 5.333221
    ins3Result: privateParameter: qazwsx
    publicParameter: 10
    memberVariable: 2......This is allocated in apply block
     */

    /*
        also Scope Function
        객체에 대한 추가 작업을 완료한 뒤 해당 객체를 반환하여 코드에서 계속 사용하도록 할 수 있음
        로그를 작성하는 경우가 예시가 될 수 있음
     */

    val medals = listOf<String>("Gold", "Silver", "Bronze")
    val reversedLongUpperCaseMedals: List<String> =
        medals
            .map { it.uppercase() }
            .filter { it.length > 4 }
            .reversed()

    println(reversedLongUpperCaseMedals)


    val medals2 = listOf<String>("Gold", "Silver", "Bronze")
    val reversedLongUpperCaseMedals2: List<String> =
        medals2
            .map { it.uppercase() }
            .also(::println)
            .filter { it.length > 4 }
            .also { println(it) }
            .also { it.map { m -> StringBuilder(m).append("2") } }
            .reversed()

    println(reversedLongUpperCaseMedals2)

    /*
    [GOLD, SILVER, BRONZE]
    [SILVER, BRONZE]
    [BRONZE, SILVER]
    also는 작업 도중의 데이터를 받고 다시 해당 데이터를 돌려주기 때문에
    also 블럭 안에서 데이터를 변경해도 해당 데이터는 also 안에서만 변경이 적용되고, 다음 과정에서는 적용되지 않는다
     */


    /*
    with Scope Function
    with는 확장함수가 아니기 때문에 receiver.확장함수() 의 형태로 사용하지 않음
    with의 매개변수로 receiver를 전달함
    객체에 대해서 여러 함수를 호출하려는 경우 with Scope Function을 사용함
     */

    val withTestClassInstance = WithTestClass(conVal1 = "constructor value 1 ", conVal2 = "coval2 ", conVal3 = 5)
    withTestClassInstance.mf1()
    withTestClassInstance.mf2()
    withTestClassInstance.mf3()
    withTestClassInstance.mf4(3)

    val withTestClassInstance2 = WithTestClass(conVal1 = "okmijn ", conVal2 = "yhbygb ", conVal3 = 10)
    with(withTestClassInstance2) {
        mf1()
        mf2()
        mf3()
        mf4(9)
    }

    /*
    member1: constructor value 1
    member2: coval2
    member3: 5
    result constructor value 1 constructor value 1 constructor value 1 constructor value 1
    member1: okmijn
    member2: yhbygb
    member3: 10
    result okmijn okmijn okmijn okmijn okmijn okmijn okmijn okmijn okmijn okmijn
     */

    val withResult = with(withTestClassInstance2) {
        mf1()
        mf2()
        mf3()
        mf4(9)
        mf5()
    }
    println("withResult: $withResult")
    // withResult: yhbygb 10

    /*
        정리
        let: receiver 접근은 it 으로 하고 람다 표현식의 결과를 반환한다 / 코드에서 null 검사를 수행하고 반환 값으로 추가적인 작업을 진행한다
        apply: receiver 접근은 this로 하고, receiver를 반환한다 / 객체가 생성되는 즉시 초기화 한다
        run: receiver 접근은 this로 하고, 람다 표현식의 결과를 반환한다 / 객체를 생성할 때 초기화 하고 결과값을 계산하여 반환한다
        also: receiver 접근은 it 으로 하고, receiver를 반환한다 / 객체를 반환하기 전에 추가적인 작업을 완료한다
        with: receiver 접근은 this로 하고, 람다 표현식의 결과를 반환한다 / 객체에 대해 여러 함수를 호출한다
     */

    // 람다 표현식과 리시버
    /*
        람다 표현식도 리시버를 가질 수 있다
        이 경우, 람다 표현식은 매번 리시버를 명시적으로 지정해주지 않고도 리시버의 모든 멤버함수나 속성에 접근할 수 있음
        리시버가 있는 람다 표현식의 구문은 함수 유형을 정의할 때와는 다름
        먼저 확장하려는 리시버를 작성하고, . 을 붙인 다음 나머지 함수 유형 정의를 완성함
     */

    fun render(block: Canvas.() -> String): Canvas {
        val canvas = Canvas()
        canvas.block()
        return canvas
    }
    val rr1 = render {
        prepareTools("green blue")
        drawCircle()
        drawSquare()
        whatIsColorOfCanvas()
    }

    val rr2 = render({
        this.prepareTools("red orange")
        this.drawCircle()
        this.drawSquare()
        this.whatIsColorOfCanvas()
    })
    println("rr1: $rr1, rr2: $rr2")
    /*
        Tool is Already
        ㅇ drawing a circle
        ㅁ drawing a square
        rr1: com.example.iDontLikePackage.Canvas@38af3868, rr2: com.example.iDontLikePackage.Canvas@77459877
     */

    println("======================================================")
    fun render2(block: Canvas.() -> Unit): String {
        val canvas = Canvas()
        canvas.prepareTools("red orange")
        canvas.block()
        return canvas.whatIsColorOfCanvas()
    }
    val rr12 = render2 {
        prepareTools("green blue")
        drawCircle()
        drawSquare()
    }
    val rr22 = render2({
        this.drawCircle()
        this.drawSquare()
    })
    println("rr12: $rr12, rr22: $rr22")
    /*
        Tool is Already
        Tool is Already
        ㅇ drawing a circle
        ㅁ drawing a square
        Tool is Already
        ㅇ drawing a circle
        ㅁ drawing a square
        rr12: Canvas`s color is green blue, rr22: Canvas`s color is red orange
     */

    /*
        리시버를 가진 람다 표현식은 도메인 특정 언어 (Domain-Specific Language) 생성시 유용함
        리시버를 명시적으로 참조하지 않고도 리시버의 멤버 함수와 속성에 접근할 수 있어서 코드가 간결해짐
     */

    fun menu(name: String, init: Menu.() -> Unit): Menu {
        val menu = Menu(name)
        menu.init()
        return menu
    }
    // menu 함수를 실행시키면... 아무 일도 안 일어나지

    fun printMenu(menu: Menu) {
        println("Menu: ${menu.name}")
        menu.items.forEach({it -> println("  Item: ${it.name}")})
    }

    val mainMenu = menu("Main Menu") {
        item("Home")
        item("Settings")
        item("exit")
    }

    printMenu(mainMenu)
    /*
     Menu: Main Menu
      Item: Home
      Item: Settings
      Item: exit
     */
    /*
        리시버를 가진 람다 표현식은 Kotlin의 타입 안전 빌더와 결합하여
        런타임이 아닌 컴파일 시점에 타입 문제를 감지하는 DSL을 작성할 수 있음
     */

}