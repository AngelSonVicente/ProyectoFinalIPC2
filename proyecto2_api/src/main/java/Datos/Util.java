package Datos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    
      public  boolean NoHaAlcanzadoFechaLimite(String fechalimite) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Obtener la fecha actual
            Date currentDate = new Date();

            // Analizar la fecha límite
            Date parsedEndDate = dateFormat.parse(fechalimite);

            // Comparar las fechas
            return !currentDate.after(parsedEndDate);
        } catch (ParseException e) {
            // Manejar excepción si hay un problema al analizar la fecha límite
            e.printStackTrace();
            return false;
        }
    }
    

    public static int[][] BuscarRepetidos(int[] ids, int[] cantidades) {
        int n = ids.length;
        int[][] libros = new int[n][2]; // Inicializar el arreglo de libros

        for (int i = 0; i < n; i++) {
            int id = ids[i];
            int cantidad = cantidades[i];

            // Buscar si el ID ya existe en el arreglo de libros
            int indexExistente = buscarID(libros, id);

            if (indexExistente != -1) {
                // Si el ID ya existe, sumar la cantidad a la cantidad existente
                libros[indexExistente][1] += cantidad;
            } else {
                // Si el ID no existe, agregarlo como un nuevo libro en el arreglo
                libros[i][0] = id;
                libros[i][1] = cantidad;
            }
        }

        // Filtrar el arreglo para eliminar filas con ceros
        int librosNoNulos = 0;
        for (int i = 0; i < libros.length; i++) {
            if (libros[i][0] != 0) {
                librosNoNulos++;
            }
        }

        int[][] resultado = new int[librosNoNulos][2];
        int index = 0;
        for (int i = 0; i < libros.length; i++) {
            if (libros[i][0] != 0) {
                resultado[index][0] = libros[i][0];
                resultado[index][1] = libros[i][1];
                index++;
            }
        }

        return resultado;
    }

    private static int buscarID(int[][] libros, int id) {
        for (int i = 0; i < libros.length; i++) {
            if (libros[i][0] == id) {
                return i;
            }
        }
        return -1;
    }



    public int[] ConvertirArregloSaI(String arreglo[]){

        int[] cantidadesInt = new int[arreglo.length];

        for (int i = 0; i < arreglo.length; i++) {
            try {
                cantidadesInt[i] = Integer.parseInt(arreglo[i]);
            } catch (NumberFormatException e) {


            }
        }


        return cantidadesInt;
    }


    public  boolean esNumero(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
        public static String Encriptar(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = password.getBytes();
            byte[] hashBytes = md.digest(passwordBytes);

            StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                hexHash.append(String.format("%02x", b));
            }

            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
        
        public boolean ValidarLenght(String palabra, int Lenght){
        if(palabra.length() <= Lenght){
        return true;
        }
        return false;
        }


}
