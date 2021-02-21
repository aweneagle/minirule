package org.minirule.grammar;

// class VarBoolean wraps these data types:
//  Boolean
public class VarBoolean implements IVar{
    
    private Boolean val;

    public Class<?> getType() {
        return Boolean.class;
    }

    public Object getValue() {
        return this.val;
    }

    public boolean getBoolean() {
        return this.val;
    }

    public Object cast(Class<?> type) throws RuleException {
        if (type == Boolean.class) {
            return this.val;
        }
        throw new RuleException(RuleError.TypeCastFail, "[Boolean] -> [" + type.getTypeName() + "]");
    }

    public VarBoolean(Boolean val) {
        this.val = val;
    }

    // convert into float
    public double toDouble() throws RuleException {
        throw new RuleException(RuleError.TypeCastFail);
    }
    // convert into string
    public String toString() {
        return this.val.toString();
    }
    // check if var is a sring or not
    public boolean isString() {
        return true;
    }

    // check if var is null or not
    public boolean isNull() {
        return this.val == null;
    }

    // check if var is number or not
    public boolean isNumber() {return false;}

    // check if var is boolean or not
    public boolean isBoolean() {return true;}

    // get property
    public IVar getProperty(String field) throws RuleException {
        throw new RuleException(RuleError.FieldOfBoolean);
    }

    // set property
    public void setProperty(String field, IVar value) throws RuleException {
        throw new RuleException(RuleError.FieldOfBoolean);
    } 

    // get element by index
    public IVar getElement(int index) throws RuleException {
        throw new RuleException(RuleError.FieldOfBoolean);
    }
    // get property by index
    public IVar property(IVar index) throws RuleException {
        throw new RuleException(RuleError.FieldOfBoolean);
    }
}
