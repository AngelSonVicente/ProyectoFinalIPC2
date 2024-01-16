/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Estado;
import Datos.Categoria;
import Datos.CompletarPerfilUsuario;
import Datos.Empresa;
import Datos.Entrevista;
import Datos.Oferta;
import Datos.Solicitudes;
import Datos.Usuario;
import Service.CategoriaService;
import Service.CurriculumService;
import Service.EmpresaService;
import Service.EntrevistaService;
import Service.OfertaService;
import Service.PreferenciasService;
import Service.SolicitudesService;
import Service.TelefonosService;
import Service.UsuarioService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MSI
 */
public class SubirArchivoEntradaBD {

    private CategoriaService categoriservice = new CategoriaService();
    private UsuarioService usuarioService = new UsuarioService();
    private EmpresaService empresaService = new EmpresaService();
    private TelefonosService telefonoService = new TelefonosService();
    private PreferenciasService preferenciasService = new PreferenciasService();
    private EntrevistaService entrevistaService = new EntrevistaService();
    private OfertaService ofertaService = new OfertaService();
    private SolicitudesService solicitudService = new SolicitudesService();
    private CurriculumService cvService = new CurriculumService();

    public void SubirJSon(InputStream input, List<Part> pdfParts) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(input);

            JsonNode Categorias = rootNode.get("categorias");
            JsonNode Usuarios = rootNode.get("usuarios");
            JsonNode Empleadores = rootNode.get("empleadores");
            JsonNode Ofertas = rootNode.get("ofertas");

            JsonNode Administrador = rootNode.get("admin");
            if (Administrador != null) {
              
                Usuario adminBD = new Usuario(Administrador.get("codigo").asInt(),
                        Administrador.get("nombre").asText(), Administrador.get("username").asText(),
                        Administrador.get("direccion").asText(), Administrador.get("email").asText(),
                        Administrador.get("password").asText(), Administrador.get("CUI").asText(),
                        Administrador.get("fechaNacimiento").asText(), "Admin", null);
                System.out.println("Admin----------");
                System.out.println(adminBD.toString());

                usuarioService.CrearUsuario(adminBD);

            }

            System.out.println("Categorias----------");
            //Categorias
            for (JsonNode categoria : Categorias) {
          
                Categoria categoriaEntrada = new Categoria(categoria.get("codigo").asInt(), categoria.get("nombre").asText(), categoria.get("descripcion").asText());

                System.out.println(categoriaEntrada.toString());

                // ponga uste su insert aca, con metodos asi bien pajas
                System.out.println("\n");

                //Subir de una a la BD-------------------------------------------
                categoriservice.crearCategoria(categoriaEntrada);
            }

            for (JsonNode empleadores : Empleadores) {
                String codigo = empleadores.get("codigo").asText();
                String mision = empleadores.get("mision").asText();
                String vision = empleadores.get("vision").asText();

                //subir de una a la bD
                Usuario empleadorBD = new Usuario(empleadores.get("codigo").asInt(),
                        empleadores.get("nombre").asText(), empleadores.get("username").asText(),
                        empleadores.get("direccion").asText(), empleadores.get("email").asText(),
                        empleadores.get("password").asText(), empleadores.get("CUI").asText(),
                        empleadores.get("fechaFundacion").asText(), "Empleador", null);
                System.out.println("Empleadores----------");

                System.out.println(empleadorBD.toString());
                System.out.println("mision: " + mision + "  vision: " + vision);

                usuarioService.CrearUsuario(empleadorBD);

                JsonNode Telefonos = empleadores.get("telefonos");

                List<String> telefonos = new ArrayList<String>();

                for (JsonNode telefonoNode : Telefonos) {
                    String telefono = telefonoNode.asText();
                    System.out.println("Teléfono: " + telefono);
                    telefonos.add(telefonoNode.asText());
                    //Subir a la base
                }

                //subir telefonos 
                telefonoService.ingresarTelefonos(codigo, telefonos);

                JsonNode tarjeta = empleadores.get("tarjeta");
                if (tarjeta != null) {

                    String titular = tarjeta.get("Titular").asText();
                    String numero = tarjeta.get("numero").asText();
                    String codigoSeguridad = tarjeta.get("codigoSeguridad").asText();
                    System.out.println("Titular: " + titular + "  numero: " + numero + " codigoSeguidad: " + codigoSeguridad);

                    //subir a la BD
                    Empresa empresa = new Empresa(null, codigo, null, mision, vision, tarjeta.get("Titular").asText(),
                            tarjeta.get("numero").asText(), tarjeta.get("codigoSeguridad").asText(), null);

                    empresaService.crearEmpresa(empresa);

                }

            }

