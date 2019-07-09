package Core;

public class Operation {
    public enum Type {
        SHIFT,
        ADD,
        ZERO,
        OUT,
        IN,
        BEGIN_WHILE,
        END_WHILE
    }

    public Type type = null; //тип операции
    public int arg = 1; //кол-во повторений

    public Operation(Type type, int arg) {
        this.type = type;
        this.arg = arg;
    }

    public Operation(Type type) {
        this.type = type;
    }

    public Operation clone() {
        return new Operation(type, arg);
    }
}
