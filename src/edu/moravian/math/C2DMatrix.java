/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.math;

/**
 *
 * @author Daniel Huynh
 */
public class C2DMatrix 
{
    public class Matrix
    {
        public double _11, _12, _13;
        public double _21, _22, _23;
        public double _31, _32, _33;

        Matrix() {
            _11 = 0.0;
            _12 = 0.0;
            _13 = 0.0;
            _21 = 0.0;
            _22 = 0.0;
            _23 = 0.0;
            _31 = 0.0;
            _32 = 0.0;
            _33 = 0.0;
        }
    }
    
    Matrix m_Matrix = new Matrix();
    
    public C2DMatrix()
    {
        this.identity();
    }
    public void _11(double val) {
        m_Matrix._11 = val;
    }

    public void _12(double val) {
        m_Matrix._12 = val;
    }

    public void _13(double val) {
        m_Matrix._13 = val;
    }

    public void _21(double val) {
        m_Matrix._21 = val;
    }

    public void _22(double val) {
        m_Matrix._22 = val;
    }

    public void _23(double val) {
        m_Matrix._23 = val;
    }

    public void _31(double val) {
        m_Matrix._31 = val;
    }

    public void _32(double val) {
        m_Matrix._32 = val;
    }

    public void _33(double val) {
        m_Matrix._33 = val;
    }
    
    private void identity() {
        m_Matrix._11 = 1;
        m_Matrix._12 = 0;
        m_Matrix._13 = 0;

        m_Matrix._21 = 0;
        m_Matrix._22 = 1;
        m_Matrix._23 = 0;

        m_Matrix._31 = 0;
        m_Matrix._32 = 0;
        m_Matrix._33 = 1;
    }
    
    public Vector2D transformVector2D(Vector2D point)
    {
        double tempX = (m_Matrix._11*point.getX()) + (m_Matrix._21*point.getY()) + (m_Matrix._31);
        double tempY = (m_Matrix._12*point.getX()) + (m_Matrix._22*point.getY()) + (m_Matrix._32);
        return new Vector2D(tempX, tempY);
    }
    
    public void matrixMultiply(Matrix mIn)
    {
        Matrix mat_temp = new Matrix();

        //first row
        mat_temp._11 = (m_Matrix._11 * mIn._11) + (m_Matrix._12 * mIn._21) + (m_Matrix._13 * mIn._31);
        mat_temp._12 = (m_Matrix._11 * mIn._12) + (m_Matrix._12 * mIn._22) + (m_Matrix._13 * mIn._32);
        mat_temp._13 = (m_Matrix._11 * mIn._13) + (m_Matrix._12 * mIn._23) + (m_Matrix._13 * mIn._33);

        //second
        mat_temp._21 = (m_Matrix._21 * mIn._11) + (m_Matrix._22 * mIn._21) + (m_Matrix._23 * mIn._31);
        mat_temp._22 = (m_Matrix._21 * mIn._12) + (m_Matrix._22 * mIn._22) + (m_Matrix._23 * mIn._32);
        mat_temp._23 = (m_Matrix._21 * mIn._13) + (m_Matrix._22 * mIn._23) + (m_Matrix._23 * mIn._33);

        //third
        mat_temp._31 = (m_Matrix._31 * mIn._11) + (m_Matrix._32 * mIn._21) + (m_Matrix._33 * mIn._31);
        mat_temp._32 = (m_Matrix._31 * mIn._12) + (m_Matrix._32 * mIn._22) + (m_Matrix._33 * mIn._32);
        mat_temp._33 = (m_Matrix._31 * mIn._13) + (m_Matrix._32 * mIn._23) + (m_Matrix._33 * mIn._33);

        m_Matrix = mat_temp;
    }
    
    public void rotate(Vector2D fwd, Vector2D side)
    {
        Matrix mat = new Matrix();

        mat._11 = fwd.getX();
        mat._12 = fwd.getY();
        mat._13 = 0;

        mat._21 = side.getX();
        mat._22 = side.getY();
        mat._23 = 0;

        mat._31 = 0;
        mat._32 = 0;
        mat._33 = 1;

        //and multiply
        this.matrixMultiply(mat);
    }
    
}