            for (JsonNode usuario : Usuarios) {
                String codigo = usuario.get("codigo").asText();
          
                String cv = usuario.get("curriculum").asText();

                Usuario usuarioBD = new Usuario(usuario.get("codigo").asInt(),
                        usuario.get("nombre").asText(), usuario.get("username").asText(),
                        usuario.get("direccion").asText(), usuario.get("email").asText(),
                        usuario.get("password").asText(), usuario.get("CUI").asText(),
                        usuario.get("fechaNacimiento").asText(), "Usuario", null);

                //subir usuario
                usuarioService.CrearUsuario(usuarioBD);
                System.out.println(usuarioBD.toString());

                Part curriculumPART = null;
                //buscamos el pdf del cv
                for (Part part : pdfParts) {

                    String PDFname = part.getSubmittedFileName().substring(0, part.getSubmittedFileName().length() - 8);

                    System.out.println("CV JSon: " + cv);

                    //cuando encuentra el pdf lo mete a un PARt para ser enviado a la BD
                    if (cv.equals(part.getSubmittedFileName().substring(0, part.getSubmittedFileName().length() - 4))) {

                        curriculumPART = part;

                    }

                }

                //   se sube el Curriculum a la base 
                cvService.IngresarCV(String.valueOf(codigo), curriculumPART);

                JsonNode Telefonos = usuario.get("telefonos");

                List<String> telefonos = new ArrayList<String>();
                for (JsonNode telefonoNode : Telefonos) {

                    String telefono = telefonoNode.asText();
                    System.out.println("Teléfono: " + telefono);

                    telefonos.add(telefonoNode.asText());

                    //Subir a la base
                }

                List<String> categoriasPreferencia = new ArrayList<String>();

                JsonNode CategoriasPreferencia = usuario.get("categorias");

                for (JsonNode preferencia : CategoriasPreferencia) {
                    String categoria = preferencia.asText();
                    System.out.println("Categoria: " + categoria);
                    categoriasPreferencia.add(preferencia.asText());
                    //Subir a la base
                }

                CompletarPerfilUsuario perfilUsuario = new CompletarPerfilUsuario(usuario.get("codigo").asText(), telefonos, categoriasPreferencia);

                //subir telefonos y categiras preferencias
                telefonoService.ingresarTelefonos(perfilUsuario.getCodigoUsuario(), perfilUsuario.getTelefonos());
                preferenciasService.ingresarPreferencias(perfilUsuario);

            }

