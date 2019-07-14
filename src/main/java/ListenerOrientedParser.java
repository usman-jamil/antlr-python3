import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.lang.SuppressWarnings;

import com.google.gson.Gson;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ListenerOrientedParser{
    public void exportToJSON() throws IOException, URISyntaxException {
        final MyContent result = parse();
        Gson gson = new Gson();
        final String json = gson.toJson(result);
        System.out.printf("code below: %n '%s' %n has been parsed to object: %n '%s'%n", "functions.py", json);
    }

    public MyContent parse() throws IOException, URISyntaxException {
        final URL res = Paths.get("functions.py").toUri().toURL();
        final CharStream inp = CharStreams.fromStream(res.openStream());
        final Python3Lexer lex = new Python3Lexer(inp);
        final TokenStream toks = new CommonTokenStream(lex);
        final Python3Parser parser = new Python3Parser(toks);
        
        InputListener inputListener = new InputListener();
        parser.file_input().enterRule(inputListener);
        
        return inputListener.getContent();
    }

    class InputListener extends Python3BaseListener {
        private MyContent content;
        private FunctionListener functionListener;

        public InputListener() {
            content = null;
            functionListener = new FunctionListener();
        }

        Consumer<Python3Parser.StmtContext> stmtConsumer = new Consumer<Python3Parser.StmtContext>() {
            public void accept(Python3Parser.StmtContext stmt) {
                if(stmt != null && stmt.compound_stmt() != null) {
                    stmt.compound_stmt().funcdef().enterRule(functionListener);
                }
            };
        };

        @Override
        public void enterFile_input(Python3Parser.File_inputContext ctx) {
            ctx.stmt().forEach(stmtConsumer);
        }

        public MyContent getContent() {
            Collection<MyFunction> functions = functionListener.getFunctions();
            content = new MyContent("functions.py", functions);
            return content;
        }
    }

    class FunctionListener extends Python3BaseListener {
        private Collection<MyFunction> functions;

        public FunctionListener() {
            functions = new ArrayList<>();
        }

        @Override
        public void enterFuncdef(Python3Parser.FuncdefContext ctx) {
            String methodName = ctx.NAME().getText();
            int line = ctx.getStart().getLine();
            ParameterListener parameterListener = new ParameterListener();
            ctx.parameters().typedargslist().tfpdef().forEach(parameter -> parameter.enterRule(parameterListener));
            Collection<MyParameter> parameters = parameterListener.getParameters();
            functions.add(new MyFunction(methodName, line, parameters));
        }

        public Collection<MyFunction> getFunctions() {
            return functions;
        }
    }

    class ParameterListener extends Python3BaseListener {

        private Collection<MyParameter> parameters;

        public ParameterListener() {
            parameters = new ArrayList<>();
        }

        @Override
        public void enterTfpdef(Python3Parser.TfpdefContext ctx) {
            String parameterName = ctx.NAME().getText();
            parameters.add(new MyParameter(parameterName));
        }

        public Collection<MyParameter> getParameters() {
            return parameters;
        }
    }
}