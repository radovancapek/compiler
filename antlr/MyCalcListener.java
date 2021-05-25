public class MyCalcListener extends CalcBaseListener {
    Stack<Number> stack = new Stack();

    public Stack<Number> getStack() {
        return stack;
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void enterFact(CalcParser.FactContext ctx) {
        super.enterFact(ctx);
    }

    @Override
    public void enterAbs(CalcParser.AbsContext ctx) {
        super.enterAbs(ctx);
    }

    @Override
    public void enterInt(CalcParser.IntContext ctx) {
        super.enterInt(ctx);
    }

    @Override
    public void exitFloat(CalcParser.FloatContext ctx) {
        this.stack.push(new Number(Type.FLOAT, ctx.getText()));
        super.exitFloat(ctx);
    }

    @Override
    public void exitInt(CalcParser.IntContext ctx) {
        this.stack.push(new Number(Type.INT, ctx.getText()));
        super.exitInt(ctx);
    }

    @Override
    public void exitAbs(CalcParser.AbsContext ctx) {
        Number num = stack.pop();
        float val = Float.parseFloat(num.getValue());
        num.setValue(String.valueOf(Math.abs(val)));
        stack.push(num);
        super.exitAbs(ctx);
    }

    private int factorial(int n){
        if (n == 0) return 1;
        else return(n * factorial(n-1));
    }

    @Override
    public void exitFact(CalcParser.FactContext ctx) {
        Number number = this.stack.pop();
        if(number.getType().equals(Type.INT) && (Integer.parseInt(number.getValue()) >= 0)) {
            int num = Integer.parseInt(number.getValue());
            this.stack.push(new Number(Type.INT, String.valueOf(factorial(num))));
        } else {
            System.err.println("Vstup není nezáporný integer.");
        }
        super.exitFact(ctx);
    }

    @Override
    public void exitPow(CalcParser.PowContext ctx) {
        float exp = Float.parseFloat(this.stack.pop().getValue());
        float num = Float.parseFloat(this.stack.pop().getValue());
        float res = (float) Math.pow(num, exp);
        this.stack.push(new Number(Type.FLOAT, String.valueOf(res)));
        super.exitPow(ctx);
    }

    @Override
    public void exitAddSub(CalcParser.AddSubContext ctx) {
        Number n1 = stack.pop();
        Number n2 = stack.pop();
        if(n1.getType().equals(Type.INT) && n2.getType().equals(Type.INT)) {
            int n1Val = Integer.parseInt(n1.getValue());
            int n2Val = Integer.parseInt(n2.getValue());
            if(ctx.op.getType() == CalcParser.ADD)
                stack.push(new Number(Type.INT, String.valueOf(n1Val+n2Val)));
            if(ctx.op.getType() == CalcParser.SUB)
                stack.push(new Number(Type.INT, String.valueOf(n2Val-n1Val)));
        } else {
            Float n1Val = Float.parseFloat(n1.getValue());
            Float n2Val = Float.parseFloat(n2.getValue());
            if(ctx.op.getType() == CalcParser.ADD)
                stack.push(new Number(Type.FLOAT, String.valueOf(n1Val+n2Val)));
            if(ctx.op.getType() == CalcParser.SUB)
                stack.push(new Number(Type.FLOAT, String.valueOf(n2Val-n1Val)));
        }
        super.exitAddSub(ctx);
    }

    @Override
    public void exitMulDiv(CalcParser.MulDivContext ctx) {
        Float num1 = Float.valueOf(stack.pop().getValue());
        Float num2 = Float.valueOf(stack.pop().getValue());
        if(ctx.op.getType() == CalcParser.MUL)
            stack.push(new Number(Type.FLOAT, String.valueOf(num1*num2)));
        if(ctx.op.getType() == CalcParser.DIV) {
            if(num1 != 0) stack.push(new Number(Type.FLOAT, String.valueOf(num2/num1)));
            else System.err.println("Dělení nulou.");
        }

        super.exitMulDiv(ctx);
    }
}
