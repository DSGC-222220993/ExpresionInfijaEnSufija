import java.util.Scanner;
import java.util.Stack;

public class InfixToSufix {

    public static String InfToSuf(String infix){
        //conversion de la expresion
        StringBuilder sufix=new StringBuilder();
        //construye la cadena de salida
        Stack<Character> stack=new Stack<>();
        // se establece la pila para almacenar los operadores

        for (int i = 0; i < infix.length(); i++) {
            char ch=infix.charAt(i);

            if (Character.isLetter(ch)){
                //verifica que el operando sea una letra, despues lo agrega a la salida
                sufix.append(ch);
            } else if (ch=='(') {
                //si  es un parentesis de apertura lo coloca en la pila
                stack.push(ch);
            } else if (ch==')') {
                //si es un parentesis de cierre extrae los operadores de adentro de la pila y los agrega a la salida
                while(!stack.isEmpty()&&stack.peek()!='('){
                    sufix.append(stack.pop());
                }
                if (!stack.isEmpty()&& stack.peek() == '('){
                    stack.pop();//saca el parentesis de apertura de la pila
                }
            }else{
                //en caso de que sea un operador, lo saca de la pila y lo agrega a la salida con un orden
                // de acuerdo a su precedencia
                while(!stack.isEmpty()&&priority(stack.peek())>=priority(ch)){
                    sufix.append(stack.pop());
                }
                stack.push(ch);
                //agrega el operador actual a la pila
            }
        }
        while (!stack.isEmpty()){
            //saca los operadores que restan en la pila y los manda a la salida
            sufix.append(stack.pop());
        }
        return sufix.toString();
        //devuelve la cadena sufija
    }
    public static int priority(char operator){
        //asigna prioridad a los operadores
        //se establece la funcion que devuelve la presedencia de un operador
        switch (operator){
            case'+':
            case'-':
                return 1;
            case'*':
            case'/':
                return 2;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {

        //se solicita la expresion infija por consola y despues se imprime el resultado con notacion sufija

        Scanner scanner=new Scanner(System.in);
        System.out.print("Utilizando unicamente letras como operadores, ingresa la expresion infija que deseas convertir: ");

        String infix=scanner.nextLine();
        scanner.close();

        String sufix=InfToSuf(infix);
        System.out.println("La expresion en notacion sufija luciria de la siguiente manera: "+sufix);
    }
}
