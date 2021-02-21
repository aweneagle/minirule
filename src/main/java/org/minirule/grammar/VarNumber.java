package org.minirule.grammar;

// class VarNumber wraps these data types:
//  Integer, Double, Float, Short
public class VarNumber implements IVar {
    private Number val;
    public Class<?> type;

    public Class<?> getType() {
        return this.type;
    }

    // decide what type to cast by Compare or Calculater
    public static Class<?> union(Class<?> a, Class<?> b) throws RuleException {
        String aType = type2short(a);
        String bType = type2short(b);
        switch (aType) {
            case "d":
            return Double.class;
            case "f":
            switch (bType) {
                case "d":
                    return Double.class;
                default:
                    return Float.class;
            }
            case "l":
            switch (bType) {
                case "d":
                    return Double.class;
                case "f":
                    return Float.class;
                default:
                    return Long.class;
            }
            case "i":
            switch (bType) {
                case "d":
                    return Double.class;
                case "f":
                    return Float.class;
                case "l":
                    return Long.class;
                default:
                    return Integer.class;
            }
            case "s":
            switch (bType) {
                case "d":
                    return Double.class;
                case "f":
                    return Float.class;
                case "l":
                    return Long.class;
                case "i":
                    return Integer.class;
                case "s":
                    return Short.class;
            }
            default:
                throw new RuleException(RuleError.TypeUnknown);
        }
    }

        // it is short for Number types, like:
        // "i" for Integer,int
        // "f" for Float,float
        // "d" for Double,double
        // "s" for Short,short
    private static String type2short(Class<?> type) {
        if (type == Integer.class || type == int.class) {
            return "i";
        } else if (type == Short.class || type == short.class) {
            return "s";
        } else if (type == Double.class || type == double.class) {
            return "d";
        } else if (type == Float.class || type == float.class) {
            return "f";
        } else if (type == Long.class || type == long.class) {
            return "l";
        } else {
            return "";
        }
    }

    public Object getValue() {
        return this.val;
    }

    public VarNumber(Number val) {
        this.val = val;
        this.type = val.getClass();
    }

    // convert data type
    public Object cast(Class <?> to) throws RuleException {
        switch (to.getTypeName()) {
            case "int":
            case "java.lang.Integer":
                return this.val.intValue();
            case "double":
            case "java.lang.Double":
                return this.val.doubleValue();
            case "float":
            case "java.lang.Float":
                return this.val.intValue();
            case "short":
            case "java.lang.Short":
                return this.val.intValue();
            default:
                throw new RuleException(RuleError.TypeCastFail, "[" + this.type.getTypeName() + "] -> [" + to.getTypeName() + "]");
        }
    }
    // convert into float
    public double toDouble() throws RuleException {
        return this.val.doubleValue();
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
    public boolean isNumber() {return true;}

    // check if var is boolean or not
    public boolean isBoolean() {return false;}

    // get property
    public IVar getProperty(String field) throws RuleException {
        throw new RuleException(RuleError.FieldOfNumber);
    }

    // set property
    public void setProperty(String field, IVar value) throws RuleException {
        throw new RuleException(RuleError.FieldOfNumber);
    } 
    // get element by index
    public IVar getElement(int index) throws RuleException {
        throw new RuleException(RuleError.IndexOfNumber);
    }
    // get boolean
    public boolean getBoolean() {
        return false;
    }
    // get property by index
    public IVar property(IVar index) throws RuleException {
        throw new RuleException(RuleError.FieldOfNumber);
    }
}
