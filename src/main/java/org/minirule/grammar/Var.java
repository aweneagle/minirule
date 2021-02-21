package org.minirule.grammar;

import java.util.List;
import java.util.Map;

// class Var wraps these data types:
//  Integer, String, Float, Double, Boolean, Class<T>
public class Var 
{
    @SuppressWarnings("rawtypes")
	public static IVar New(Object obj) {
        if (obj == null) {
            return new VarNull();
        } else if (obj instanceof Boolean) {
            return new VarBoolean((Boolean)obj);
        } else if (obj instanceof String) {
            return new VarString((String)obj);
        } else if (obj instanceof Number) {
            return new VarNumber((Number)obj);
        } else if (obj instanceof Map) {
            return new VarMap((Map)obj);
        } else if (obj instanceof List) {
            return new VarList((List)obj);
        } else if (obj instanceof RuleException) {
            return new VarError((RuleException)obj);
        } else if (obj instanceof FuncCallException) {
            return new VarError((FuncCallException)obj);
        } else {
            return new VarClass(obj);
        }
    }
}
