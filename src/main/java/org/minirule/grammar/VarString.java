package org.minirule.grammar;

// class VarString wraps these data types:
//  String
public class VarString implements IVar {
    private String val;

    public Class<?> getType() {
        return String.class;
    }

    public Object getValue() {
        return this.val;
    }

    public VarString(String val) {
        this.val = val;
    }

    // convert data type
    public Object cast(Class<?> type) throws RuleException {
        if (type == String.class) {
            return this.val;
        }
        throw new RuleException(RuleError.TypeCastFail, "[String] -> [" + type.getTypeName() + "]");
    }
    // convert into float
    public double toDouble() throws RuleException {
        throw new RuleException(RuleError.TypeCastFail);
    }
    // convert into string
    public String toString() {
        return this.val;
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
    public boolean isBoolean() {return false;}

    // get property
    public IVar getProperty(String field) throws RuleException {
        throw new RuleException(RuleError.FieldOfString);
    }

    // set property
    public void setProperty(String field, IVar value) throws RuleException {
        throw new RuleException(RuleError.FieldOfString);
    } 
    // get element by index
    public IVar getElement(int index) throws RuleException {
        throw new RuleException(RuleError.IndexOfString);
    }
    // get boolean
    public boolean getBoolean() {
        return false;
    }
    // get property by index
    public IVar property(IVar index) throws RuleException {
        throw new RuleException(RuleError.FieldOfString);
    }
}
