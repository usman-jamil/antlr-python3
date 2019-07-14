import java.util.Collection;

public class MyContent {
    private String name;
    private Collection<MyFunction> functions;

    public MyContent(String name, Collection<MyFunction> functions) {
        this.name = name;
        this.functions = functions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyContent aProgram = (MyContent) o;

        if (name != null ? !name.equals(aProgram.name) : aProgram.name != null) return false;
        return functions != null ? functions.equals(aProgram.functions) : aProgram.functions == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (functions != null ? functions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyContent{" +
                "\r\nname='" + name + '\'' +
                "\r\nfunctions=" + functions +
                '}';
    }
}