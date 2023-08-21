/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package parzibyte.imprimirpdfjava;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 * @author parzibyte
 */
public class ImprimirPDFJava {

    static String impresora = "Microsoft Print to PDF";
    static String serial = "";
    static String grados = "0";//Debe ser múltiplo de 90, pero indicados como cadena

    static String direccionServidor = "http://localhost:8080";

public static void ejemploUrl() {
    Map<String, String> parametros = Map.of(
            "urlPdf", "https://parzibyte.github.io/plugin-silent-pdf-print-examples/delgado.pdf",
            "impresora", impresora,
            "serial", serial,
            "grados", grados
    );
    StringBuilder parametrosURL = new StringBuilder();
    parametros.forEach((clave, valor) -> {
        parametrosURL.append(URLEncoder.encode(clave, StandardCharsets.UTF_8))
                .append("=")
                .append(URLEncoder.encode(valor, StandardCharsets.UTF_8))
                .append("&");
    });
    String urlCompleta = direccionServidor + "/url?" + parametrosURL.toString();

    var request = HttpRequest.newBuilder().uri(URI.create(urlCompleta)).GET().build();
    var client = HttpClient.newHttpClient();
    try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        String responseBody = response.body();
        System.out.println("Código de estado: " + statusCode);
        System.out.println("Respuesta: " + responseBody);
    } catch (Exception e) {
        System.out.println("Error haciendo petición. Asegúrese de que el plugin se está ejecutando:");
        e.printStackTrace();
    }
}

    public static void ejemploLocal() {
        Map<String, String> parametros = Map.of(
                "nombrePdf", "C:\\Users\\parzibyte\\Desktop\\delgado.pdf",
                "impresora", impresora,
                "serial", serial,
                "grados", grados
        );
        StringBuilder parametrosURL = new StringBuilder();
        parametros.forEach((clave, valor) -> {
            parametrosURL.append(URLEncoder.encode(clave, StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(valor, StandardCharsets.UTF_8))
                    .append("&");
        });
        String urlCompleta = direccionServidor + "/?" + parametrosURL.toString();

        var request = HttpRequest.newBuilder().uri(URI.create(urlCompleta)).GET().build();
        var client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();
            System.out.println("Código de estado: " + statusCode);
            System.out.println("Respuesta: " + responseBody);
        } catch (Exception e) {
            System.out.println("Error haciendo petición. Asegúrese de que el plugin se está ejecutando:");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ejemploUrl();
        //ejemploLocal();
    }
}
