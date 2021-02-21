package org.minirule.grammar;

/**
 * Hello world!
 *
 */
public enum RuleError 
{
    FieldOfNull(-1000, "field of null"),
    FieldOfNumber(-1001, "field of number"),
    FieldOfString(-1001, "field of string"),
    FieldOfBoolean(-1001, "field of boolean"),
    FieldOfMap(-1001, "field of map"),
    IndexOfNull(-1000, "index of null"),
    IndexOfNumber(-1001, "index of number"),
    IndexOfString(-1001, "index of string"),
    IndexOfBoolean(-1001, "index of boolean"),
    IndexOfClass(-1001, "index of class"),
    IndexOfMap(-1001, "index of map"),
    IndexOutOfRange(-1001, "index out of range"),
    FieldNotFound(-1001, "field not found"),
    FieldNotAccessable(-1002, "field not accessable"),
    TypeCastFail(-1003, "type cast failed"),
    CmpFail(-1004, "compare failed"),
    CalFail(-1005, "calculate failed"),
    CalDivByZero(-1005, "calculate div by zero"),
    FuncNotFound(-1006, "function not found"),
    FuncNotAccessable(-1006, "function not accessable"),
    FuncArgsNumber(-1007, "function's args number is not right"),
    FuncArgsNotArray(-1007, "function's last params is not an array"),
    TypeUnknown(-1, "unknown data type"),
    ConditionNotBool(-1003, "type not Bool"),
    CalOpUnknown(-1, "unknown calculate op type"),

    TryAgain(-1003, "try again"),
    TranFailed(-1003, "try again");

    public int code;
    public String msg;
    private RuleError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}