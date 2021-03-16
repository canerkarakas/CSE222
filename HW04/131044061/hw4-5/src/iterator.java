import java.util.Iterator;

public class iterator implements Iterable, Iterator {
    private int upBound;
    private int downBound;
    private int rightBound;
    private int leftBound;
    private int coord_x;
    private int coord_y;
    private int myMove;
    private int[][] myArr;
    private int membersNum =0;
    private int firstSpiral = 0;

    public iterator(int arr[][]){
        myArr = arr;
        coord_x = 0;
        coord_y = 0;
        rightBound = arr[0].length;
        downBound = arr.length;
        upBound = downBound;
        leftBound = rightBound;
    }

    @Override
    public Iterator iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        if (myMove==0){
            coord_y++;
        }
        if (myMove==1){
            coord_x++;
        }
        if (myMove==2){
            coord_y--;
        }
        if (myMove==3){
            coord_x--;
        }
        return myArr[coord_x][coord_y];
    }

    public void Traverse(int move){
        myMove = move;
        if(membersNum==(myArr[0].length*myArr.length)){
            upBound=0;
            downBound=0;
            rightBound=0;
            leftBound=0;
        }
        if (upBound!=0 && downBound!=0 && rightBound!=0 && leftBound!=0){
            if (coord_x==0 && coord_y==0) {
                System.out.print(myArr[0][0] + " ");
                membersNum++;
            }
            if (move==0){
                firstSpiral++;
                for (int i=1;i<rightBound;i++){
                    if (membersNum!=myArr[0].length*myArr.length){
                        System.out.print(next() + " ");
                        membersNum++;
                    }
                }
                Traverse(1);
            }
            if (move==1){
                for (int i=1;i<downBound;i++){
                    if(membersNum!=myArr[0].length*myArr.length) {
                        System.out.print(next() + " ");
                        membersNum++;
                    }
                }
                Traverse(2);
            }
            if (move==2){
                for (int i=1;i<leftBound;i++){

                    if(membersNum!=myArr[0].length*myArr.length) {
                        System.out.print(next() + " ");
                        membersNum++;
                    }
                }
                Traverse(3);
            }
            if (move==3){
                upBound--;
                for (int i=1;i<upBound;i++){
                    if(membersNum!=myArr[0].length*myArr.length) {
                        System.out.print(next() + " ");
                        membersNum++;
                    }
                }
                if (firstSpiral==1){
                    rightBound = rightBound-1;
                }
                else
                    rightBound = rightBound-2;
                leftBound = leftBound - 2;
                downBound = downBound - 2;
                upBound = upBound-1;
                Traverse(0);
            }
        }
    }
}