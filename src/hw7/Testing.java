package hw7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Testing {

    public static void start(Class c) {

        ArrayList<Method> tests = new ArrayList<>();
        Method beforeSuite = null;
        Method afterSuite = null;

        Object obj = null;
        try {
            obj = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Не получилось создать экземпляр класса " + c.getName());
        }

        for (Method m : c.getDeclaredMethods()) {

            if (m.getAnnotation(Test.class) != null) {
                if (!m.isAccessible()) {
                    m.setAccessible(true);
                }
                tests.add(m);
            }

            if (m.getAnnotation(BeforeSuite.class) != null) {
                if (beforeSuite != null) {
                    throw new RuntimeException("В классе может быть только один метод с аннотацией @BeforeSuite");
                }

                if (!m.isAccessible()) {
                    m.setAccessible(true);
                }

                beforeSuite = m;
            }

            if (m.getAnnotation(AfterSuite.class) != null) {
                if (afterSuite != null) {
                    throw new RuntimeException("В классе может быть только один метод с аннотацией @AfterSuite");
                }

                if (!m.isAccessible()) {
                    m.setAccessible(true);
                }

                afterSuite = m;
            }
        }

        if (beforeSuite != null) {
            try {
                beforeSuite.invoke(obj, null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Не получилось выполнить метод " + beforeSuite.getName());
            }
        }

//        tests.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));
        tests.sort(((o1, o2) -> o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority()));

        for (Method test : tests) {
            try {
                test.invoke(obj, null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Не получилось выполнить метод " + test.getName());
            }
        }

        if (afterSuite != null) {
            try {
                afterSuite.invoke(obj, null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Не получилось выполнить метод " + afterSuite.getName());
            }
        }
    }

    public static void start(String className) throws ClassNotFoundException {
        start(Class.forName(className));
    }
}
