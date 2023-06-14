import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SymbolTable {

    static class TableRow {

        String identifier;
        String kind;
        Class<?> type;
        int scope;
        List<Class<?>> parameters = new ArrayList<>();

        public TableRow(String identifier, String kind, Class<?> type, int scope) {
            this.identifier = identifier;
            this.kind = kind;
            this.type = type;
            this.scope = scope;
        }

        public TableRow(String identifier, String kind, Class<?> type, int scope, List<Class<?>> parameters) {
            this.identifier = identifier;
            this.kind = kind;
            this.type = type;
            this.scope = scope;
            this.parameters = parameters;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TableRow other = (TableRow) o;

            return Objects.equals(identifier, other.identifier) &&
                    Objects.equals(kind, other.kind) &&
                    scope >= other.scope;
        }

        @Override
        public int hashCode() {
            return Objects.hash(identifier, kind, type, scope);
        }

        @Override
        public String toString() {
            return "TableRow{" +
                    "identifier='" + identifier + '\'' +
                    ", kind='" + kind + '\'' +
                    ", type=" + type +
                    ", scope='" + scope + '\'' +
                    ", parameters='" + parameters + '\'' +
                    '}';
        }

    }


    ArrayList<TableRow> symbolTable = new ArrayList<>();

    public void addFunction(String functionName, Class<?> returnType, List<Class<?>> parameters) {
        TableRow row = new TableRow(functionName, "function", returnType, 1, parameters);
        symbolTable.add(row);
    }

    public boolean isFunctionDefined(String identifier) {
        TableRow row = new TableRow(identifier, "function", null, 1);
        return symbolTable.contains(row);
    }

    public List<Class<?>> getFunctionParameters(String identifier) {
        for (TableRow row : symbolTable) {
            if (Objects.equals(row.identifier, identifier)) {
                return row.parameters;
            }
        }

        return null;
    }

    public void addVariable(String identifier, Class<?> type, int scope) {
        TableRow row = new TableRow(identifier, "variable", type, scope);
        symbolTable.add(row);
    }

    public void variableCleanup(int scope) {
        symbolTable.removeIf(row -> row.scope == scope && Objects.equals(row.kind, "variable"));
    }

    public boolean isVariableDefined(String identifier, int scope) {
        TableRow row = new TableRow(identifier, "variable", null, scope);
        return symbolTable.contains(row);
    }

    public void updateVariableType(String identifier, Class<?> type) {
        for (TableRow row : symbolTable) {
            if (Objects.equals(row.identifier, identifier)) {
                row.type = type;
                break;
            }
        }
    }

    public Class<?> getType(String identifier, String kind, int scope) {
        TableRow row = new TableRow(identifier, kind, null, scope);
        int index = symbolTable.indexOf(row);
        return symbolTable.get(index).type;
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "symbolTable=" + symbolTable +
                '}';
    }
}
