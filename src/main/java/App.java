import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.nio.file.Paths;
import java.net.URL;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.gui.TreeViewer;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

//import com.google.gson.Gson;

public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        new ListenerOrientedParser().exportToJSON();
        //showParseTree();
    }

    public static void showParseTree() throws IOException, URISyntaxException {
        final URL res = Paths.get("functions.py").toUri().toURL();
        final CharStream inp = CharStreams.fromStream(res.openStream());
        final Python3Lexer lex = new Python3Lexer(inp);
        final TokenStream toks = new CommonTokenStream(lex);
        final Python3Parser parser = new Python3Parser(toks);
        final ParseTree tree = parser.file_input();

        final JFrame frame = new JFrame("Antlr AST");
    
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 500, 500);
    
        final TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewr.setScale(1.0); // scale a little
        scrollPane.getViewport().add(viewr);
    
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 500);
        frame.setVisible(true);
      }
}
