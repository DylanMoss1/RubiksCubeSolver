package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Render_Cubies;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Cubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.*;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class RenderCubie extends Cubie {

    protected final static String VSHADER_FN = "resources/cube_vertex_shader.glsl";
    protected final static String FSHADER_FN = "resources/cube_fragment_shader.glsl";

    protected Mesh cube_mesh;
    protected ShaderProgram cube_shader;	// Shader to colour the body mesh
    protected Texture cube_texture;		// Texture image to be used by the body shader
    protected Matrix4f cube_transform;	// Transformation matrix of the body object

    public float totalTime;
    public float centreMove;

    public RenderCubie(int index, int i, int j, int k, int timeConstant) {
        super(index, i, j, k);
        scale = 1/((float) timeConstant)/2;
        createTexture();
    }

    public RenderCubie(int index, int i, int j, int k, Matrix3f orientation, int timeConstant) {
        super(index, i, j, k, orientation);
        scale = 1/((float) timeConstant)/2;
        createTexture();
    }

    public void createTexture(){
        cube_mesh = new CubeMesh();

        cube_shader = new ShaderProgram(new Shader(GL_VERTEX_SHADER, VSHADER_FN), new Shader(GL_FRAGMENT_SHADER, FSHADER_FN), "colour");
        cube_shader.bindDataToShader("oc_position", cube_mesh.vertex_handle, 3);
        cube_shader.bindDataToShader("oc_normal", cube_mesh.normal_handle, 3);
        cube_shader.bindDataToShader("texcoord", cube_mesh.tex_handle, 2);

        // Initialise Texturing
        cube_texture = new Texture();
        cube_texture.load("resources/rubiksCubeMap.png");
        cube_texture.bindTexture();
    }


    public void moveCube(String move){

        super.moveCube(move);

        Matrix4f cube_scale = new Matrix4f();
        cube_transform = new Matrix4f();
        cube_scale = cube_scale.scaleLocal(0.5f,0.5f,0.5f);
        ind_cube_rotate_translate.mul(cube_scale,cube_transform);
    }


    public void render(Camera camera, String move) {
        moveCube(move);
        renderMesh(camera, cube_mesh, cube_transform, cube_shader, cube_texture);
    }

    public void renderMesh(Camera camera, Mesh mesh , Matrix4f modelMatrix, ShaderProgram shader, Texture texture) {

        //Load shader
        shader.reloadIfNeeded();
        shader.useProgram();

        //Create transformation matrix
        Matrix4f mvp_matrix = new Matrix4f(camera.getProjectionMatrix()).mul(camera.getViewMatrix()).mul(modelMatrix);

        //Upload these matrices to the shader
        shader.uploadMatrix4f(mvp_matrix, "mvp_matrix");
        shader.uploadMatrix4f(modelMatrix, "m_matrix");
        shader.uploadVector3f(camera.getCameraPosition(), "wc_camera_position");


        //Create normal and model matrices
        Matrix3f normal_matrix;
        Matrix3f modelMatrix3 = new Matrix3f();
        modelMatrix.get3x3(modelMatrix3);
        normal_matrix = modelMatrix3.invert().transpose();

        //Upload the normal matrix to the shader
        shader.uploadMatrix3f(normal_matrix, "normal_matrix");

        //Bind the textures to the shader
        texture.bindTexture();
        shader.bindTextureToShader("tex", 0);
        shader.bindTextureToShader("skybox", 1);

        //Send these elements to the GL shader
        glBindVertexArray(mesh.vertexArrayObj);
        glDrawElements(GL_TRIANGLES, mesh.no_of_triangles, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);

        texture.unBindTexture();
    }
}
