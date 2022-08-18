import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        App app = new App();
        app.start();
    }

    public void start() {

        String rutSinDV;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su rut sin dígito verificador: ");
        rutSinDV = scanner.nextLine();

        while (esRutSinDVValido(rutSinDV) == false) {
            System.out.print("Por favor, inténtelo de nuevo: ");
            rutSinDV = scanner.nextLine();
        }

        System.out.println("El dígito verificador es: " + calcularDV(rutSinDV));
    }

    private String calcularDV(String rutSinDV) {

        String rutSinDVInvertido = invertirString(rutSinDV);
        int DVNumerico = procesarRut(rutSinDVInvertido);
        String DV = convertirDVNumericoADVLiteral(DVNumerico);

        return DV;
    }

    private boolean esRutSinDVValido(String r) {
        try {
            int i = Integer.valueOf(r);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    private String invertirString(String s) {
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            st.append(s.charAt(s.length() - i - 1));
        }

        return st.toString();
    }

    private int procesarRut(String s) {

        int suma = 0;
        int index = 0;

        while (index < s.length()) {
            String c = s.split("")[index];

            int digito = Integer.valueOf(c);
            int i = (index % 6) + 2;

            suma += i * digito;

            index++;
        }

        double a = suma / 11;
        a = Math.floor(a);
        a = a * 11;
        a = Math.abs(suma - a);
        a = 11 - a;

        return ((int) a);
    }

    private String convertirDVNumericoADVLiteral(int s) {
        switch (s) {
            case 10:
                return "k";
            case 11:
                return "0";
            default:
                return String.valueOf(s);
        }
    }
}