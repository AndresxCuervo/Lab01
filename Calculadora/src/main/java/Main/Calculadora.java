//Andres Felipe Cuervo Palacios 7004284
//Kevin Vargas


package Main;

import java.awt.event.ActionEvent;
import javax.swing.*;
import op1.CalculadoraCientifica;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculadora extends JFrame {
      private final List<String> expresion;

    public Calculadora() {
        expresion = new ArrayList<>();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        pantalla = new JPanel();
        panel = new JTextField();
        cero = new JButton();
        uno = new JButton();
        dos = new JButton();
        tres = new JButton();
        cuatro = new JButton();
        cinco = new JButton();
        seis = new JButton();
        siete = new JButton();
        ocho = new JButton();
        nueve = new JButton();
        punto = new JButton();
        suma = new JButton();
        resta = new JButton();
        multiplicar = new JButton();
        dividir = new JButton();
        igual = new JButton();
        del = new JButton();
        ac = new JButton();
        parentesisizq = new JButton();
        parentesisder = new JButton();
        calculadorac = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Calculadora Básica");

        // Configuración del panel de la pantalla
        panel.setHorizontalAlignment(JTextField.RIGHT);
        panel.setEditable(true);

        // Configuración del panel principal
        pantalla.setLayout(new java.awt.GridLayout(5, 4, 10, 10));  // 5 filas, 4 columnas, espacio de 10px
        
        // Añadir botones al panel principal
        pantalla.add(ac);
        pantalla.add(parentesisizq);
        pantalla.add(parentesisder);
        pantalla.add(dividir);
        pantalla.add(siete);
        pantalla.add(ocho);
        pantalla.add(nueve);
        pantalla.add(multiplicar);
        pantalla.add(cuatro);
        pantalla.add(cinco);
        pantalla.add(seis);
        pantalla.add(resta);
        pantalla.add(uno);
        pantalla.add(dos);
        pantalla.add(tres);
        pantalla.add(suma);
        pantalla.add(cero);
        pantalla.add(punto);
        pantalla.add(del);
        pantalla.add(igual);
        

        // Establecer las etiquetas de los botones
        ac.setText("AC");
        del.setText("DEL");
        parentesisizq.setText("(");
        parentesisder.setText(")");
        dividir.setText("/");
        multiplicar.setText("*");
        resta.setText("-");
        suma.setText("+");
        igual.setText("=");
        punto.setText(".");
        cero.setText("0");
        uno.setText("1");
        dos.setText("2");
        tres.setText("3");
        cuatro.setText("4");
        cinco.setText("5");
        seis.setText("6");
        siete.setText("7");
        ocho.setText("8");
        nueve.setText("9");
        calculadorac.setText("calculadora científica");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel)
            .addComponent(pantalla, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(calculadorac, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)  // Añadir el botón calculadorac en la parte inferior
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pantalla, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calculadorac, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))  // Establecer la altura del botón calculadorac
        );
        
        ActionListener();
       
        pack();
    }


    private void ActionListener(){
// Métodos de eventos para los botones (por ahora vacíos)
    cero.addActionListener(evt -> botonPresionado("0"));
    uno.addActionListener(evt -> botonPresionado("1"));
    dos.addActionListener(evt -> botonPresionado("2"));
    tres.addActionListener(evt -> botonPresionado("3"));
    cuatro.addActionListener(evt -> botonPresionado("4"));
    cinco.addActionListener(evt -> botonPresionado("5"));
    seis.addActionListener(evt -> botonPresionado("6"));
    siete.addActionListener(evt -> botonPresionado("7"));
    ocho.addActionListener(evt -> botonPresionado("8"));
    nueve.addActionListener(evt -> botonPresionado("9"));
    punto.addActionListener(evt -> botonPresionado("."));
    suma.addActionListener(evt -> botonPresionado("+"));
    resta.addActionListener(evt -> botonPresionado("-"));
    multiplicar.addActionListener(evt -> botonPresionado("*"));
    dividir.addActionListener(evt -> botonPresionado("/"));
    parentesisizq.addActionListener(evt -> botonPresionado("("));
    parentesisder.addActionListener(evt -> botonPresionado(")"));
    del.addActionListener(evt -> borrarUltimoCaracter());
    ac.addActionListener(evt -> limpiarPanel());
    igual.addActionListener(evt -> {
    double resultado = evaluarExpresion();
    panel.setText(String.valueOf(resultado));
    expresion.clear();  // Limpia la lista para la próxima operación
    expresion.add(String.valueOf(resultado));  // Permite usar el resultado para la próxima operación
});
    calculadorac.addActionListener((ActionEvent e) -> {
        {calculadorac.setEnabled(!calculadorac.isEnabled());
        }
        new CalculadoraCientifica().setVisible(true);
    });
    
    }
    
    
    private boolean operador(String texto) {
    if (texto.isEmpty()) {
        return false;
    }
    char ultimoCaracter = texto.charAt(texto.length() - 1);
    return ultimoCaracter == '+' || ultimoCaracter == '-' || ultimoCaracter == '*' || ultimoCaracter == '/';
}
    private boolean hayParentesisIzq(String texto) {
    int countIzq = 0;
    int countDer = 0;

    // Recorre el texto y cuenta los paréntesis izquierdos y derechos
    for (char c : texto.toCharArray()) {
        if (c == '(') {
            countIzq++;
        } else if (c == ')') {
            countDer++;
        }
    }

    // Solo permite agregar ")" si hay más paréntesis izquierdos no cerrados
    return countIzq > countDer;
    
    
}

    // Método para borrar el último carácter
    private void borrarUltimoCaracter() {
        String textoActual = panel.getText();
        if (!textoActual.isEmpty()) {
            panel.setText(textoActual.substring(0, textoActual.length() - 1));
        }
    }

    // Método para limpiar el panel
    private void limpiarPanel() {
        panel.setText("");
    }
    
   private void botonPresionado(String texto) {
    String textoActual = panel.getText();

    if (textoActual.isEmpty() && operador(texto) && !texto.equals("-")) {
        return; // No hace nada
    }

    // Si se presiona ")" pero no hay un "(", no se hace nada
    if (texto.equals(")") && !hayParentesisIzq(textoActual)) {
        return; // No hace nada
    }

    if (operador(textoActual) && operador(texto)) {
        panel.setText(textoActual.substring(0, textoActual.length() - 1) + texto);
    } else {
        panel.setText(textoActual + texto);
    }

    // Agregar el texto a la lista de expresión
    expresion.add(texto);
}

