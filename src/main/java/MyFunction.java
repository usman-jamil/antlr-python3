import java.util.Collection;

public class MyFunction {
    private String name;
    private int line;
    private Collection<MyParameter> parameters;

    public MyFunction(String name, int line, Collection<MyParameter> parameters) {
        this.name = name;
        this.line = line;
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyFunction aClass = (MyFunction) o;

        if (name != null ? !name.equals(aClass.name) : aClass.name != null) return false;
        return parameters != null ? parameters.equals(aClass.parameters) : aClass.parameters == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyFunction{" +
                "\r\nname='" + name + '\'' +
                "\r\nparameters=" + parameters +
                "\r\nline=" + line +
                '}';
    }
}