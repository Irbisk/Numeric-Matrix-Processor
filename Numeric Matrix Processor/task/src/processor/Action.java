package processor;

import java.util.Scanner;

public class Action {
    Scanner scanner = new Scanner(System.in);

    public void startGame() {
        boolean action = true;
        while (action) {
            System.out.println("1. Add matrices\n" +
                    "2. Multiply matrix by a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
                    "0. Exit\n" +
                    "Your choice: ");

            Matrix matrix1 = new Matrix();
            Matrix matrix = new Matrix();
            Matrix matrix2 = new Matrix();



            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter size of first matrix:");
                    matrix1.createMatrixSize();
                    System.out.println("Enter first matrix:");
                    matrix1.createMatrix();
                    System.out.println("Enter size of second matrix:");
                    matrix2.createMatrixSize();
                    System.out.println("Enter second matrix:");
                    matrix2.createMatrix();
                    if (matrix1.getColumns() != matrix2.getColumns() || matrix1.getRows() != matrix2.getRows()) {
                        System.out.println("The operation cannot be performed.");
                    }
                    matrix.add(matrix1, matrix2);
                    matrix.showMatrix();
                    break;
                case 2:
                    System.out.println("Enter size of matrix:");
                    matrix1.createMatrixSize();
                    System.out.println("Enter matrix:");
                    matrix1.createMatrix();
                    System.out.println("Enter constant: ");
                    double constant = scanner.nextDouble();
                    matrix.multiplyByAConstant(matrix1, constant).showMatrix();
                    break;
                case 3:
                    System.out.println("Enter size of first matrix:");
                    matrix1.createMatrixSize();
                    System.out.println("Enter first matrix:");
                    matrix1.createMatrix();
                    System.out.println("Enter size of second matrix:");
                    matrix2.createMatrixSize();
                    System.out.println("Enter second matrix:");
                    matrix2.createMatrix();
                    if (matrix1.getColumns() != matrix2.getRows()) {
                        System.out.println("The operation cannot be performed.");
                    } else {
                        matrix.myltiply(matrix1, matrix2);
                    }
                    break;
                case 4:
                    System.out.println("1. Main diagonal\n" +
                            "2. Side diagonal\n" +
                            "3. Vertical line\n" +
                            "4. Horizontal line\n" +
                            "Your choice: ");
                    int transponse = scanner.nextInt();
                    System.out.println("Enter size of matrix:");
                    matrix1.createMatrixSize();
                    System.out.println("Enter matrix:");
                    matrix1.createMatrix();
                    matrix.transponse(transponse, matrix1).showMatrix();

                    break;
                case 5:
                    System.out.println("Enter size of matrix:");
                    matrix1.createMatrixSize();
                    System.out.println("Enter matrix:");
                    matrix1.createMatrix();
                    System.out.println(matrix.determinant(matrix1));
                    break;
                case 6:
                    System.out.println("Enter size of matrix:");
                    matrix1.createMatrixSize();
                    System.out.println("Enter matrix:");
                    matrix1.createMatrix();
                    matrix.inverseMatrix(matrix1);
                default:
                    action = false;
            }
        }
    }
}
