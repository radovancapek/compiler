public class Main {
    public static void main(String[] args) throws IOException {

        InputStream inputStream = new FileInputStream("input.txt");
        CalcLexer lexer = new CalcLexer(new ANTLRInputStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser parser = new CalcParser(tokens);
        ParseTree tree = parser.start();
        MyCalcListener listener = new MyCalcListener();

        ParseTreeWalker.DEFAULT.walk(listener, tree);


        for(Number number : listener.getStack()) {
            System.out.println(number.getValue());
        }
    }
}
