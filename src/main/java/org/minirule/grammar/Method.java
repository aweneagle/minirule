package org.minirule.grammar;

import java.util.HashMap;

public abstract class Method implements Cloneable{
    protected HashMap<String, Object> context;
    public void setContext(HashMap<String, Object> context) {
        this.context = context;
    }

    public Method copy() {
        try {
            Method cp = (Method) super.clone();
            cp.context = null;
            return cp;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public abstract IVar call(IVar ... params) throws FuncCallException, RuleException;
}
