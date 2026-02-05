package com.example.kotlinPractice

import com.example.iDontLikePackage.AbstractClass1
import com.example.iDontLikePackage.Canvas
import com.example.iDontLikePackage.CreatedObject
import com.example.iDontLikePackage.CreatedObject2
import com.example.iDontLikePackage.CreatedObject3
import com.example.iDontLikePackage.DataOject
import com.example.iDontLikePackage.DataOject2
import com.example.iDontLikePackage.DelegatedClass1
import com.example.iDontLikePackage.DelegatedClass1_1
import com.example.iDontLikePackage.DelegatedClass2
import com.example.iDontLikePackage.DelegatedClass3
import com.example.iDontLikePackage.DoubleInheritedClassFromInterface
import com.example.iDontLikePackage.IDontLikePackageSystem
import com.example.iDontLikePackage.InheritedClass1
import com.example.iDontLikePackage.InheritedClass2
import com.example.iDontLikePackage.InheritedClassFromInterface
import com.example.iDontLikePackage.Menu
import com.example.iDontLikePackage.ThisIsMultipleClassInSameFile
import com.example.iDontLikePackage.WithCompanionObject
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


    // 클래스 상속
    /*
        기본적으로 Kotlin 클래스는 상속될 수 없음
        의도치 않은 상속을 방지하고 클래스 유지관리의 용이함을 위하여 이렇게 설계함
        코틀린 클래스는 단일 상속만 지원함 -> 자바랑 똑같음
        모든 클래스는 Any 라는 클래스의 하위 클래스임
        Any 클래스는 toString() 함수를 멤버 함수로 자동으로 제공함
        상속받은 클래스를 다시 상속받는 방식으로 클래스의 계층화를 이룰 수 있음
     */
    // 추상 클래스
    /*
        클래스간의 일부 코드를 공유하기 위해 상속을 사용하려는 경우 (클래스 구성 코드의 중복 방지) 추상 클래스 사용을 고려
        추상 클래스는 기본적으로 상속될 수 있음
        추상 클래스의 목적이 다른 클래스가 상속하거나 구현할 멤버를 제공하는 것이기 때문임
        따라서 생성자를 가질 수 있으나 이를 통해 인스턴스를 생성할 수는 없음
        자식 클래스 내에서 override 키워드 사용해서 부모 클래스의 속성과 동작을 재정의 할 수 있음
        추상 클래스는 구현이 있는 함수와 속성 뿐만 아니라 구현이 없는 함수와 속성(추상함수와 추상 속성으로 알려져있음?) 을 모두 포함할 수 있음
        구현이 없는 함수와 속성을 선언하기 위해서는 abstract 키워드를 사용할 수 있음
        단일 상속만 지원하기 때문에 여러 소스에서의 상속이 필요한 경우 Interface를 사용해야함
     */
