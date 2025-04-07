//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Double a=4.7;
        Double b=5.0;
        LaCalculadora calculadora = new LaCalculadora();
        Double resultado= calculadora.operacionDosParametros(a, b, (a1, b1) -> a1 + b1);
        System.out.println(resultado);
    }
}