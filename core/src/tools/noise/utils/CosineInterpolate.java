package tools.noise.utils;

public class CosineInterpolate {
    public CosineInterpolate(){}
    
    public static Double set(Double a, Double b, Double x){
	Double ft = x * Math.PI;
	Double f = (1 - Math.cos(ft)) * .5;

	return  a*(1-f) + b*f;
    }
}
