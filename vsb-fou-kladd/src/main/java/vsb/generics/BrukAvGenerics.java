package vsb.generics;

public class BrukAvGenerics {

    public static void main(String[] args) {
        MyImplementingClass clazz = new MyImplementingClass();

        MyImplementingClass kopi = clazz.lagKopi();
        System.out.println(kopi);

        MyGenericsInterface<MyImplementingClass> kopi2 = clazz.lagKopi();
        System.out.println(kopi2);
    }

}
