public class LaCalculadora{
    public Double operacionDosParametros(Double x, Double y, OperacionDosParametros miOperacion){
        return miOperacion.operacion(x,y);
    }
}
