package processor;

import java.util.Scanner;

public class Matrix {
    private int rows;
    private int columns;
    private double[][] elements;

    Scanner scanner = new Scanner(System.in);

    public Matrix() {
        this.elements = new double[rows][columns];
    }

    public void createMatrixSize() {
        this.rows = scanner.nextInt();
        this.columns = scanner.nextInt();
    }
    public void createMatrix() {
        this.elements = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.elements[i][j] = scanner.nextDouble();
            }
        }
    }

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.elements = new double[rows][columns];
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void add(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix(matrix1.rows, matrix1.columns);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                matrix.elements[i][j] = matrix1.elements[i][j] + matrix2.elements[i][j];
            }
        }
        matrix.showMatrix();
    }

    public Matrix multiplyByAConstant(Matrix matrix1, double number) {
        Matrix matrix = new Matrix(matrix1.rows, matrix1.columns);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                matrix.elements[i][j] = matrix1.elements[i][j] * number;
            }
        }
        return matrix;
    }

    public void myltiply(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix();
        matrix.setRows(matrix1.rows);
        matrix.setColumns(matrix2.columns);
        matrix.elements = new double[matrix.rows][matrix.columns];
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                double sum = 0;
                for (int k = 0; k < matrix1.columns; k++) {
                    sum += matrix1.elements[i][k] * matrix2.elements[k][j];
                }
                matrix.elements[i][j] = sum;
            }
        }
        matrix.showMatrix();

    }

    public Matrix transponse(int type, Matrix matrix1) {
        Matrix matrix = new Matrix(matrix1.rows, matrix1.columns);
        switch (type) {
            case 1:
                for (int i = 0; i < matrix.rows; i++) {
                    for (int j = 0; j < matrix.columns; j++) {
                        matrix.elements[i][j] = matrix1.elements[j][i];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < matrix.rows; i++) {
                    for (int j = 0; j < matrix.columns; j++) {
                        matrix.elements[i][j] = matrix1.elements[matrix.rows - 1 - j][matrix.columns - 1 - i];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < matrix.rows; i++) {
                    for (int j = 0; j < matrix.columns; j++) {
                        matrix.elements[i][j] = matrix1.elements[i][matrix.columns - 1 - j];
                    }
                }
                break;
            default:
                for (int i = 0; i < matrix.rows; i++) {
                    for (int j = 0; j < matrix.columns; j++) {
                        matrix.elements[i][j] = matrix1.elements[matrix.rows - 1 - i][j];
                    }
                }

        }
        return matrix;
    }

    public double determinant(Matrix matrix) {
        double determinant = 0;
        if (matrix.columns == 1) {
            determinant = matrix.elements[0][0];
        } else if (matrix.columns == 2) {
            determinant = matrix.elements[0][0] * matrix.elements[1][1] - matrix.elements[0][1] * matrix.elements[1][0];
        } else {
            for (int i = 0 ; i < 1; i++) {
                for (int j = 0; j < matrix.columns; j++) {
                    determinant += Math.pow(-1, 2 + j) * matrix.elements[i][j] * determinantMinor(matrix, i, j) ;
                }
            }
        }
        return determinant;
    }
    public double determinantMinor(Matrix matrixD, int i, int j) {
        Matrix matrix = new Matrix(matrixD.rows - 1, matrixD.columns - 1);
        for (int k = 0; k < matrix.rows; k++) {
            for (int l = 0; l < matrix.columns; l++) {
                if (k < i && l < j) {
                    matrix.elements[k][l] = matrixD.elements[k][l];
                } else if (k >= i && l < j) {
                    matrix.elements[k][l] = matrixD.elements[k + 1][l];
                } else if (k < i && l >= j) {
                    matrix.elements[k][l] = matrixD.elements[k][l + 1];
                } else if (k >= i && l >= j) {
                    matrix.elements[k][l] = matrixD.elements[k + 1][l + 1];
                }
            }
        }
        return determinant(matrix);
    }



    public Matrix cofactorMatrix(Matrix matrix1) {
        Matrix matrix = new Matrix(matrix1.rows, matrix1.columns);
        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix1.columns; j++) {
                matrix.elements[i][j] = Math.pow(-1, 2 + i + j) * determinantMinor(matrix1, i, j);
            }
        }
        matrix = matrix.transponse(1, matrix);
        return matrix;
    }

    public void inverseMatrix(Matrix matrix1) {
        if (determinant(matrix1) == 0) {
            System.out.println("This matrix doesn't have an inverse.");
        } else {
            Matrix matrix = multiplyByAConstant(cofactorMatrix(matrix1), 1 / determinant(matrix1));
            matrix.showMatrix();
        }
    }


    public void showMatrix () {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                System.out.print(elements[i][j] + " ");
            }
            System.out.println();
        }
    }
}
