import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.ParserRuleContext;

public class Main {
    private static String readFile(File file, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, encoding);
    }

    public static ParserRuleContext parse(File file) throws IOException {
        String code = readFile(file, Charset.forName("UTF-8"));
        TypeScriptLexer lexer = new TypeScriptLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        TypeScriptParser parser = new TypeScriptParser(tokens);
        parser.setTokenStream(tokens);
        parser.setTrace(false);
        ParserRuleContext tree = parser.program(); // invoke the entry point of our grammar
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        return tree;
    }

//    public static class AstPrinter {
//        public static void print(ParserRuleContext tree) {
////            recursively print the whole tree,
////            skip the nodes which have just one children which is another parser rule (no terminals)
//            for (int i = 0; i < tree.getChildCount(); i++) {
//                if (tree.getChild(i).getChildCount() > 1) {
//                    print((ParserRuleContext) tree.getChild(i));
//                } else {
//                    System.out.println(tree.getChild(i).getText());
//                }
//            }
//
//
//        }
//    }

    public static class AstPrinter {
        public static void print(ParserRuleContext tree) {
            print(tree, 0);
        }

        private static void print(ParserRuleContext tree, int indentation) {
            for (int i = 0; i < indentation; i++) {
                System.out.print("  ");
            }
            System.out.println(tree.getText() + " ");
//            System.out.println(tree.toStringTree());


            for (int i = 0; i < tree.getChildCount(); i++) {
                if (tree.getChild(i).getChildCount() > 1) {
                    print((ParserRuleContext) tree.getChild(i), indentation + 1);
                } else {
                    for (int j = 0; j < indentation + 1; j++) {
                        System.out.print("  ");
                    }
                    System.out.println(tree.getChild(i).getText());
//                    System.out.println(tree.getChild(i).toStringTree());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        boolean testRigApproach = false;

//        Standard TestRig approach
//        TestRigAndy.main(new String[]{"TypeScript", "program", "/Volumes/SSD/Data/Devel/grammars-v4/javascript/typescript/examples/Class.ts", "-tree"});
//        TestRig.main(new String[]{"TypeScript", "program", "/Volumes/SSD/Data/Devel/grammars-v4/javascript/typescript/examples/Class.ts", "-tree"});

//      Andy TestRig approach
//        if (testRigApproach) {
//            String[] andyArgs = new String[]{"TypeScript", "program", "/Volumes/SSD/Data/Devel/grammars-v4/javascript/typescript/examples/Class.ts", "-tree"};
//            TestRigAndy testRig = new TestRigAndy(andyArgs);
//            if (andyArgs.length >= 2) {
//                testRig.process();
//            }
//        }

        // Nicer approach
        File file = new File("/Volumes/SSD/Data/Devel/grammars-v4/javascript/typescript/examples/Class.ts");
        ParserRuleContext tree = parse(file);
        AstPrinter printer = new AstPrinter();
        printer.print(tree);

        // Use the lexer object as needed
        // print hello
        System.out.println("parsing complete mate");
    }
}

// jar tf target/typescript-1.0-SNAPSHOT.jar