            System.out.println("OFertas----------");
            //verificar estados 
            for (JsonNode oferta : Ofertas) {
                String codigo = oferta.get("codigo").asText();
                String estado = oferta.get("estado").asText();
                String usuarioElegido = oferta.get("usuarioElegido").asText();

                System.out.println("Codigo Oferta: " + codigo);
                if (usuarioElegido.equals("null")) {
                    usuarioElegido = null;
                }

                String estadoBD = "";
                if (estado.equals("ACTIVA")) {
                    estadoBD = Estado.Activo.name();
                } else {
                    estadoBD = estado;
                }

                Oferta ofertaBD = new Oferta(oferta.get("codigo").asText(),
                        oferta.get("empresa").asText(), null, oferta.get("nombre").asText(),
                        oferta.get("descripcion").asText(), oferta.get("categoria").asText(), null,
                        estadoBD, oferta.get("fechaPublicacion").asText(),
                        oferta.get("fechaLimite").asText(), Float.parseFloat(oferta.get("salario").asText()),
                        oferta.get("modalidad").asText(), oferta.get("ubicacion").asText(),
                        oferta.get("detalles").asText(), usuarioElegido, null);

                //subir Oferta BD
                  ofertaService.crearOferta(ofertaBD);
                System.out.println(ofertaBD.toString());

                JsonNode Solicitudes = oferta.get("solicitudes");

                List<Solicitudes> solciitudesBD = new ArrayList<Solicitudes>();
                if (Solicitudes != null) {

                    System.out.println("SOlicitudes");
                    for (JsonNode solicitudNode : Solicitudes) {
                        int codigoSolicitud = solicitudNode.get("codigo").asInt();
                        String usuarioSolicitud = solicitudNode.get("usuario").asText();
                        String mensajeSolicitud = solicitudNode.get("mensaje").asText();

                        String EstadoSolicitud = Estado.Activo.name();
                        //si hay usuario elegido 
                        if (usuarioElegido != null) {
                            //buscamos al usuario elegido para cambiar el estado de su solicitud como elegido
                            if (usuarioElegido.equals(usuarioSolicitud)) {
                                EstadoSolicitud = Estado.Elegido.name();

                            }

                        }
                        //Primero se envia como Activo, es como so el usario crea su solicitud a la oferta

                        Solicitudes solicitud = new Solicitudes(solicitudNode.get("codigo").asText(),
                                codigo, null, solicitudNode.get("usuario").asText(),
                                null, solicitudNode.get("mensaje").asText(), EstadoSolicitud);

                        solciitudesBD.add(solicitud);

                        //subir a la BD
                          solicitudService.crearSOlicitudBD(solicitud);
                        System.out.println("  - Código: " + codigoSolicitud + ", Usuario: " + usuarioSolicitud + ", Mensaje: " + mensajeSolicitud);
                    }
                }

                JsonNode entrevistasNode = oferta.get("entrevistas");

                if (entrevistasNode != null) {

                    //CAmbiar estado oferta Entrevista, ya que si ya hay entrevistas, ya pasó la fase de selccion
                    System.out.println("Entrevistas:");
                    for (JsonNode entrevistaNode : entrevistasNode) {

                        String codigoSolicitud = null;

                        //buscamos el codigo de la solicitud, ya que se requiere para poder actualizar su estado al momento de crear la entrevista
                        for (Solicitudes solicitud : solciitudesBD) {
                            if (solicitud.getCodigoUsuario().equals(entrevistaNode.get("usuario").asText())) {
                                codigoSolicitud = solicitud.getCodigo();
                            }
                            

                        }

                        int codigoEntrevista = entrevistaNode.get("codigo").asInt();
                        int usuarioEntrevista = entrevistaNode.get("usuario").asInt();
                        String fechaEntrevista = entrevistaNode.get("fecha").asText();
                        String horaEntrevista = entrevistaNode.get("hora").asText();
                        String ubicacionEntrevista = entrevistaNode.get("ubicacion").asText();
                        String estadoEntrevista = entrevistaNode.get("estado").asText();
                        String notasEntrevista = entrevistaNode.get("notas").asText();

                        String estadoEntrevistaBD = "";
                        if (estadoEntrevista.equals("FINALIZADA")) {
                            estadoEntrevistaBD = "Finalizado";
                        } else {
                            estadoEntrevistaBD = estadoEntrevista;
                        }

                        System.out.println("  - Código: " + codigoEntrevista + ", Usuario: " + usuarioEntrevista
                                + ", Fecha: " + fechaEntrevista + ", Hora: " + horaEntrevista + ", Ubicación: " + ubicacionEntrevista
                                + ", Estado: " + estadoEntrevista + ", Notas: " + notasEntrevista);

                        Entrevista entrevistaBD = new Entrevista(null, codigo, null, entrevistaNode.get("usuario").asText(),
                                null, entrevistaNode.get("fecha").asText(), entrevistaNode.get("hora").asText(), entrevistaNode.get("ubicacion").asText(),
                                estadoEntrevistaBD, entrevistaNode.get("notas").asText(), codigoSolicitud);

                        //Agendar Entrevista (EntrevistService)(no hay entrevista sin SOlicitud)
                        //Entrevista Service se encarga de crear la entrevista y actualizar el estado de solicitud
                        //Subir BD
                           entrevistaService.agendarEntrevista(entrevistaBD);
                    }
                }

                System.out.println("Usuario elegido: " + usuarioElegido);

            }

        } catch (Exception e) {
            System.out.println("eorrr______________________"+e);

        }

    }

}