//    val abstClass = AbstractClass1(param1 = "I", param2 = "N")
    // Cannot create an instance of an abstract class.

    val inheritedInstance1 = InheritedClass1(param1 = "I", param2 = "N", param3 = "H")
    println("inheritedInstance1.member2: ${inheritedInstance1.member2}")
    inheritedInstance1.action1()
    inheritedInstance1.action2()
    /*
        inheritedInstance1.member2: 1
        action1
        Inherited Class's Action2
     */

    val inheritedInstance2 = InheritedClass2(param1 = "E", param2 = "R", param3 = "I")
    println("inheritedInstance2.member2: ${inheritedInstance2.member2}")
    inheritedInstance2.action1()
    inheritedInstance2.action2()
    inheritedInstance2.inheritedClassFunction()
    /*
        inheritedInstance2.member2: 30
        action1
        Inherited Class's Action1313113 | param3: I
        Inherited Class's InheritedClassFunction
     */


    /*
        Interface
        인터페이스는 클래스와 유사하나 몇가지 차이점이 존재함
        1. 인터페이스의 인스턴스를 생성할 수 없음 -> 생성자나 헤더가 존재하지 않음
        2. 인터페이스에 정의된 함수와 속성은 암시적으로 상속 가능함 (open 키워드가 암묵적으로 붙어있음)
        3. 함수의 구현을 제공하지 않는 경우에도 함수를 abstract 키워드를 붙여서 선언할 필요가 없음
        클래스가 인터페이스를 나중에 상속 받고 해당 클래스 안에서 구현할 수 있는 함수와 속성의 집합을 정의함
        이는 구체적인 구현 세부 사항보다는 인터페이스가 설명하는 추상화에 집중할 수 있게 됨
        인터페이스 사용 시 코드가 얻는 이점은 다음과 같음
        1. 모듈화 정도가 강해져서 서로 다른 부분을 분리하므로 각 부분이 독립적으로 발전할 수 있음
        2. 관련 기능을 응집력 있는 세트로 묶어 이해하기 쉬움
        3. 테스트를 위해 구현체를 모의 객체로 빠르게 교체할 수 있어 테스트가 용이함
        인터페이스는 다중 상속을 지원하므로, 클래스가 한 번에 여러 인터페이스를 구현할 수 있음
        인터페이스의 상속은 추상 클래스의 상속과 비슷하나, () 를 사용하지 않음 -> 생성자가 없기 때문임
        인터페이스에 함수 구현 가능함
     */

    val inheritedCalssFromInterface1 = InheritedClassFromInterface(p1 = 30, p2 = "It is Parameter ValuE")
    val inheritedCalssFromInterface2 = InheritedClassFromInterface(p1 = -540, p2 = "iT IS pRAMETER vALUe")

    val callResult1 = inheritedCalssFromInterface1.mfun2(berg = "Ham", berg2 = 433)
    val callResult2 = inheritedCalssFromInterface2.mfun2(berg = "Guten", berg2 = 1211)
    // mem1: 30 | mem2: iT IS pARAMETER vALUe
    // mem1: 540 | mem2: It is Prameter ValuE

    println("callResult1: $callResult1, callResult2: $callResult2")
    // callResult1: Hamburg 433, callResult2: Gutenburg 1211
    val paramMutableList = mutableListOf<String>("String", "lowercase", "UPPERCASE", "inverse", "Average", "Leverage", )
    val doubleInherited = DoubleInheritedClassFromInterface(p1 = 30, p2 = "It is Parameter ValuE", p3 = "It will be reverse", p4 = paramMutableList)
    doubleInherited.mfun1()
    val createBergResult = doubleInherited.mfun2(berg = "Regens", berg2 = 433)
    doubleInherited.mfun3()
    val cre4teListResult = doubleInherited.mfun4()
//    mem1: 30
//    mem2: iT IS pARAMETER vALUe
//    mem3: esrever eb lliw tI
//    mem4: [lowercase, inverse]
//    mem1: 30 | mem2: iT IS pARAMETER vALUe
//    호출 가능함?

    println("createBergResult : $createBergResult, \ncre4teListResult : $cre4teListResult")
//    createBergResult : Regensburg 433,
//    cre4teListResult : [lowercase, inverse]






    // Delegation (위임)
    /*
        인터페이스는 유용하나, 함수가 너무 많을 경우 하위 클래스에 반복되는 코드가 많이 생길 수 있음
        클래스 동작의 일부만 재정의 하고 싶을 때에도 반복 작업이 많이 필요해짐
     */
    val d1 = DelegatedClass1()
    val d2 = DelegatedClass1_1()
    val d3 = DelegatedClass2(d1)
    val d4 = DelegatedClass2(d2)
    val d5 = DelegatedClass3(d1)
    val d6 = DelegatedClass3(d2)
    d3.f1()
//    v1: SSSSSSSSSSSSSSSSSSS
    d3.f2()
//    delegatedClass2
    d4.f1()
//    v1 1_!_!_!_!_!_!_!___!_!_!__!_!_!_!_!__!_!_: RRRRRRRRRRRRRRR
    d4.f2()
//    delegatedClass2_1
    d5.f1()
//    IT IS DELEGATED
    d5.f2()
//    delegatedClass2
    d6.f1()
//    IT IS DELEGATED
    d6.f2()
