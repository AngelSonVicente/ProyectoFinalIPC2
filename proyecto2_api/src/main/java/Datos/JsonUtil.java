/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class JsonUtil<T> {

    private Gson gson = new Gson();

    public void EnviarJson(HttpServletResponse response, T object) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String res = gson.toJson(object);
        var out = response.getWriter();
        out.print(res);
    }

    public void EnviarListaJson(HttpServletResponse response, List<T> list) throws IOException {
        new JsonUtil<List<T>>().EnviarJson(response, list);
    }

    public T JsonAObjeto(HttpServletRequest request, Class<T> classT) throws IOException {
        var buffer = new StringBuilder();
        var reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String body = buffer.toString();
        return gson.fromJson(body, classT);
    }

    public String getBody(HttpServletRequest request) throws IOException {
        var buffer = new StringBuilder();
        var reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    
    }
    public T JsonStringAObjeto(String body, Class<T> classT) throws IOException {
        return gson.fromJson(body, classT);
    }

}
