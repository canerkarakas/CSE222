public class Main {

    public static void main(String[] args) {
        int[][] array2D = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        iterator myIter = new iterator(array2D);
        myIter.Traverse(0);
    }
}