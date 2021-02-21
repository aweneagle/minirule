package org.minirule.grammar;

import java.lang.reflect.Field;

// class VarClass wrap type:
//      Class<T>
public class VarClass implements IVar {
    private Object val;
    private Class<?> type;

    public Class<?> getType() {
        return this.type;
    }

    public Object getValue() {
        return this.val;
    }

    public VarClass(Object val) {
        if (val == null) {
            this.type = null;
            this.val = null;
        } else {
            this.type = val.getClass();
            this.val = val;
        }
    }

    // convert data type
    public Object cast(Class<?> type) throws RuleException {
        if (this.type == type) {
            return this.val;
        }
        throw new RuleException(RuleError.TypeCastFail, "[" + this.type.getTypeName()+ "] -> [" + type.getTypeName() + "]");
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
        if (val == null) {
            throw new RuleException(RuleError.FieldOfNull);
        }
        try {
            Field f = val.getClass().getDeclaredField(field);
            return Var.New(f.get(val));
        } catch(NoSuchFieldException e) {
            throw new RuleException(RuleError.FieldNotFound);
        } catch(Exception e) {
            throw new RuleException(RuleError.FieldNotAccessable);
        }
    }

    // set property
    public void setProperty(String field, IVar value) throws RuleException {
        if (val == null) {
            throw new RuleException(RuleError.FieldOfNull);
        }
        try {
            Field f = val.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(val, value.cast(f.getType()));
            return;
        } catch(NoSuchFieldException e) {
            throw new RuleException(RuleError.FieldNotFound);
        } catch(Exception e) {
            throw new RuleException(RuleError.TypeCastFail);
        }
    } 

    // get element by index
    public IVar getElement(int index) throws RuleException {
        throw new RuleException(RuleError.IndexOfClass);
    }

    // get property by index
    public IVar property(IVar field) throws RuleException {
        if (field.isString()) {
            return getProperty(field.toString());
        } else {
            throw new RuleException(RuleError.TypeUnknown, "unknow field type for class, type:" + field.getType().getTypeName());
        }
    }

    // get boolean
    public boolean getBoolean() {
        return false;
    }
}
