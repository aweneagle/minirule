package org.minirule.grammar;

import java.util.List;

public class VarList implements IVar {
    
    @SuppressWarnings("rawtypes")
    private List val;

    public Class<?> getType() {
        return val.getClass();
    }

    public Object getValue() {
        return this.val;
    }

    public Object cast(Class<?> type) throws RuleException {
        if (type == val.getClass()) {
            return this.val;
        }
        throw new RuleException(RuleError.TypeCastFail, "[" + getType().getTypeName() + "] -> [" + type.getTypeName() + "]");
    }

    @SuppressWarnings("rawtypes")
    public VarList(List val) {
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


    // get element by index
    public IVar getElement(int index) throws RuleException {
        if (index >= val.size()) {
            throw new RuleException(RuleError.IndexOutOfRange);
        }
        return Var.New(val.get(index));
    }

    // get property
    public IVar getProperty(String field) throws RuleException {
        throw new RuleException(RuleError.FieldOfMap);
    }

    // set property
    public void setProperty(String field, IVar value) throws RuleException {
        throw new RuleException(RuleError.FieldOfMap);
    } 
    // get boolean
    public boolean getBoolean() {
        return false;
    }
    // get property by index
    public IVar property(IVar index) throws RuleException {
        if (index.isNumber()) {
            return this.getElement((Integer)index.cast(Integer.class));
        } else {
            throw new RuleException(RuleError.TypeUnknown, "unknow index type for List, type:" + index.getType().getTypeName());
        }
    }
}
