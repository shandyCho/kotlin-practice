package com.example.kotlinPractice

import com.example.iDontLikePackage.IDontLikePackageSystem
import com.example.iDontLikePackage.ThisIsDataClass
import com.example.iDontLikePackage.returnUnitType
import com.example.iDontLikePackage.singleExpressionFunction
import com.example.iDontLikePackage.test2222
import com.example.iDontLikePackage.toSecond
import com.example.iDontLikePackage.whatThe

fun beginner() {
    val name = "Kotlin"
    // val 은 불변 변수 선언시 사용
    // var 은 가변 변수 선언 시 사용
    var mutableName = "Nameeee"
    println("mutableName: $mutableName")
    // mutableName: Nameeee
    //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
    // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.

//    name = "editedKotlin"
    // 'val' cannot be reassigned. 발생
    mutableName = "editedName"
    println("Hello, $name!")
//    Hello, Kotlin!
    println("mutableName: $mutableName")
//    mutableName: editedName

    val customer = 10
    println("There are $customer customers.")
//    There are 10 customers.
    println("There are ${customer + 1} customers.")
    // There are 11 customers.
    // 표현식 넣어서 간단한 연산은 되는듯?

    val customersInfo = HashMap<String, String>()
    customersInfo["test"] = "foo"
    customersInfo["test2"] = "bar"
    // HashMap 이 그냥 생성 가능함?
    println("CustomersInfo is ${customersInfo.toString()}")
    // 그냥 안에 코드를 집어넣을 수 있다는 말이 맞는 듯


    val typedValue: Int = 7
    // 타입 추론은 코틀린에서 가능하지만 타입 명시도 가능함
    var typedVariable: List<String> = listOf("A", "B", "C")
    var typedVariable2: List<Int> = ArrayList<Int>()
    // typedVariable2 의 경우 일반 List 이기 때문에 생성 후 요소 추가가 불가능하다
    var typedVariable3: MutableList<Int> = ArrayList<Int>()
    typedVariable3.add(1)
    typedVariable3.add(2)
    typedVariable3.add(3)
    // 컬렉션 생성 후 요소 추가가 필요한 경우에는 Mutable~ 자료형을 사용해야하는 것 같다

//    val tes
//    tes = 5
//    자료형을 명시하지 않고 선언만 하는 것은 불가능하다
    val tes: String
    tes = "This is Test"
    // 자료형을 명시할 경우, 선언만 하는 것이 가능하다
    // 그런데 어짜피 자료형 명시 하지 않는 경우에도 JVM 바이트 코드로 컴파일 할 때는 최초에 값이 할당되는 경우에 타입을 넣어줄 수 있지 않나?
    // 데이터가 외부에서 들어오는 경우에는 추론 안되서 그러나

//    val tes2: String
//    println(tes2)
    // Variable 'tes2' must be initialized.
    // 당연하지만 선언만 하고 값 할당 안했으면 초기화 하라고 (값 할당하라고) 메세지 나옴

    // 코틀린 공홈의 타입 맞추기 문제 예제
    val a: Int = 1000
    val b: String = "log message"
    val c: Double = 3.14
    val d: Long = 100_000_000_000_000
    val e: Boolean = false
    val f: Char = '\n'


    /* 코틀린에서 컬렉션은 세가지 유형으로 구분할 수 있음
        Lists: 정렬된 요소들의 모음
        Sets: 정렬되지 않은 고유한 요소들의 모음
        Maps: 키가 고유하고 하나의 값에만 매핑되는 키-값 쌍의 집합
        각 컬렉션들은 가변 타입과 불변 타입이 존재함
     */

    val l: List<String> = listOf("a", "b", "c", "d", "e", "f")
    val ml: MutableList<String> = mutableListOf("a", "b", "c", "d", "e", "f")
    ml.add("d")

    println(ml.toString())
    // [a, b, c, d, e, f, d]
    // MutableList<>의 경우 var이 아닌 val로 선언해도 값의 추가가 가능함
    // 기준이 뭐야 이거?
    // MutableList 생성했지만 의도치 않게 수정되는 것을 막기 위해서 List에 해당 MutableList를 할당해주는 기법이 있고, 이를 캐스팅이라고 한다

    // 캐스팅 예제
    val cast: MutableList<String> = mutableListOf("test", "array", "list", "근데", "자료형이", "ArrayList가 될까?")
    val casted: List<String> = cast
    // casted에서는 데이터 추가, 삭제, 수정이 불가능하다
    println("casted[3]: ${casted[3]}")
    // casted[3]: 근데
    // List의 요소에 접근하는 방법은 list[list의 인덱스] 가 있다.
    println("casted.first(): ${casted.first()}, casted.last(): ${casted.last()}, casted.get(2): ${casted.get(2)}")
    // casted.first(): test, casted.last(): ArrayList가 될까?, casted.get(2): list
    // first()와 last()는 확장함수?
    // 확장함수 호출을 위해서는 객체 뒤에 . 을 붙인 후 함수 이름을 작성하면 됨
    println("cast.count(): ${cast.count()}")
    val removedItem = cast.removeAt(2)
    println("removedItem: $removedItem")
    println("cast.count(): ${cast.count()}")
    println("casted.count(): ${casted.count()}")
    // cast.count(): 6
    // removedItem: list
    // cast.count(): 5
    // casted.count(): 5
    // 원본 리스트를 수정하였을 경우, casting된 리스트도 수정이 됨
    println("\"test\" in cast: ${"test" in cast}")
    println("\"test1\" in cast: ${"test1" in cast}")
    // "test" in cast: true
    // "test1" in cast: false
    // List 안에 특정 요소가 있는지는 요소 in 리스트 의 형태로 확인할 수 있음

    val s: Set<String> = setOf("a", "b", "c", "d", "e", "f", "b")
    val ms: MutableSet<String> = mutableSetOf("a", "b", "c", "d", "e", "f", "b")

    println("s: $s")
    // s: [a, b, c, d, e, f]
    // 생성 시에 매개변수로 중복되는 요소가 들어가더라도 중복이 걸러져서 생성됨

    val readOnlyFruit = setOf("apple", 3, "cherry", "cherry")
    println("readOnlyFruit: $readOnlyFruit")
    readOnlyFruit.iterator().forEach { f -> println("f is String: ${f is String}") }
    // 이렇게 되면 Set<Any> 혹은 Set<*> 가 된다는 말인가?
    val mutableFruit: MutableSet<Any> = mutableSetOf("apple", 3, "cherry", "cherry")

    mutableFruit.iterator().forEach { f2 -> println("f2 is String: ${f2 is String}") }
    // f is String: true
    // f is String: false
    // f is String: true
    // f2 is String: true
    // f2 is String: false
    // f2 is String: true
    // JVM 기반 언어에서 이게 된다고?
    // Set 역시 MutableSet의 의도치 않은 수정을 막기 위한 Casting이 가능함
    // count(), add(), remove(), 요소 in Set 판별식도 사용 가능함

    val m = mapOf("apple" to 3, "cherry" to 3, "cherry" to 3)
    val mm: MutableMap<String, Int> = mutableMapOf("apple" to 3, "cherry" to 3)
    // mapOf로 생성할 때 key to value 형식으로 Map의 매개변수를 넣을 수 있음
    println("m: ${m}")
    // m: {apple=3, cherry=3}
    println("m[\"cherry\"]: ${m["cherry"]}")
    // m["cherry"]: 3
    // Map도 List, Set과 같이 Casting이 가능하다
    println("m[\"banana\"]: ${m["banana"]}")
    // m["banana"]: null
    // key가 Map에 존재하지 않을 경우 null을 반환한다
    mm["banana"] = 10
    mm["cherry"] = 30
    println("mm: $mm")
    // mm: {apple=3, cherry=30, banana=10}
    // MutableMap은 map[key] = value 로 새로운 key - value 쌍을 생성하거나 이미 있는 key의 value를 변경할 수 있다
    // remove(key), count(), .containsKey(key) 사용 가능하다
    println("mm.keys: ${mm.keys}")
    println("mm.values: ${mm.values}")
    // mm.keys: [apple, cherry, banana]
    // mm.values: [3, 30, 10]
    // Map.keys, Map.values 를 이용하여 key 리스트, value 리스트를 얻을 수 있음
    // keys와 values는 Map, MutableMap의 객체 속성임

    val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    println("orange" in readOnlyJuiceMenu.keys)
    // true
    // keys 속성에는 in 문법 사용 가능
    // Alternatively, you don't need to use the keys property
    println("orange" in readOnlyJuiceMenu)
    // true
    // Map 자체에 in 을 사용할 경우 key에 해당하는 값이 있는지 여부를 반환함
    println(200 in readOnlyJuiceMenu.values)
    // false
    // values 속성에서도 in 문법 사용 가능

    ///////// 분기문
    // if와 when이 존재하며, when을 권장함
    // when의 경우 코드의 가독성이 좋으며, 다른 분기를 추가하기 쉬움, 코드 내 오류 발생을 줄여줄 수 있음(?)

    // if문
    val ifTest = "check"

    if (ifTest === "check") {
        println("Checked!")
    } else {
        println("Unchecked!")
    }
    // if문의 결과를 바로 값에 할당할 수도 있다
    val ifResult = if (ifTest === "check") "eee" else "eve"
    println("ifResult: $ifResult")
    // Checked!
    // ifResult: eee

    // when문
    val whenTest = "wheeeeeen"
    when (whenTest) {
        "wheeeeeen" -> println("equal!")
        "hello" -> println("hello!")
        else -> println("who are you?")
    }
    // equal!
    // when의 모든 분기는 그 중 하나가 충족될 때 까지 순차적으로 검사함
    // 분기들 중 중간 분기에서 매칭이 될 경우 다음의 분기들은 실행되지 않음

    // when 역시 판별 결과값을 변수에 담을 수 있음
    // when 뒤에 바로 매개변수를 두어 해당 값을 테스트 하는 것 뿐만 아니라 여러 변수를 판별할수도 있음
    // 당연하지만 앞에서 true 가 걸리면 뒤에서 true가 와도 실행 안됨
    val whenTest2 = when {
        whenTest === "check" -> "eee"
        whenTest === "hello" -> "hello"
        whenTest === "wheeeeeen" -> "matched"
        ifTest === "check" -> "what"
        else -> "hello"
    }
    println("whenTest2: $whenTest2")
    // whenTest2: matched

    val whenTest3 = when {
        whenTest === "check" -> "eee"
        whenTest === "hello" -> "hello"
        ifTest === "check" -> "what"
        whenTest === "wheeeeeen" -> "matched"
        else -> "hello"
    }
    println("whenTest3: $whenTest3")
    // whenTest3: what


    // Ranges -> 범위 표현
    // 1..4 는 1, 2, 3, 4 를 나타낸다
    // 1..<4 는 1, 2, 3 을 나타낸다
    // 4 downTo 1 는 4, 3, 2, 1 을 나타낸다
    // 1씩, 혹은 한 단계씩 증가 혹은 감소하는 것이 아니라면 step 원하는 증가값 구문을 Ranges 구문 뒤에 붙인다
    // 1..4 step 2 는 1, 3 과 동일하다
    // Int 자료형이 아닌 Char 자료형에도 사용할 수 있다
    // 'a'..'d' 는 'a', 'b', 'c', 'd' 와 동일하다
    // 'z' downTo 's' step 2 는 'z', 'x', 'v', 't' 와 동일하다

    // Loops -> 반복문
    // for과 while, do-while문이 존재함

    for (number in 1..10) {
        print("${number}, ")
    }
    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    println("")
    val forTest = listOf("test1", "test2", "test3")
    for (test in forTest) {
        println(test)
    }
    // test1
    // test2
    // test3

    // while문 조건을 검사하고 while 문 안의 코드를 실행한다
    var eaten = 0
    while (eaten < 5) {
        println("eat something")
        eaten++
    }

    eaten = 0
    var cooked = 0
    while (eaten < 5) {
        println("eat pasta")
        eaten++
    }

    // do 문 안의 코드를 실행하고 while 문 조건을 검사한다
    do {
        println("cook a pasta")
        cooked++
    } while (cooked < eaten)


    // 함수에서 설정한 매개변수 명을 호출할 때 넣어줄 수 있음
    whatThe(count = 60, message = "this is message", defalutValuee = "eeeeeeee")
    whatThe(count = 60, message = "this is message")
    // this is message count is 60
    // defalutValuee is eeeeeeee
    // this is message count is 60
    // defalutValuee is default
    // 함수의 매개변수에 기본값을 설정해줄 수 있다

    val unnnn = returnUnitType()
    println(unnnn)
    // If function do not declared return type, it will be returned Unit Type
    // kotlin.Unit

    println(whatThe(count = 120, message = "this is message in what there"))
    // is it possible?
    // 함수를 매개변수에 넣을 수 있다
    println(singleExpressionFunction(str = "striiiiiiiing", vec = 11111))
    // striiiiiiiing11111

    // 익명함수 사용 가능 (람다함수 사용 가능)
    val lExpression = {text: String -> text.uppercase()}
    println(lExpression("kkkkkkkkkkkkkkkkkkk"))
    // KKKKKKKKKKKKKKKKKKK
    val lEx2 = {println("이딴게 람다함수라고")}
    lEx2()
    // 이딴게 람다함수라고
    // 람다함수 (익명함수 람다 표현식 등등...) 은 다른 함수의 매개변수로 전달될 수 있고, 함수에서 반환할 수도 있으며 지금처럼 단독 호출도 가능하다
    // .filter() 함수가 람다함수를 매개변수로 받는 대표적인 함수임

    val numbers = listOf(1, -2, 3, -5, 7, 8, -10)
    val positive = numbers.filter({ x -> x > 0 })
    val positive2 = numbers.filter { x -> x > 0 }    // 이렇게 작성해도 동작함 (이 경우는 람다식이 함수의 유일한 매개변수일 경우에 가능함
    val negativeFiltering = { x: Int -> x < 0 }
    val negative = numbers.filter(negativeFiltering)
    println(positive)
    println(negative)
    // filter 함수는 매개변수로 받은 람다식이 true일 경우에만 배열의 요소를 남기고, false일 경우 삭제해버림
    // [1, 3, 7, 8]
    // [-2, -5, -10]

    // .map() 함수도 람다식을 매개변수로 받는 대표적인 함수
    val doubled = numbers.map { x -> x * 2 }
    val tripledMapping = { x: Int -> x * 3 }
    val tripled = numbers.map(tripledMapping)
    println(doubled)
    println(tripled)
    // [2, -4, 6, -10, 14, 16, -20]
    // [3, -6, 9, -15, 21, 24, -30]

    // 함수에도 타입이 존재함
    val functionType: (Int, Int) -> Int = { x: Int, y: Int -> x * y }
    // 함수의 타입은 (매개변수 타입, 매개변수 타입) -> 반환 타입 의 형태로 작성함
    // 함수가 아무것도 반환하지 않을 경우, 반환 타입은 Unit 으로 작성함
    // 함수가 아무런 매개변수도 받지 않을 경우, 매개변수는 () 으로만 작성함
    // ex) () -> Unit
    println(functionType(3, 6))
    // 18

    val timesInMinutes = listOf(2, 10, 5, 1)
    val minToSec = toSecond("minute")
    val totalTimeInSeconds = timesInMinutes.map(toSecond("minute"))
    println(totalTimeInSeconds)
    println(totalTimeInSeconds.sum())


    // 람다식의 경우, 중괄호 뒤에 괄호를 추가한 다음, 매개변수를 괄호 안에 넣으면 단독 호출이 가능함
    println({ test: String -> test.toCharArray()
        .filter({ char: Char -> char.isLowerCase() })
        .toCharArray()
        .joinToString("") }("kOotTTTTTTliIIn")
    )

    println({ text: String -> text.uppercase() }("hello"))

    println(listOf(1, 2, 3).fold(0, { x, items -> x + items }))
    println(listOf(1, 2, 3).fold(0) { x, items -> x + items })
    println(listOf(1, 2, 3).map() { x -> x + 2 })
    // 매개변수가 람다식 하나이거나, 마지막 매개변수일 경우, 함수 매개변수 () 가 아닌 그 뒤에 람다식을 붙여 쓸 수 있음
    // 해당 사용 방식을 후행 람다라고 함

    val instanccccce = IDontLikePackageSystem("Parameter", 2, true)
    instanccccce.iHate("test", "ffffffffff")
    instanccccce.useClassParameter()
    println("classParameter1: ${instanccccce.classParameter1}")
    // 클래스 프로퍼티를 리터럴 템플릿에서 사용할 경우 ${} 을 이용해야한다
    println("classParameter2: ${instanccccce.classParameter2}")
//    println("classParameter3: ${instanccccce.classParameter3}")
    // val 혹은 var 과 함께 선언하지 않은 매개변수의 경우, 프로퍼티 접근법으로 접근할 수 없다
    println("classParameter3: ${instanccccce.classParameter3Copy}")
    // classParameter1: Parameter
    // classParameter2: 2
    // classParameter3: true
    instanccccce.classParameter3Copy = false
    instanccccce.classParameter2 = 350000000
    instanccccce.iHate("test", "ffffffffff")
    instanccccce.useClassParameter()
//    ffffffffff test, classParameter3Copy: false
//    Parameter, 350000000

    val dataClasss = ThisIsDataClass(data = "Striing", privateData = "Private ")
    val copiedDataClass = dataClasss.copy()
    println(copiedDataClass)
    // ThisIsDataClass(data=Striing, privateData=Private )
    test2222()
    println("dataClasss.equals(copiedDataClass): ${dataClasss.equals(copiedDataClass)}")
    println("dataClasss == copiedDataClass: ${dataClasss == copiedDataClass}")
    // dataClasss.equals(copiedDataClass): true
    // dataClasss == copiedDataClass: true



    val ins1 = IDontLikePackageSystem("Parameter", 2, true)
    val ins2 = IDontLikePackageSystem("Parameter", 2, true)
    println("ins1.equals(ins2): ${ins1.equals(ins2)}")
    // ins1.equals(ins2): false

    val dataIns1 = ThisIsDataClass(data = "DATA", privateData = "PRIVATE_DATA")
    val dataIns2 = dataIns1.copy()
    println("1. dataIns1.equals(dataIns2): ${dataIns1.equals(dataIns2)}")
    // 1. dataIns1.equals(dataIns2): true
    dataIns1.privateData = "ChangedData"
    println("2. dataIns1.equals(dataIns2): ${dataIns1.equals(dataIns2)}")
    // 2. dataIns1.equals(dataIns2): false


    // Kotlin에서의 Null Safety
    // Kotlin에서는 null이 허용되는 시점을 명시적으로 선언할 필요성이 있음
    // null이 들어왔는지를 검사해야할 필요성이 있음
    // null 값을 포함할 수 있는 속성이나 함수에 대한 안전한 호출을 사용할 필요가 있음
    // null 값이 들어왔을 때 수행할 작업을 선언해줄 필요가 있음

    // Nullable Types
    var cantNull: String = "이건 널이 될수 없널널널널널"
//    cantNull = null
    // 바로 에러남 Null cannot be a value of a non-null type 'String'
    var nullableVariable: String? = "이건 널이 될수 있널널널널널"
    nullableVariable = null

//    println(nullableVariable.length)
    // Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type 'Nothing?
    println(nullableVariable?.length)
    // null
//    println(nullableVariable!!.length)
    // null-able 타입의 경우, ?.를 통해 null이 들어왔을 때 null을 반환해줄 수 있도록 히던지
    // !!. 을 통해서 null이 들어왔을 때 코드가 실패하도록 할 수 있음
    // 근데 !!. 사용하니깐 Exception in thread "main" java.lang.NullPointerException
    //	at com.example.MainKt.main(Main.kt:428)
    //	at com.example.MainKt.main(Main.kt) 이런 메세지가 튀어나오는데

    // variable != null 을 통해서 null 검사를 수행할 수 있음 -> 이건 자바랑 똑같네
    // 객체의 속성이 nullable 할 경우에 ?. 문법으로 해당 속성에 접근할 수 있음
    // 객체나 객체의 속성이 null일 경우에 해당 접근법에서는 null을 반환함
    // 3-depth 이상으로 속성 접근이 필요할 경우에도 ?. 을 통해서 접근할 수 있음 (체이닝이 됨)

    // Elvis Operator (엘비스 연산자) ?:
    // nullableVariable = null
    println("nullableVariable?.length ?: 10 -> ${nullableVariable?.length ?: 10}")
    println("if (nullableVariable?.length == null) 10 else  nullableVariable.length -> ${if (nullableVariable?.length == null) 10 else  nullableVariable.length}")
    // nullableVariable?.length ?: 10 -> 10
    // if (nullableVariable?.length == null) 10 else  nullableVariable.length -> 10









}