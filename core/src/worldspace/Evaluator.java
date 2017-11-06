package worldspace;

/**
 *
 * @author roasstbeef (alex.s)
 */
public abstract class Evaluator {
    protected int modifier = 1;
    protected Chunk chunk;

    public abstract void eval(Coords coord);
    public abstract void eval(Chunk chunk);
    public abstract void eval();

    public Evaluator(){
    }      
    public Evaluator(Chunk chunk){
        setChunk(chunk);
    }  
    public void setChunk(Chunk chunk){
        this.chunk=chunk;
    }

    public void setModifier(int m){
        this.modifier= m;
    }  

    public int getModifier() {
        return this.modifier;
    }    
    
    
}
