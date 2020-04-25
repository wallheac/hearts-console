package com.designwork.cardgame;

public class Pair<T1, T2> {

    public final T1 first;
    public final T2 second;

    public Pair(T1 t1, T2 t2) {
        first = t1;
        second = t2;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "{" + first + "," + second + "}";
    }

    public static <T1, T2> Pair<T1, T2> of(T1 t1, T2 t2) {
        return new Pair<T1, T2>(t1, t2);
    }
}
