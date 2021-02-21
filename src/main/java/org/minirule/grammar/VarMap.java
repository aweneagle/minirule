package org.minirule.grammar;
import java.util.Map;

public class VarMap implements IVar {
    
    @SuppressWarnings("rawtypes")
    private Map val;

    public Class<?> getType() {
        return val.getClass();
    }

    public Object getValue() {
        return this.val;
    }

    public Object cast(Class<?> type) throws RuleException {
        if (type == getType()) {
            return this.val;
        }
        throw new RuleException(RuleError.TypeCastFail, "[" + getType().getTypeName() + "] -> [" + type.getTypeName() + "]");
    }

    @SuppressWarnings("rawtypes")
    public VarMap(Map val) {
        this.val = val;
    }

    // convert into float
    public double toDouble() throws RuleException {
        throw new RuleException(RuleError.TypeCastFail);
    }
    // convert into string
    public String toString() {
        return "";
    }
    // check if var is a sring or not
    public boolean isString() {
        return false;
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
        return Var.New(val.get(field));
    }

    // set property
    public void setProperty(String field, IVar value) throws RuleException {
        throw new RuleException(RuleError.IndexOfMap);
    } 
    // get element by index
    public IVar getElement(int index) throws RuleException {
        throw new RuleException(RuleError.IndexOfMap);
    }
    // get boolean
    public boolean getBoolean() {
        return false;
    }
    // get property by index
    public IVar property(IVar field) throws RuleException {
        if (field.isString()) {
            return getProperty(field.toString());
        } else {
            throw new RuleException(RuleError.TypeUnknown, "unknow field type for class, type:" + field.getType().getTypeName());
        }
    }
}
