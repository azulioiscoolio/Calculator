import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator implements ActionListener {

    JFrame frame = new JFrame();

    JPanel gridPanel = new JPanel();

    StringBuilder numScreen = new StringBuilder("0");
    int integer = 0;
    double decimal = 0;

    ArrayList<String> calc = new ArrayList<>();

    boolean isDecimal = false;
    JButton[] gridButtons = new JButton[19];
    JTextField screenText = new JTextField();


    public Calculator(){
        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(600,550);
        frame.setResizable(false);

        Font font = new Font("Silom", Font.BOLD, 50);

        screenText.setBounds(25, 50, 300, 75);
        screenText.setSelectedTextColor(Color.black);
        screenText.setHorizontalAlignment(JTextField.RIGHT);
        screenText.setFont(font);
        screenText.setText(numScreen.toString());
        screenText.setEditable(false);
        screenText.setVisible(true);

        frame.add(screenText);
        frame.setVisible(true);

        font = new Font("Arial Back", Font.BOLD, 30);

        JButton clear = new JButton();
        clear.setText("AC");
        clear.setFont(font);
        clear.setFocusable(false);
        clear.addActionListener(this);
        clear.setVisible(true);
        clear.setBounds(350, 50, 100, 75);
        gridButtons[12] = clear;

        JButton delete = new JButton();
        delete.setText("DEL");
        delete.setFocusable(false);
        delete.addActionListener(this);
        delete.setFont(font);
        delete.setVisible(true);
        delete.setBounds(465, 50, 100, 75);
        gridButtons[13] = delete;

        JButton add = new JButton();
        add.setText("+");
        add.setFont(font);
        add.addActionListener(this);
        add.setVisible(true);
        add.setFocusable(false);
        add.setBounds(410, 150, 100, 58);
        gridButtons[14] = add;

        JButton minus = new JButton();
        Font fontMinus = new Font("Arial Back", Font.BOLD, 45);
        minus.setText("-");
        minus.setFont(fontMinus);
        minus.addActionListener(this);
        minus.setVisible(true);
        minus.setFocusable(false);
        minus.setBounds(410, 213, 100, 58);
        gridButtons[15] = minus;

        JButton times = new JButton();
        times.setText("x");
        times.setFont(font);
        times.addActionListener(this);
        times.setVisible(true);
        times.setFocusable(false);
        times.setBounds(410,277,100,58);
        gridButtons[16] = times;

        JButton div = new JButton();
        div.setText("/");
        div.setFont(font);
        div.addActionListener(this);
        div.setVisible(true);
        div.setFocusable(false);
        div.setBounds(410, 340,100,58);
        gridButtons[17] = div;

        JButton equal = new JButton();
        equal.setText("=");
        equal.setFont(font);
        equal.addActionListener(this);
        equal.setFocusable(true);
        equal.setVisible(true);
        equal.setBounds(100, 420, 400, 60);
        gridButtons[18] = equal;

        frame.add(equal);
        frame.add(div);
        frame.add(times);
        frame.add(add);
        frame.add(minus);
        frame.add(delete);
        frame.add(clear);

        gridPanel.setVisible(true);
        gridPanel.setLayout(new GridLayout(4,3,5,5));
        gridPanel.setBounds(25,150, 300,250);


        for(int i = 0; i < 12; i++){
            gridButtons[i] = new JButton();
            if(i < 9){
                gridButtons[i].setText(Integer.toString(i + 1));
            }else if(i == 9){
                gridButtons[i].setText(".");
            }else if(i == 10){
                gridButtons[i].setText("0");
            }else {
                gridButtons[i].setText("(-)");
            }
            gridPanel.add(gridButtons[i]);
            gridButtons[i].setFont(font);
            gridButtons[i].setVisible(true);
            gridButtons[i].setFocusable(false);
            gridButtons[i].addActionListener(this);
        }
        frame.add(gridPanel);
    }

    public void addDigitToScreen(String buttonStr, int buttonNum){
        if(numScreen.toString().equals("0")){
            numScreen = new StringBuilder(buttonStr);
            integer = buttonNum;
            decimal = buttonNum;
            screenText.setText(numScreen.toString());
        }else{
            numScreen.append(buttonStr);
            if(isDecimal){

            }else{


            }
        }
    }

    public void turnToNeg(){
        integer = -1 * integer;
        decimal = -1.0 * decimal;

        numScreen.insert(0, '-');
        screenText.setText(numScreen.toString());
    }

    public void turnToDouble(){
        if(isDecimal){
            return;
        }
        isDecimal = true;
        numScreen.append('.');
        screenText.setText(numScreen.toString());
    }

    public void clear(){
        integer = 0;
        decimal = 0;
        isDecimal = false;
        calc.clear();
        numScreen = new StringBuilder("0");
        screenText.setText(numScreen.toString());
    }

    public void del(){

        if(numScreen.length() == 1 && numScreen.toString().equals("0")){
            return;
        } else if (numScreen.length() == 1) {
            numScreen = new StringBuilder("0");
            screenText.setText(numScreen.toString());
            integer = 0;
            decimal = 0;
            return;
        }

        boolean isDot = numScreen.charAt(numScreen.length() - 1) == '.';

        if(isDot){
            numScreen.deleteCharAt(numScreen.length() - 1);
            isDecimal = false;
            integer = Integer.parseInt(numScreen.toString());
            decimal = integer;
        }else if(isDecimal){
            numScreen.deleteCharAt(numScreen.length() - 1);
            decimal = Double.parseDouble(numScreen.toString());
        }else{
            numScreen.deleteCharAt(numScreen.length() - 1);
            integer = Integer.parseInt(numScreen.toString());
            decimal = integer;
        }

        screenText.setText(numScreen.toString());
    }

    public void add(int addInt, double addDouble){
        calc.add(numScreen.toString());
        calc.add("+");
        numScreen.replace(0, numScreen.length() , "0");
        screenText.setText(numScreen.toString());

    }

    public void subtract(int addInt, double addDouble){
        calc.add(numScreen.toString());
        calc.add("-");
        numScreen.replace(0, numScreen.length() , "0");
        screenText.setText(numScreen.toString());

    }

    public void multiply(int addInt, double addDouble){
        calc.add(numScreen.toString());
        calc.add("*");
        numScreen.replace(0, numScreen.length() , "0");
        screenText.setText(numScreen.toString());

    }

    public void divide(int addInt, double addDouble){
        calc.add(numScreen.toString());
        calc.add("/");
        numScreen.replace(0, numScreen.length() , "0");
        screenText.setText(numScreen.toString());

    }

    public void equal(int addInt, double addDouble) {
        calc.add(numScreen.toString());

        double sum = 0;
        int intSum = 0;
        String f = "";
        while(!calc.isEmpty()){
            String elem = calc.remove(0);


            if(elem.equals("+")){
                f = "+";
            }else if(elem.equals("-")){
                f = "-";
            }else if(elem.equals("/")){
                f = "/";
            }else if(elem.equals("*")){
                f = "*";
            }else if(f.equals("")){
                sum = Double.parseDouble(elem);
            }else{

                if(f.equals("+")){
                    sum = sum + Double.parseDouble(elem);
                }else if(f.equals("-")){
                    sum = sum - Double.parseDouble(elem);
                }else if(f.equals("*")){
                    sum = sum * Double.parseDouble(elem);
                }else if(f.equals("/")){
                    Double d = Double.parseDouble(elem);

                    if(d == 0){
                        numScreen.replace(0,numScreen.length(),"ERROR");
                        try{
                            Thread.sleep(10);
                        }catch(InterruptedException e){
                            return;
                        }
                        numScreen.replace(0,numScreen.length(),"ClEARING");
                        clear();
                        return;
                    }else{
                        sum = sum / d;
                    }

                }

            }

        }

        if(isDecimal){
            decimal = sum;
        }else{
            intSum = (int) sum;
            integer = intSum;
        }

        if(!isDecimal){
            numScreen.replace(0,numScreen.length(), Integer.toString(intSum));
            calc.add(intSum +"");

        }else{
            numScreen.replace(0,numScreen.length(), Double.toString(sum));
            calc.add(sum+"");
        }

        screenText.setText(numScreen.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 19; i++){
            if(i < 9 || i == 10){
                if(e.getSource() == gridButtons[i]){
                    addDigitToScreen(gridButtons[i].getText(), i + 1);
                }
            }else if(i == 9){
                if(e.getSource() == gridButtons[i]){
                    turnToDouble();
                }
            } else if(i == 11) {
                if(e.getSource() == gridButtons[i]){
                    turnToNeg();
                }
            } else if (i == 12) {
                if(e.getSource() == gridButtons[i]){
                    clear();
                }
            } else if (i == 13) {
                if (e.getSource() == gridButtons[i]) {
                    del();
                }
            } else if (i == 14) {
                if(e.getSource() == gridButtons[i]){
                    add(integer, decimal);
                }
            } else if (i == 15) {
                if(e.getSource() == gridButtons[i]){
                    subtract(integer, decimal);
                }
            } else if (i == 16) {
                if(e.getSource() == gridButtons[i]){
                    multiply(integer, decimal);
                }
            } else if (i == 17) {
                if(e.getSource() == gridButtons[i]){
                    divide(integer, decimal);
                }
            } else {
                if(e.getSource() == gridButtons[i]){
                    equal(integer, decimal);
                }

            }
        }


    }

    public static void main(String args[]){
        Calculator calc = new Calculator();
    }

}
