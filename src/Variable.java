

public class Variable {

    Class<?> type;

    public Variable() {
    }

    public Variable(Class<?> type) {
        this.type = type;
    }

    public Variable(Object object) {
        switch (object.getClass().getSimpleName()) {
            case "Boolean" -> type = boolean.class;
            case "Integer" -> type = int.class;
            case "String" -> type = String.class;
            case "Double" -> type = double.class;
        }
    }

    public boolean validType(Class<?> otherType) {
        return type == otherType || type == null || otherType == null ||
                (otherType == String.class && type != null);
    }
}
