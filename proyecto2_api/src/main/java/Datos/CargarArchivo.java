package Datos;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CargarArchivo {
    public void Carga(){
        Date fechita = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String FECHAACTUAL = formatoFecha.format(fechita);



//        Subir subir = new Subir();

        SubirArchivoEntrada insertar = new SubirArchivoEntrada();

        String filePath = "C:/IPC2/Proyecto1ipc22023/archivo.json"; // Ruta al archivo JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            JsonNode Libros = rootNode.get("libros");
            JsonNode Categorias = rootNode.get("categorias");
            JsonNode Bibliotecas = rootNode.get("bibliotecas");
            JsonNode Recepcionistas = rootNode.get("usuariosRecepcion");
            JsonNode Transportistas = rootNode.get("usuariosTransporte");
            JsonNode Usuarios = rootNode.get("usuarios");
            JsonNode SolicitudesPrestamo = rootNode.get("solicitudesPrestamo");
            JsonNode Prestamos = rootNode.get("prestamos");
            JsonNode transportesBiblioteca = rootNode.get("transportesB");
            JsonNode transportesUsuario = rootNode.get("transportesU");
            JsonNode Incidencias = rootNode.get("incidencias");
            JsonNode SolicitudesRevocacion = rootNode.get("solicitudesRevocacion");


            //Categorias
            for (JsonNode categoria : Categorias) {
                String codigo = categoria.get("codigo").asText();
                String nombre = categoria.get("nombre").asText();
                String descripcion = categoria.get("descripcion").asText();

                System.out.println("Codigo:  " + codigo + "   Nombre: " + nombre + "  Descripcion:  " + descripcion);
                // ponga uste su insert aca, con metodos asi bien pajas
                System.out.println("\n\n");

                insertar.Categoria(codigo,nombre,descripcion);

            }

            //Libros
            for (JsonNode libro : Libros) {
                String isbn = libro.get("isbn").asText();
                String nombre = libro.get("nombre").asText();
                String costo = libro.get("costo").asText();
                String categoria = libro.get("categoria").asText();
                String autor = libro.get("autor").asText();

                System.out.println("isbn:  "+ isbn+"   Nombre: " +nombre+"  Costo:  " + costo+"  Categoria:  "+ categoria +"  autor:  "+autor );
               System.out.println("\n\n");
                // ponga uste su insert aca, con metodos asi bien pajas

                insertar.Libros(isbn,nombre,costo,categoria,autor);


            }



                //BIbliotecas
                for (JsonNode biblioteca : Bibliotecas) {
                    String codigo = biblioteca.get("codigo").asText();
                    String nombreBiblioteca = biblioteca.get("nombre").asText();
                    String Direccion = biblioteca.get("direccion").asText();

                    insertar.Bibliotecas(codigo,nombreBiblioteca,Direccion);

                    System.out.println("COdigo:  " +codigo+"  Nombre:  "+ nombreBiblioteca + " Direccion:  "+Direccion );
                        // Accede a la lista de libros en esta biblioteca
                        JsonNode libros = biblioteca.get("libros");

                        // Itera sobre los libros en esta biblioteca
                        for (JsonNode libro : libros) {
                            int isbn = libro.get("isbn").asInt();
                            int existencias = libro.get("existencias").asInt();

                            insertar.CatalogoBibliotecas(codigo,isbn,existencias);
                            // Realiza acciones con los datos de los libros de esta biblioteca
                            System.out.println("ISBN: " + isbn + ", Existencias: " + existencias);
                        }
                    System.out.println("\n\n");

                }


            //Recepcionistas
            for (JsonNode recepcion : Recepcionistas) {
                String codigo = recepcion.get("codigo").asText();
                String nombre = recepcion.get("nombre").asText();
                String usuario = recepcion.get("username").asText();
                String contra = recepcion.get("password").asText();
                String correo = recepcion.get("email").asText();
                String biblioteca = recepcion.get("biblioteca").asText();

                insertar.Recepcionista(codigo,nombre,usuario,contra,correo,biblioteca,"Activo");

                System.out.println("Codigo:  " + codigo + "   Nombre: " + nombre + "  usuario:  "+ usuario+"  contraseña:  "+contra+" correo:  "+correo + "Biblioteca:  "+ biblioteca );
                // ponga uste su insert aca, con metodos asi bien pajas
                System.out.println("\n\n");
            }

            //TRansporte
            for (JsonNode transporte : Transportistas) {
                String codigo = transporte.get("codigo").asText();
                String nombre = transporte.get("nombre").asText();
                String usuario = transporte.get("username").asText();
                String contra = transporte.get("password").asText();
                String correo = transporte.get("email").asText();

                insertar.Transportista(codigo,nombre,usuario,contra,correo);

                System.out.println("Codigo:  " + codigo + "   Nombre: " + nombre + "  usuario:  "+ usuario+"  contraseña:  "+contra+" correo:  "+correo);
                // ponga uste su insert aca, con metodos asi bien pajas
                System.out.println("\n\n");
            }

            //USuarioFinales
            for (JsonNode usuario : Usuarios) {
                String codigo = usuario.get("codigo").asText();
                String nombre = usuario.get("nombre").asText();
                String Usuario = usuario.get("username").asText();
                String contra = usuario.get("password").asText();
                String correo = usuario.get("email").asText();
                String saldo = usuario.get("saldoInicial").asText();

                insertar.UsuarioFinal(codigo,nombre,Usuario,contra,correo,saldo);

                System.out.println("Codigo:  " + codigo + "   Nombre: " + nombre + "  usuario:  "+ Usuario+"  contraseña:  "+contra+" correo:  "+correo +" saldo:  "+ saldo);
                System.out.println("\n\n");
                // ponga uste su insert aca, con metodos asi bien pajas
            }

            //SolicitudPrestamo
            for (JsonNode soli : SolicitudesPrestamo) {
                String codigo = soli.get("codigo").asText();
                String biblioteca = soli.get("biblioteca").asText();
                String recepcionista = soli.get("recepcionista").asText();
                String usuario = soli.get("usuario").asText();
                String isbn = soli.get("isbn").asText();
                String fecha = soli.get("fecha").asText();
                String estado = soli.get("estado").asText();
                String tipoentrega = soli.get("tipoEntrega").asText();
                String transportista = soli.get("transportista").asText();


                if(tipoentrega.equals("RECEPCION")){
                insertar.SolicitudPrestamo(codigo,biblioteca,recepcionista,usuario,isbn,fecha,estado,"Biblioteca");
                }
                if(tipoentrega.equals("DOMICILIO")){

                    insertar.TransporteUsuarios(codigo,biblioteca,usuario,transportista,isbn,fecha,estado);

                }


                System.out.println("Código: " + codigo + ", Biblioteca: " + biblioteca + ", Recepcionista: " + recepcionista + ", Usuario: " + usuario + ", ISBN: " + isbn + ", Fecha: " + fecha + ", Tipo de Entrega: " + tipoentrega + ", Transportista: " + transportista);

                System.out.println("\n\n");
                // ponga uste su insert aca, con metodos asi bien pajas
            }
            //Prestamos
            for (JsonNode soli : Prestamos) {
                String codigo = soli.get("codigo").asText();
                String biblioteca = soli.get("biblioteca").asText();
                String recepcionista = soli.get("recepcionista").asText();
                String usuario = soli.get("usuario").asText();
                String isbn = soli.get("isbn").asText();
                String fecha = soli.get("fecha").asText();
                String estado = soli.get("estado").asText();
                String multa = soli.get("multa").asText();

                LocalDate fechaActual = LocalDate.parse(fecha);
                LocalDate Fechanueva = fechaActual.plusDays(8);
                String FechaEntrega = String.valueOf(Fechanueva);


                insertar.Prestamos(codigo,biblioteca,recepcionista,usuario,isbn,fecha,FechaEntrega,estado,multa);

                System.out.println("Código: " + codigo + ", Biblioteca: " + biblioteca + ", Recepcionista: " + recepcionista + ", Usuario: " + usuario + ", ISBN: " + isbn + ", Fecha: " + fecha + ", Estado: " + estado + ", Multa: " + multa);

                System.out.println("\n\n");
                // ponga uste su insert aca, con metodos asi bien pajas
            }

            //TransportesEntreBibliotecas
            for (JsonNode dato : transportesBiblioteca) {
                String codigo = dato.get("codigo").asText();
                String bibliotecaOrigen = dato.get("bibliotecaOrigen").asText();
                String bibliotecaDestino = dato.get("bibliotecaDestino").asText();
                String recepcionista = dato.get("recepcionista").asText();
                String transportista = dato.get("transportista").asText();
                String fecha = dato.get("fecha").asText();
                String estado = dato.get("estado").asText();


                insertar.TransporteBibliotecas(codigo,bibliotecaOrigen,bibliotecaDestino,recepcionista,transportista,fecha,estado);

                System.out.println("Código: " + codigo + ", Biblioteca Origen: " + bibliotecaOrigen + ", Biblioteca Destino: " + bibliotecaDestino + ", Recepcionista: " + recepcionista + ", Transportista: " + transportista + ", Fecha: " + fecha + ", Estado: " + estado);

                JsonNode Detalle = dato.get("detalle");

                // Detalles
                for (JsonNode detalle : Detalle) {
                    String isbn = detalle.get("isbn").asText();
                    String unidades = detalle.get("unidades").asText();


                    insertar.DetallesTeb(codigo,isbn,unidades);

                    System.out.println("ISBN: " + isbn + ", unidades: " + unidades);


                }
                System.out.println("\n\n");


                // ponga uste su insert aca, con metodos asi bien pajas
            }




            //Transportes a usuario
            for (JsonNode soli : transportesUsuario) {
                String codigo = soli.get("codigo").asText();
                String biblioteca = soli.get("biblioteca").asText();
                String usuario = soli.get("usuario").asText();
                String isbn = soli.get("isbn").asText();
                String fecha = soli.get("fecha").asText();
                String estado = soli.get("estado").asText();
                String transportista = soli.get("transportista").asText();

                insertar.TransporteUsuarios(codigo,biblioteca,usuario,transportista,isbn,fecha,estado);
                System.out.println("Código: " + codigo + ", Biblioteca: " + biblioteca + ", Usuario: " + usuario + ", ISBN: " + isbn + ", Fecha: " + fecha + ", Estado: " + estado + ", Transportista: " + transportista);

                System.out.println("\n\n");
                // ponga uste su insert aca, con metodos asi bien pajas
            }

            //Incidencias
            for (JsonNode soli : Incidencias) {
                String codigo = soli.get("codigo").asText();
                String prestamo = soli.get("prestamo").asText();
                String tipo = soli.get("tipo").asText();
                String costo = soli.get("cantidadPagada").asText();


                insertar.Incidencias(codigo,prestamo,tipo,costo,"Activo",tipo,FECHAACTUAL);

                System.out.println("Código: " + codigo + ", Prestamo: " + prestamo + ", Tipo: " + tipo + ", Costo: " + costo);

                System.out.println("\n\n");
                // ponga uste su insert aca, con metodos asi bien pajas
            }
    //Solicitud de revocaion
            for (JsonNode soli : SolicitudesRevocacion) {
                String codigo = soli.get("codigo").asText();
                String usuario = soli.get("usuario").asText();
                String estado = soli.get("estado").asText();
                String detalle = soli.get("detalle").asText();

                insertar.SolicitudRevocacion(codigo,usuario,detalle,estado);
                System.out.println("Código: " + codigo + ", Usuario: " + usuario + ", Estado: " + estado + ", Detalle: " + detalle);

                System.out.println("\n\n");
                // ponga uste su insert aca, con metodos asi bien pajas
            }


        } catch (Exception e) {

        }

    }





}
