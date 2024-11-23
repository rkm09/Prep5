package prep;


public class FindCelebrity277 extends Relation {
    public static void main(String[] args) {
        FindCelebrity277 c = new FindCelebrity277();
        System.out.println(c.findCelebrity(3));
    }

    public int findCelebrity(int n) {
        return 0;

    }
}

class Relation {
    boolean knows(int a, int b) {
        if(a == 0 && b == 1) return true;
        if(a == 1 && b == 0) return false;
        if(a == 1 && b == 2) return false;
        if(a == 2 && b == 1) return true;
        return false;
    }
}