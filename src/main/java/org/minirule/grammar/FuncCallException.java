package org.minirule.grammar;

public class FuncCallException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public FuncCallException(String msg) {
        super(msg);
    }

    public FuncCallException(RuleError err) {
        super(err.msg);
    }
}
