package tools.noise;

import tools.noise.utils.CosineInterpolate;

public class Noise1d{
    public Noise1d(){
        
    }
    public double SimpleNoise (double... coords){
        int N =0;
        for(double i : coords)
            N += i;
        N = (N << 13) ^ N;

        return (double) (1.0 - ((N * (N * N * 15731 + 789221) + 1376312589) & 
                Integer.MAX_VALUE) / 1073741824f);        

    }
    
    public double smoothedNoise(double x){
        return SimpleNoise(x)/2  +  SimpleNoise(x-1)/4  +  
                SimpleNoise(x+1)/4;
    }

    public double interpolatedNoise(double x){

        double X = Math.floor(x);
        double dX = x - X;

        double v1 = smoothedNoise(x);
        double v2 = smoothedNoise(x + 1);

        return CosineInterpolate.set(v1, v2, dX);

    }

    public double perlinNoise(double x){

        double total = 0.0;
        double p = .25;
        double n = 6.0 - 1.0;

        for(int i=0; i<n; i++){

            double frequency = Math.pow(2,i);
            double amplitude = Math.pow(p,i);

            total = total + interpolatedNoise(x * frequency) * amplitude;

        }

        return total;
    }
    public double perlinNoise(int iter, double k, double m, double x){
        double total = 0.0;

        for(int i=0; i<iter-1; i++){
            double frequency = Math.pow(k,i);
            double amplitude = Math.pow(m,i);

            total = total + interpolatedNoise(x * frequency) * amplitude;
        }
        return total;
    }
}