//    delegatedClass2_1


    // 객체 선언
    /*
        객체 선언을 사용하여 단일 인스턴스를 가진 클래스를 선언할 수 있음
        클래스 선언 + 단일 인스턴스 생성 의 역할을 함
        객체 선언은 프로그램의 단일 참조 지점으로 사용할 클래스를 생성하거나,
        시스템 전반의 동작을 조정할 때 유용함
        코틀린의 모든 객체는 지연 생성 방식으로, 해당 객체에 실제로 접근할 때 생성됨
        또한 코틀린은 모든 객체가 thread-safe 하게 생성되도록 보장하므로, 수동으로 확인할 필요가 없음 -> 진짜로?
        객체 선언은 다음과 방식으로 할 수 있음
             object 오브젝트명 {
                객체 본문에는 속성이나 멤버 함수를 추가할 수 있음
             }
         객체는 생성자를 가질 수 없기 때문에 클래스와 같은 헤더를 가질 필요는 없음
         객체는 클래스와 인터페이스를 상속 받을 수 있음 상속 받는 방법은 기존과 동일함
     */

    val o = CreatedObject
    o.m1 = "와아아아아"
    println(o.m1)
    // 와아아아아
    o.objectFunction("eeeee")
    // 와 짱신기해요 eeeee 2 근데 Object라고 따로 분리를 시켜버렸네?

    val o2 = CreatedObject
    println(o2.m1)
    // 와아아아아
    o2.objectFunction("eeeee")
    // 와 짱신기해요 eeeee 2 근데 Object라고 따로 분리를 시켜버렸네?
    println("o == o2 ${o == o2}")
    // 싱글톤 객체 (단일 인스턴스) 가 생성되기 때문에 한 번 생성하고 수정할 경우 다음 생성 시 수정 내역이 반영되어 있음

    /*
        Data Object
        객체 선언의 내용을 더 쉽게 출력할 수 있도록 해줌
        데이터 Object는 자동으로 toString()과 equals()를 재정의 해줌
        데이터 Object는 싱글톤 객체이기 때문에 copy() 는 자동으로 제공되지 않음
        선언을 위해서는 data object 오브젝트명 {} 을 사용한다
        더 자세한 내용은 이 곳에서 확인: https://kotlinlang.org/docs/object-declarations.html#data-objects
     */
    val o3 = CreatedObject2
    val o4 = CreatedObject3

    val dataObject = DataOject
    val do2 = DataOject2

    println("object: ${o3.toString()}")
    println("dataObject: ${dataObject.toString()}")
    println("o3.equals(o4): ${o3.equals(o4)}")
    println("dataObject.equals(do2): ${dataObject.equals(do2)}")
    // o == o2 true
    // object: com.example.iDontLikePackage.CreatedObject2@27bc2616
    // dataObject: DataOject
    // o3.equals(o4): false
    // dataObject.equals(do2): false
    // toString override는 눈에 확 들어오는데 equals override는 감이 잡히지 않음

    /*
        Companion Object
        동반 객체?
        코틀린에서 클래스는 객체 하나를 가질 수 있으며 이것이 동반 객체임
        클래스당 단 하나의 동반 객체만 가질 수 있음
        해당 클래스가 처음 참조될 때 생성됨

        동탄 객체 내부에서 선언된 모든 속성이나 함수는 모든 클래스 인스턴스에서 공유됨
        이 말은 즉 클래스 인스턴스는 여러개 생성해도 해당 클래스의 동반 객체는 싱클톤이기 때문에 그 메모리 주소만 들고 있기 때문?

        클래스 내에서 동반 객체를 생성하기 위해서는 객체 선언과 동일한 구문을 사용하되, 앞에 companion 키워드를 붙임
        동반 객체는 이름을 가질 필요가 없음 (익명 객체?) 이름을 정의하지 않으면 기본값은 Companion임
        자세한 사항은 이 곳에서 확인: https://kotlinlang.org/docs/object-declarations.html#companion-objects
     */

    val wco = WithCompanionObject
    wco.printString("Hello")
    // String: Hello




}