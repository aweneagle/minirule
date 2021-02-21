package org.minirule.grammar;

public class VarNull implements IVar {
    
    public Class<?> getType() {
        return VarNull.class;
    }

    public Object getValue() {
        return null;
    }

    public boolean getBoolean() {
        return false;
    }

    public Object cast(Class<?> type) throws RuleException {
        throw new RuleException(RuleError.TypeCastFail, "[Null] -> [" + type.getTypeName() + "]");
    }

    // convert into float
    public double toDouble() throws RuleException {
        throw new RuleException(RuleError.TypeCastFail, "[Null] -> [Double]");
    }
    // convert into string
    public String toString() {
        return "null";
    }
    // check if var is a sring or not
    public boolean isString() {
        return true;
    }

    // check if var is null or not
    public boolean isNull() {
        return true;
    }

    // check if var is number or not
    public boolean isNumber() {return false;}

    // check if var is boolean or not
    public boolean isBoolean() {return true;}

    // get property
    public IVar getProperty(String field) throws RuleException {
        return this;
    }

    // set property
    public void setProperty(String field, IVar value) throws RuleException {
        throw new RuleException(RuleError.FieldOfNull);
    } 

    // get element by index
    public IVar getElement(int index) throws RuleException {
        return this;
    }
    // get property by index
    public IVar property(IVar index) throws RuleException {
        return this;
    }
}
