/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Oferta;
import Datos.Usuario;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class SubirArchivoEntradaBD {

    public void SubirJSon(InputStream input) {
        System.out.println("Entrando al back del Json");
        Date fechita = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String FECHAACTUAL = formatoFecha.format(fechita);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(input);

            JsonNode Categorias = rootNode.get("categorias");
            JsonNode Usuarios = rootNode.get("usuarios");
            JsonNode Empleadores = rootNode.get("empleadores");
            JsonNode Admin = rootNode.get("admin");
            JsonNode Ofertas = rootNode.get("ofertas");

            System.out.println("Categorias----------");
            //Categorias
            for (JsonNode categoria : Categorias) {
                String codigo = categoria.get("codigo").asText();
                String nombre = categoria.get("nombre").asText();
                String descripcion = categoria.get("descripcion").asText();

                System.out.println("Codigo:  " + codigo + "   Nombre: " + nombre + "  Descripcion:  " + descripcion);
                // ponga uste su insert aca, con metodos asi bien pajas
                System.out.println("\n");

                //SUbir Categoria BD
            }
 System.out.println("OFertas----------");
               
            for (JsonNode oferta : Ofertas) {
                String codigo = oferta.get("codigo").asText();
                String nombre = oferta.get("nombre").asText();
                String descripcion = oferta.get("descripcion").asText();
                String empresa = oferta.get("empresa").asText();
                String categoria = oferta.get("categoria").asText();
                String estado = oferta.get("estado").asText();
                String fechaPublicacion = oferta.get("fechaPublicacion").asText();
                String fechaLimite = oferta.get("fechaLimite").asText();
                double salario = oferta.get("salario").asDouble();
                String modalidad = oferta.get("modalidad").asText();
                String ubicacion = oferta.get("ubicacion").asText();
                String detalles = oferta.get("detalles").asText();
                String usuarioElegido = oferta.get("usuarioElegido").asText();

                Oferta ofertaBD = new Oferta(oferta.get("codigo").asText(),
                        oferta.get("empresa").asText(), null, oferta.get("nombre").asText(),
                        oferta.get("descripcion").asText(), oferta.get("categoria").asText(), null,
                        oferta.get("estado").asText(), oferta.get("fechaPublicacion").asText(),
                        oferta.get("fechaLimite").asText(), Float.parseFloat(oferta.get("salario").asText()),
                        oferta.get("modalidad").asText(), oferta.get("ubicacion").asText(),
                        oferta.get("detalles").asText(), oferta.get("usuarioElegido").asText(), null);

                System.out.println(ofertaBD.toString());

                JsonNode Solicitudes = oferta.get("solicitudes");

                if (Solicitudes != null) {

                    System.out.println("SOlicitudes");
                    for (JsonNode solicitudNode : Solicitudes) {
                        int codigoSolicitud = solicitudNode.get("codigo").asInt();
                        int usuarioSolicitud = solicitudNode.get("usuario").asInt();
                        String mensajeSolicitud = solicitudNode.get("mensaje").asText();

                        System.out.println("  - Código: " + codigoSolicitud + ", Usuario: " + usuarioSolicitud + ", Mensaje: " + mensajeSolicitud);
                    }
                }

                JsonNode entrevistasNode = oferta.get("entrevistas");
                if (entrevistasNode != null) {
                    System.out.println("Entrevistas:");
                    for (JsonNode entrevistaNode : entrevistasNode) {
                        int codigoEntrevista = entrevistaNode.get("codigo").asInt();
                        int usuarioEntrevista = entrevistaNode.get("usuario").asInt();
                        String fechaEntrevista = entrevistaNode.get("fecha").asText();
                        String horaEntrevista = entrevistaNode.get("hora").asText();
                        String ubicacionEntrevista = entrevistaNode.get("ubicacion").asText();
                        String estadoEntrevista = entrevistaNode.get("estado").asText();
                        String notasEntrevista = entrevistaNode.get("notas").asText();

                        System.out.println("  - Código: " + codigoEntrevista + ", Usuario: " + usuarioEntrevista
                                + ", Fecha: " + fechaEntrevista + ", Hora: " + horaEntrevista + ", Ubicación: " + ubicacionEntrevista
                                + ", Estado: " + estadoEntrevista + ", Notas: " + notasEntrevista);
                    }
                }

                System.out.println("Usuario elegido: " + usuarioElegido);

            }

            for (JsonNode empleadores : Empleadores) {
                int codigo = empleadores.get("codigo").asInt();
                String nombre = empleadores.get("nombre").asText();
                String direccion = empleadores.get("direccion").asText();
                String Usuario = empleadores.get("username").asText();
                String contra = empleadores.get("password").asText();
                String correo = empleadores.get("email").asText();
                String cui = empleadores.get("CUI").asText();
                String birth = empleadores.get("fechaFundacion").asText();

                String mision = empleadores.get("mision").asText();
                String vision = empleadores.get("vision").asText();

                Usuario empleador = new Usuario(empleadores.get("codigo").asInt(),
                        empleadores.get("nombre").asText(), empleadores.get("username").asText(),
                        empleadores.get("direccion").asText(), empleadores.get("email").asText(),
                        empleadores.get("password").asText(), empleadores.get("CUI").asText(),
                        empleadores.get("fechaFundacion").asText(), "Empleador", null);
                System.out.println("Empleadores----------");

                System.out.println(empleador.toString());
                System.out.println("mision: " + mision + "  vision: " + vision);

                JsonNode Telefonos = empleadores.get("telefonos");

                for (JsonNode telefonoNode : Telefonos) {
                    String telefono = telefonoNode.asText();
                    System.out.println("Teléfono: " + telefono);
                    //Subir a la base
                }

                JsonNode tarjeta = empleadores.get("tarjeta");
                if (tarjeta != null) {

                    String titular = tarjeta.get("Titular").asText();
                    String numero = tarjeta.get("numero").asText();
                    String codigoSeguridad = tarjeta.get("codigoSeguridad").asText();
                    System.out.println("Titular: " + titular + "  numero: " + numero + " codigoSeguidad: " + codigoSeguridad);

                }

            }

            for (JsonNode usuario : Usuarios) {
                int codigo = usuario.get("codigo").asInt();
                String nombre = usuario.get("nombre").asText();
                String direccion = usuario.get("direccion").asText();
                String Usuario = usuario.get("username").asText();
                String contra = usuario.get("password").asText();
                String correo = usuario.get("email").asText();
                String cui = usuario.get("CUI").asText();
                String birth = usuario.get("fechaNacimiento").asText();
                String cv = usuario.get("curriculum").asText();

                Usuario usuarioBD = new Usuario(usuario.get("codigo").asInt(),
                        usuario.get("nombre").asText(), usuario.get("username").asText(),
                        usuario.get("direccion").asText(), usuario.get("email").asText(),
                        usuario.get("password").asText(), usuario.get("CUI").asText(),
                        usuario.get("fechaNacimiento").asText(), "Usuario", null);

                System.out.println(usuarioBD.toString());

                JsonNode Telefonos = usuario.get("telefonos");

                for (JsonNode telefonoNode : Telefonos) {
                    String telefono = telefonoNode.asText();
                    System.out.println("Teléfono: " + telefono);
                    //Subir a la base
                }

                JsonNode CategoriasPreferencia = usuario.get("categorias");

                for (JsonNode preferencia : CategoriasPreferencia) {
                    String categoria = preferencia.asText();
                    System.out.println("Categoria: " + categoria);
                    //Subir a la base
                }

            }

            for (JsonNode admin : Admin) {

                int codigo = admin.get("codigo").asInt();
                String nombre = admin.get("nombre").asText();
                String direccion = admin.get("direccion").asText();
                String Usuario = admin.get("username").asText();
                String contra = admin.get("password").asText();
                String correo = admin.get("email").asText();
                String cui = admin.get("CUI").asText();
                String birth = admin.get("fechaNacimiento").asText();

                Usuario adminBD = new Usuario(admin.get("codigo").asInt(),
                        admin.get("nombre").asText(), admin.get("username").asText(),
                        admin.get("direccion").asText(), admin.get("email").asText(),
                        admin.get("password").asText(), admin.get("CUI").asText(),
                        admin.get("fechaNacimiento").asText(), "Admin", null);
    System.out.println("Admin----------");
        
                System.out.println(adminBD.toString());
//                insertar.UsuarioFinal(codigo,nombre,Usuario,contra,correo,saldo);

                // ponga uste su insert aca, con metodos asi bien pajas
            }
            //USuarioFinales

//            //SolicitudPrestamo
//            for (JsonNode soli : SolicitudesPrestamo) {
//                String codigo = soli.get("codigo").asText();
//                String biblioteca = soli.get("biblioteca").asText();
//                String recepcionista = soli.get("recepcionista").asText();
//                String usuario = soli.get("usuario").asText();
//                String isbn = soli.get("isbn").asText();
//                String fecha = soli.get("fecha").asText();
//                String estado = soli.get("estado").asText();
//                String tipoentrega = soli.get("tipoEntrega").asText();
//                String transportista = soli.get("transportista").asText();
//
//
//                if(tipoentrega.equals("RECEPCION")){
//                insertar.SolicitudPrestamo(codigo,biblioteca,recepcionista,usuario,isbn,fecha,estado,"Biblioteca");
//                }
//                if(tipoentrega.equals("DOMICILIO")){
//
//                    insertar.TransporteUsuarios(codigo,biblioteca,usuario,transportista,isbn,fecha,estado);
//
//                }
//
//
//                System.out.println("Código: " + codigo + ", Biblioteca: " + biblioteca + ", Recepcionista: " + recepcionista + ", Usuario: " + usuario + ", ISBN: " + isbn + ", Fecha: " + fecha + ", Tipo de Entrega: " + tipoentrega + ", Transportista: " + transportista);
//
//                System.out.println("\n\n");
//                // ponga uste su insert aca, con metodos asi bien pajas
//            }
//            //Prestamos
//            for (JsonNode soli : Prestamos) {
//                String codigo = soli.get("codigo").asText();
//                String biblioteca = soli.get("biblioteca").asText();
//                String recepcionista = soli.get("recepcionista").asText();
//                String usuario = soli.get("usuario").asText();
//                String isbn = soli.get("isbn").asText();
//                String fecha = soli.get("fecha").asText();
//                String estado = soli.get("estado").asText();
//                String multa = soli.get("multa").asText();
//
//                LocalDate fechaActual = LocalDate.parse(fecha);
//                LocalDate Fechanueva = fechaActual.plusDays(8);
//                String FechaEntrega = String.valueOf(Fechanueva);
//
//
//                insertar.Prestamos(codigo,biblioteca,recepcionista,usuario,isbn,fecha,FechaEntrega,estado,multa);
//
//                System.out.println("Código: " + codigo + ", Biblioteca: " + biblioteca + ", Recepcionista: " + recepcionista + ", Usuario: " + usuario + ", ISBN: " + isbn + ", Fecha: " + fecha + ", Estado: " + estado + ", Multa: " + multa);
//
//                System.out.println("\n\n");
//                // ponga uste su insert aca, con metodos asi bien pajas
//            }
//
//            //TransportesEntreBibliotecas
//            for (JsonNode dato : transportesBiblioteca) {
//                String codigo = dato.get("codigo").asText();
//                String bibliotecaOrigen = dato.get("bibliotecaOrigen").asText();
//                String bibliotecaDestino = dato.get("bibliotecaDestino").asText();
//                String recepcionista = dato.get("recepcionista").asText();
//                String transportista = dato.get("transportista").asText();
//                String fecha = dato.get("fecha").asText();
//                String estado = dato.get("estado").asText();
//
//
//                insertar.TransporteBibliotecas(codigo,bibliotecaOrigen,bibliotecaDestino,recepcionista,transportista,fecha,estado);
//
//                System.out.println("Código: " + codigo + ", Biblioteca Origen: " + bibliotecaOrigen + ", Biblioteca Destino: " + bibliotecaDestino + ", Recepcionista: " + recepcionista + ", Transportista: " + transportista + ", Fecha: " + fecha + ", Estado: " + estado);
//
//                JsonNode Detalle = dato.get("detalle");
//
//                // Detalles
//                for (JsonNode detalle : Detalle) {
//                    String isbn = detalle.get("isbn").asText();
//                    String unidades = detalle.get("unidades").asText();
//
//
//                    insertar.DetallesTeb(codigo,isbn,unidades);
//
//                    System.out.println("ISBN: " + isbn + ", unidades: " + unidades);
//
//
//                }
//                System.out.println("\n\n");
//
//
//                // ponga uste su insert aca, con metodos asi bien pajas
//            }
//
//
//
//
//            //Transportes a usuario
//            for (JsonNode soli : transportesUsuario) {
//                String codigo = soli.get("codigo").asText();
//                String biblioteca = soli.get("biblioteca").asText();
//                String usuario = soli.get("usuario").asText();
//                String isbn = soli.get("isbn").asText();
//                String fecha = soli.get("fecha").asText();
//                String estado = soli.get("estado").asText();
//                String transportista = soli.get("transportista").asText();
//
//                insertar.TransporteUsuarios(codigo,biblioteca,usuario,transportista,isbn,fecha,estado);
//                System.out.println("Código: " + codigo + ", Biblioteca: " + biblioteca + ", Usuario: " + usuario + ", ISBN: " + isbn + ", Fecha: " + fecha + ", Estado: " + estado + ", Transportista: " + transportista);
//
//                System.out.println("\n\n");
//                // ponga uste su insert aca, con metodos asi bien pajas
//            }
//
//            //Incidencias
//            for (JsonNode soli : Incidencias) {
//                String codigo = soli.get("codigo").asText();
//                String prestamo = soli.get("prestamo").asText();
//                String tipo = soli.get("tipo").asText();
//                String costo = soli.get("cantidadPagada").asText();
//
//
//                insertar.Incidencias(codigo,prestamo,tipo,costo,"Activo",tipo,FECHAACTUAL);
//
//                System.out.println("Código: " + codigo + ", Prestamo: " + prestamo + ", Tipo: " + tipo + ", Costo: " + costo);
//
//                System.out.println("\n\n");
//                // ponga uste su insert aca, con metodos asi bien pajas
//            }
//    //Solicitud de revocaion
//            for (JsonNode soli : SolicitudesRevocacion) {
//                String codigo = soli.get("codigo").asText();
//                String usuario = soli.get("usuario").asText();
//                String estado = soli.get("estado").asText();
//                String detalle = soli.get("detalle").asText();
//
//                insertar.SolicitudRevocacion(codigo,usuario,detalle,estado);
//                System.out.println("Código: " + codigo + ", Usuario: " + usuario + ", Estado: " + estado + ", Detalle: " + detalle);
//
//                System.out.println("\n\n");
//                // ponga uste su insert aca, con metodos asi bien pajas
//            }
//
        } catch (Exception e) {

        }

    }

}
