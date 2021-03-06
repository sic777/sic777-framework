package com.sic777.common.utils.container.tuple;


/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
public class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {

    private static final long serialVersionUID = 1L;
    public final F sixth;

    public SixTuple(A first, B second, C third, D fourth, E fifth, F sixth) {
        super(first, second, third, fourth, fifth);
        this.sixth = sixth;
    }
}