private double evaluarExpresion() {
    Stack<Double> numeros = new Stack<>();
    Stack<Character> operadores = new Stack<>();

    // Convertir el contenido del panel a una lista de tokens
    String expr = panel.getText();
    String[] tokens = expr.split("(?<=[-+*/()])|(?=[-+*/()])");

    for (String token : tokens) {
        if (esNumero(token)) {
            numeros.push(Double.valueOf(token));
        } else if (esOperador(token.charAt(0))) {
            while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(token.charAt(0))) {
                double b = numeros.pop();
                double a = numeros.pop();
                char operador = operadores.pop();
                numeros.push(operar(a, b, operador));
            }
            operadores.push(token.charAt(0));
        } else if (token.equals("(")) {
            operadores.push('(');
        } else if (token.equals(")")) {
            while (operadores.peek() != '(') {
                double b = numeros.pop();
                double a = numeros.pop();
                char operador = operadores.pop();
                numeros.push(operar(a, b, operador));
            }
            operadores.pop();
        }
    }

    while (!operadores.isEmpty()) {
        double b = numeros.pop();
        double a = numeros.pop();
        char operador = operadores.pop();
        numeros.push(operar(a, b, operador));
    }

    return numeros.pop();
}


private boolean esNumero(String token) {
    try {
        Double.valueOf(token);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

private boolean esOperador(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
}

private int precedencia(char operador) {
          return switch (operador) {
              case '+', '-' -> 1;
              case '*', '/' -> 2;
              default -> -1;
          };
}

private double operar(double a, double b, char operador) {
    switch (operador) {
        case '+' -> {
            return a + b;
              }
        case '-' -> {
            return a - b;
              }
        case '*' -> {
            return a * b;
              }
        case '/' -> {
            return a / b;
              }
        default -> throw new IllegalArgumentException("Operador no válido: " + operador);
    }
}
    

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Calculadora().setVisible(true);
        });
    }

    // Variables
    private JButton ac;
    private JButton cero;
    private JButton cinco;
    private JButton cuatro;
    private JButton del;
    private JButton dividir;
    private JButton dos;
    private JButton igual;
    private JPanel pantalla;
    private JButton multiplicar;
    private JButton nueve;
    private JButton ocho;
    private JTextField panel;
    private JButton parentesisder;
    private JButton parentesisizq;
    private JButton punto;
    private JButton resta;
    private JButton seis;
    private JButton siete;
    private JButton suma;
    private JButton tres;
    private JButton uno;
    public static JButton calculadorac;
}
