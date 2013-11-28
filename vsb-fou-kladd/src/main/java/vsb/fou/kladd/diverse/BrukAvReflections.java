package vsb.fou.kladd.diverse;

import java.lang.reflect.Method;

/**
 * @author Vegard S. Bye
 */
public class BrukAvReflections {

    public static void main(String[] args) {
        Method enclosingMethod = BrukAvReflections.class.getEnclosingMethod();
        System.out.println("enclosingMethod = " + enclosingMethod);
        new BrukAvReflections().doIt();
    }

    public void doIt() {
        class Local {
        }
        Method enclosingMethod = Local.class.getEnclosingMethod();
        System.out.println("enclosingMethod = " + enclosingMethod);
        System.out.println("enclosingMethod = " + enclosingMethod.getDeclaringClass());

        System.out.println("assignableFrom = " + CharSequence.class.isAssignableFrom("hei".getClass()));
        Object entity = new Object();
        System.out.println("assignableFrom = " + CharSequence.class.isAssignableFrom(entity.getClass()));
    }
}
