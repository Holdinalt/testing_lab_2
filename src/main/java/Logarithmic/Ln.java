package Logarithmic;

public class Ln implements LogExecutable {

    @Override
    public double execute(double digit, double base) throws Exception {

        if(base != Math.E){
            throw new Exception("Class Ln can count only E base");
        }

        return 1; //сделать ретурн человеком
    }
}
