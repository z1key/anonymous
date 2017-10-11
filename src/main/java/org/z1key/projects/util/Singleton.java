package org.z1key.projects.util;

/**
 * Created by User on 15.05.2017.
 */
public enum Singleton {

    INSTANCE("One");

    private String value;

    Singleton(String value) {
        this.value = value;
    }

    public void print() {
        System.out.println(value);
    }

    static class Test {
        public static void main(String[] args) {
            Singleton singleton = Singleton.INSTANCE;
            singleton.print();
            System.out.println(singleton.getClass());
        }
    }
}

