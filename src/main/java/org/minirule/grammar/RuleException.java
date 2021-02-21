package org.minirule.grammar;

public class RuleException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public RuleError error;

    public RuleException(RuleError error, String info) {
        super(error.msg + "," + info);
        this.error = error;
    }

    public RuleException(RuleError error) {
        super(error.msg);
        this.error = error;
    }
}
