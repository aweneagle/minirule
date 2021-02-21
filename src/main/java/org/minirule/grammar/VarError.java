package org.minirule.grammar;

public class VarError implements IVar {
   
    
    private RuleException ruleError;
    private FuncCallException funcError;

    public Class<?> getType() {
        return VarError.class;
    }

    public RuleException getException() {
        return ruleError;
    }

    public Object getValue() {
        return this;
    }

    public Object cast(Class<?> type) throws RuleException {
        throw new RuleException(RuleError.TypeCastFail, "[VarError] -> [" + type.getTypeName() + "]");
    }

    public VarError(FuncCallException e) {
        this.funcError = e;
    }

    public VarError(RuleException e) {
        this.ruleError = e;
    }

    // convert into float
    public double toDouble() throws RuleException {
        throw new RuleException(RuleError.TypeCastFail);
    }
    // convert into string
    public String toString() {
        if (this.ruleError != null) {
            return this.ruleError.getMessage();
        } else if (this.funcError != null) {
            return this.funcError.getMessage();
        } else {
            return "";
        }
    }
    // check if var is a sring or not
    public boolean isString() {
        return true;
    }

    // check if var is null or not
    public boolean isNull() {
        return this.ruleError == null && this.funcError == null;
    }

    // check if var is number or not
    public boolean isNumber() {return false;}

    // check if var is boolean or not
    public boolean isBoolean() {return false;}

    // get property
    public IVar getProperty(String field) throws RuleException {
        return this;
    }

    // set property
    public void setProperty(String field, IVar value) throws RuleException {
        return ;
    } 

    // get element by index
    public IVar getElement(int index) throws RuleException {
        return this;
    } 
    // get boolean
    public boolean getBoolean() {
        return false;
    }
    // get property by index
    public IVar property(IVar index) throws RuleException {
        return this;
    }
}
