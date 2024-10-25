package Modelo;
import java.io.Serializable;

public class entrada implements Serializable{
    private String Sensor1, Sensor2, Sensor3;
    private int Sensor4;


    public entrada(String Sensor1, String Sensor2, String Sensor3, int Sensor4){
        this.Sensor1 = Sensor1;
        this.Sensor2 = Sensor2;
        this.Sensor3 = Sensor3;
        this.Sensor4 = Sensor4;
    }
    public String getSensor1(){
        return Sensor1;
    }
    public String getSensor2(){
        return Sensor2;
    }
    public String getSensor3(){
        return Sensor3;
    }
    public int getSensor4() {
        return Sensor4;
    }
    public void setSensor1(String Sensor1){
        this.Sensor1 = Sensor1;
    }
    public void setSensor2(String Sensor2){
        this.Sensor2 = Sensor2;
    }
    public void setSensor3(String Sensor3){
        this.Sensor3 = Sensor3;
    }
    public void setSensor4(int Sensor4) {
        this.Sensor4 = Sensor4;
    }

    @Override 
    public String toString(){
        return "Sensor1: " + Sensor1 + " Sensor2: " + Sensor2 + " Sensor3: " + Sensor3;
    }
}

