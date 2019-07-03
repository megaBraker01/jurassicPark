package paraPuebeas;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Start {

    static String COMA = ";";
    static String CRLF = "\r\n";

    public static void main(String[] args) {
        List<Cosa> lista_de_cosas_para_escribir = CreaCosas();
        File file = new File("prueba.csv");


        try {
            EscribeCosasEnCSV(file, lista_de_cosas_para_escribir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Cosa> lista_de_cosas_leidas = null;
        try {
            lista_de_cosas_leidas = LeeCosasDeCSV(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Cosa cosa : lista_de_cosas_leidas) {
            System.out.println(cosa.toString());
        }

    }

    static List<Cosa> LeeCosasDeCSV(File archivo) throws IOException {


        FileReader fileReader = new FileReader(archivo);
        BufferedReader reader = new BufferedReader(fileReader);


        String line;
        String[] tokens;
        Cosa cosa;
        List<Cosa> cosas = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            tokens = line.split(COMA);
            cosa = new Cosa(
                    tokens[0],
                    Integer.parseInt(tokens[1]),
                    Double.parseDouble(tokens[2]),
                    LocalDate.parse(tokens[3]),
                    LocalTime.parse(tokens[4]),
                    Boolean.parseBoolean(tokens[5])
            );

            cosas.add(cosa);
        }

        reader.close();
        return cosas;
    }

    static void EscribeCosasEnCSV(File archivo, List<Cosa> cosas) throws IOException {
        OutputStream stream = new FileOutputStream(archivo);
        Writer writer = new OutputStreamWriter(stream);

        List<String> tokens;
        for (Cosa cosa : cosas) {
            tokens = new ArrayList<String>() {{
                add(cosa.getNombre());
                add(String.valueOf(cosa.getValor()));
                add(String.valueOf(cosa.getPrecio()));
                add(String.valueOf(cosa.getFecha()));
                add(String.valueOf(cosa.getHora()));
                add(String.valueOf(cosa.isActivo()));
            }};
            writer.write(String.join(COMA, tokens) + CRLF);
        }

        writer.flush();
        writer.close();
    }


    static List<Cosa> CreaCosas() {
        List<Cosa> cosas = new ArrayList<Cosa>() {
            {
                add(Cosa.of("Angel", 10, 2.5, LocalDate.now(), LocalTime.now(), true));
                add(Cosa.of("Rafael", 30, 7.08, LocalDate.now().plusDays(1), LocalTime.now().plusHours(2), true));
                add(Cosa.of("Ana", 25, 0.01, LocalDate.now().minusDays(1), LocalTime.now().minusHours(2), false));
            }
        };

        return cosas;
    }


}

