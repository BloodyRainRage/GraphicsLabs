package com.baddragon.figures;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;


/**
 * Task 1
 */
public class Task1 implements GLEventListener {
    private GLU glu = new GLU();
    private float rquad = 0.0f;

    long start;

    float conePosX;
    float conePosY;
    float conePosZ;

    float cubePosX;
    float cubePosY;
    float cubePosZ;
    float cubeEdge;

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_FOG_MODE);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, 10);

        if ((System.currentTimeMillis() - start) < 5000) {
            //Изобразить каркасный куб и описать вокург него каркасный конус
            gl.glPushMatrix();
            gl.glTranslatef(cubePosX, cubePosY, cubePosZ);
            gl.glRotated(90f, 0.0f, 1.0f, 0.0f); // Rotate The Cube On X, Y & Z
            gl.glFlush();
            GLUT glut = new GLUT();
            glut.glutWireCube(cubeEdge);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(conePosX, conePosY, conePosZ);
            gl.glRotated(-90f, 1f, 0f, 0f); // Rotate The Cube On X, Y & Z
            gl.glFlush();
            glut.glutWireCone(2, 3, 20, 10);
            gl.glPopMatrix();
        } else if ((System.currentTimeMillis() - start) < 10000) {
            //Выполнить смещение по оси X на длину ребра куба.
            //Выполнить масштабирование куба с коэффициентом 2.0
            gl.glPushMatrix();
            gl.glTranslatef(cubePosX, cubePosY, cubePosZ);
            gl.glRotated(90f, 0.0f, 1.0f, 0.0f); // Rotate The Cube On X, Y & Z
            gl.glFlush();
            GLUT glut = new GLUT();
            glut.glutWireCube(cubeEdge * 2);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(conePosX + cubeEdge, conePosY, conePosZ);
            gl.glRotated(-90f, 1f, 0f, 0f); // Rotate The Cube On X, Y & Z
            gl.glFlush();
            glut.glutWireCone(2, 3, 20, 10);
            gl.glPopMatrix();

        } else if ((System.currentTimeMillis() - start) < 15000) {

            gl.glPushMatrix();
            gl.glTranslatef(cubePosX, cubePosY, cubePosZ);
            gl.glRotated(90f, 0.0f, 1.0f, 0.0f); // Rotate The Cube On X, Y & Z
            gl.glFlush();
            GLUT glut = new GLUT();
            //Изобразить тор и цилиндр
            glut.glutWireTorus(0.5, 1, 10, 10);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(conePosX + cubeEdge, conePosY, conePosZ);
            gl.glRotated(-90f, 1f, 0f, 0f); // Rotate The Cube On X, Y & Z
            gl.glFlush();
            glut.glutWireCylinder(1, 2, 20, 10);
            gl.glPopMatrix();

        } else {
            gl.glPushMatrix();
            gl.glTranslatef(cubePosX + 1, cubePosY + 2f, cubePosZ);
            gl.glRotated(90f, 1f, 0f, 0.0f); // Rotate The Cube On X, Y & Z
            gl.glFlush();
            GLUT glut = new GLUT();
            //Переместить объекты так, чтобы тор оказался на цилиндре
            glut.glutWireTorus(0.5, 1, 10, 10);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(conePosX + cubeEdge, conePosY, conePosZ);
            gl.glRotated(-90f, 1f, 0f, 0f); // Rotate The Cube On X, Y & Z
            gl.glFlush();
            glut.glutWireCylinder(1, 2, 20, 10);
            gl.glPopMatrix();

        }


    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        cubeEdge = 1;
        cubePosX = -5f;
        cubePosY = 0f;
        cubePosZ = -20f;

        conePosX = -5f;
        conePosY = -0.5f;
        conePosZ = -20f;

        start = System.currentTimeMillis();

    }

    public void change() {


    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0)
            height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
