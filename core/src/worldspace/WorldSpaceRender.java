package worldspace;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import worldspace.Coords;
import worldspace.ElementalPoint;
import worldspace.PointsSet;
import worldspace.PolyPointsSet;
import worldspace.WorldSpace;

public class WorldSpaceRender{              
        public static final String VERT_SHADER =  
            "attribute vec2 a_position;\n" +
            "attribute vec4 a_color;\n" +			
            "uniform mat4 u_projTrans;\n" + 
            "varying vec4 vColor;\n" +			
            "void main() {\n" +  
            "	vColor = a_color;\n" +
            "	gl_Position =  u_projTrans * vec4(a_position.xy, 0.0, 1.0);\n" +
            "}";
	
	public static final String FRAG_SHADER = 
            "#ifdef GL_ES\n" +
            "precision mediump float;\n" +
            "#endif\n" +
			"varying vec4 vColor;\n" + 			
			"void main() {\n" +  
			"	gl_FragColor = vColor;\n" + 
			"}";
	
	protected static ShaderProgram createMeshShader() {
		ShaderProgram.pedantic = false;
		ShaderProgram shader = new ShaderProgram(VERT_SHADER, FRAG_SHADER);
		String log = shader.getLog();
		if (!shader.isCompiled())
			throw new GdxRuntimeException(log);		
		if (log!=null && log.length()!=0)
			System.out.println("Shader Log: "+log);
		return shader;
	}
	
	private Mesh mesh;
	private OrthographicCamera cam;
	private ShaderProgram shader;

        public static final int POSITION_COMPONENTS = 2;
	public static final int COLOR_COMPONENTS = 4;
	public static final int NUM_COMPONENTS = POSITION_COMPONENTS + COLOR_COMPONENTS;
	public static final int MAX_TRIS = 10000;
	public static final int MAX_VERTS = MAX_TRIS * 3;
	private float[] verts = new float[MAX_VERTS * NUM_COMPONENTS];	
	private int idx = 0;
        
        public WorldSpaceRender(){
            mesh = new Mesh(true, MAX_VERTS, 0, 
                            new VertexAttribute(Usage.Position, POSITION_COMPONENTS, "a_position"),
                            new VertexAttribute(Usage.ColorUnpacked, COLOR_COMPONENTS, "a_color"));		
            shader = createMeshShader(); 
        }
        
        public WorldSpaceRender(OrthographicCamera cam){
            this();
            this.cam = cam;
        }
        
        private ArrayList<PolyPointsSet> polySet = 
                new ArrayList<PolyPointsSet>();
        
        public void addPolySet(PolyPointsSet set){
            polySet.add(set);
        }
        public void setCam(OrthographicCamera cam){
            this.cam = cam; 
        }        
	public void render() {
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
                for(PolyPointsSet set : polySet)
                    drawGridData(set, Color.BROWN);
		flush();
	}
	
	void flush() {
		//if we've already flushed
		if (idx==0)
			return;
		
		mesh.setVertices(verts);
		
		Gdx.gl.glDepthMask(false);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		//number of vertices we need to render
		int vertexCount = (idx/NUM_COMPONENTS);
				
		shader.begin();
		shader.setUniformMatrix("u_projTrans", cam.combined);
		mesh.render(shader, GL20.GL_TRIANGLES, 0, vertexCount);
		shader.end();

                Gdx.gl.glDepthMask(true);

                idx = 0;
	}
        void colorize(Color color){
            verts[idx++] = color.r; 
            verts[idx++] = color.g;
            verts[idx++] = color.b;
            verts[idx++] = color.a;
            //System.out.println(idx);
        }
	
	void drawGridData(PolyPointsSet pointsSet, Color color) {
                int gridCase = pointsSet.getGridCase();
                Coords[] nodes = WorldSpace.getSimpleGridCoords(pointsSet.offset);

		if (idx>=verts.length-(NUM_COMPONENTS*4*3))
                    flush();             
                
                switch(gridCase){
                    case 1:	
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);                       
                        break;
                    case 2:
                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);                              
                        break;
                    case 3:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);   
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);   
                        break;
                    case 4:
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);                                              
                        break;                
                    case 5:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);     
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);                        

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);     
                        
                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);          
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);     
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);       
                        
                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);    

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);   
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                             
                        break;                                
                    case 6:
                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);   
                        
                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);   

                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                                                   
                        break;                                
                    case 7:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);              
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);              

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);      
                        
                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);   
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                       
                        break;
                    case 8:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);

                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                                
                        break;
                    case 9:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);   
                        
                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                           
                        break;
                    case 10://*
                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);      
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);  
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);     
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);  
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);     

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);  

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);  
                        
                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                              
                        break;                       
                    case 11://*
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);              
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);

                        verts[idx++] = pointsSet.getSideC();
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);      
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);   
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);                              
                        break;
                    case 12:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);   
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);                          

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                           
                        break;
                    case 13:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);         
                        
                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);         
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.getSideB();
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                       
                        break;
                    case 14:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.getSideD();
                        colorize(color);

                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);      
                        
                        verts[idx++] = pointsSet.getSideA();
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);    
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);       
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);                                                    
                        break;
                    case 15:
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);
                        
                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);
                        
                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x+WorldSpace.GRID_SIZE;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);

                        verts[idx++] = pointsSet.offset.x;
                        verts[idx++] = pointsSet.offset.y-WorldSpace.GRID_SIZE;
                        colorize(color);
                        break;
                }
	}

	public void dispose() {
		mesh.dispose();
		shader.dispose();
	}

}
