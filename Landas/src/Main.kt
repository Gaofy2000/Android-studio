//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val a=3.1
    val b=1.7
    val miCalculadora= Calculadora()
    val funSumar= fun(a:Double, b:Double):Double{
        return a+b
    }

    val funSumar2= { a:Double,b:Double ->a+b}

    val resultado= miCalculadora.operacionDosParametros(a, b, funSumar)
    println("Resultado: ${resultado}")

    val resultado2= miCalculadora.operacionDosParametros(a, b, funSumar2)
    println("Resultado con landa ${resultado2}")

    val resultado3= miCalculadora.operacionDosParametros(a, b) { x: Double, y: Double -> y - x }
    println("Resultado con funcion landa dentro de la funcion calculadora: ${resultado3}")

    val funCuadrado={x:Double->x*x}
    val resultadoCuadrado=miCalculadora.operacionUnParametro(a, funCuadrado)
    println("Resultado cuadrado: ${resultadoCuadrado}")

    val resultadoCuadrado2=miCalculadora.operacionUnParametro(b) { it*it }
    println("Resultado cuadrado con funcion landa dentro: ${resultadoCuadrado2}")
}