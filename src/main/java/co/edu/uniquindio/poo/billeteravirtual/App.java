package co.edu.uniquindio.poo.billeteravirtual;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoCuenta;
import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.facade.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.*;
import co.edu.uniquindio.poo.billeteravirtual.model.BilleteraVirtual;
import co.edu.uniquindio.poo.billeteravirtual.viewController.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static co.edu.uniquindio.poo.billeteravirtual.enums.RolAdministrador.SUPERADMIN;

public class App extends Application {
    public static Administrador administradorActual;
    public static Usuario usuarioActual;
    private static SistemaBilleteraFacade sistemaBilleteraFacade;
    private Stage primaryStage;
    @SuppressWarnings("exports")
    public static BilleteraVirtual billetera = BilleteraVirtual.getInstance();

    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        openLogin();
    }

    public static void main(String[] args) {
        launch();
    }

    public void openLogin() {
        inicializarData();
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(App.class.getResource("Login.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader1.load(); // Cambiado de VBox a AnchorPane

            LoginViewController loginViewController = loader1.getController();
            loginViewController.setApp(this);


            Scene scene1 = new Scene(rootLayout);
            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar Login.fxml");
            e.printStackTrace();
        }
    }

    public void openAdministrador() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Administrador.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            AdministradorViewController administradorViewController = loader2.getController();
            administradorViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openCategoria() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Categoria.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            CategoriaViewController categoriaViewController = loader2.getController();
            categoriaViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openCrearUsuario() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("CrearUsuario.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            CrearUsuarioViewController crearUsuarioViewController = loader2.getController();
            crearUsuarioViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openCrudAdministrador() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("CrudAdministrador.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            CrudAdministradorViewController crudAdministradorViewController = loader2.getController();
            crudAdministradorViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openCrudCuenta() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("CrudCuenta.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            CrudCuentaViewController crudCuentaViewController = loader2.getController();
            crudCuentaViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openCrudUsuario() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("CrudUsuario.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            CrudUsuarioViewController crudUsuarioViewController = loader2.getController();
            crudUsuarioViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openDeposito() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Deposito.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            DepositoViewController depositoViewController = loader2.getController();
            depositoViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openGraficas() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Graficas.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            GraficasViewController graficasViewController = loader2.getController();
            graficasViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openLoginAdministrador() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("LoginAdministrador.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            LoginAdministradorViewController loginAdministradorViewController = loader2.getController();
            loginAdministradorViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openLoginUsuario() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("LoginUsuario.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            LoginUsuarioViewController loginUsuarioViewController = loader2.getController();
            loginUsuarioViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openModificarPerfil() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("ModificarPerfil.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            ModificarPerfilViewController modificarPerfilViewController = loader2.getController();
            modificarPerfilViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openModificarPerfilAdministrador() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("ModificarPerfilAdministrador.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            ModificarPerfilAdministradorViewController modificarPerfilAdministradorViewController = loader2.getController();
            modificarPerfilAdministradorViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openPago() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Pago.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            PagoViewController pagoViewController = loader2.getController();
            pagoViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openPresupuesto() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Presupuesto.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            PresupuestoViewController presupuestoViewController = loader2.getController();
            presupuestoViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openRecarga() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Recarga.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            RecargaViewController recargaViewController = loader2.getController();
            recargaViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openRecuperacionContrasenia() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("RecuperacionContrasenia.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            RecuperacionContraseniaViewController recuperacionContraseniaViewController = loader2.getController();
            recuperacionContraseniaViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openRetiro() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Retiro.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            RetiroViewController retiroViewController = loader2.getController();
            retiroViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openTransaccion() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Transaccion.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            TransaccionViewController transaccionViewController = loader2.getController();
            transaccionViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openTransferencia() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Transferencia.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            TransferenciaViewController transferenciaViewController = loader2.getController();
            transferenciaViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void openUsuario() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Usuario.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            UsuarioViewController usuarioViewController = loader2.getController();
            usuarioViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void inicializarData() {
        BilleteraVirtual billetera1 = billetera;
        // Inicializar listas
        List<Usuario> listaUsuarios = new ArrayList<>();
        List<Administrador> listaAdministrador = new ArrayList<>();
        SistemaBilleteraFacade facade = new SistemaBilleteraFacade();
        List<Categoria> categorias = new ArrayList<>();
        List<Cuenta> cuentas = new ArrayList<>();
        List<Presupuesto> presupuestos = new ArrayList<>();
        List<Transaccion> transacciones = new ArrayList<>();

        // Categorías
        Categoria comida = new Categoria("Comida", "Gastos en alimentos y restaurantes");
        Categoria transporte = new Categoria("Transporte", "Gastos en transporte público y gasolina");
        Categoria entretenimiento = new Categoria("Entretenimiento", "Cine, música, salidas");
        categorias.add(comida);
        categorias.add(transporte);
        categorias.add(entretenimiento);

        // Presupuestos
        Presupuesto presupuestoJuan = new Presupuesto("Presupuesto comida", 200000.0, comida);
        Presupuesto presupuestoMaria = new Presupuesto("Presupuesto transporte", 150000.0, transporte);
        presupuestos.add(presupuestoJuan);
        presupuestos.add(presupuestoMaria);

        // Cuentas
        Cuenta cuentaJuan = new Cuenta("Bancolombia", TipoCuenta.AHORROS, CodigoGenerador.generarId(), CodigoGenerador.generarIdCuenta(), 500000.0);
        Cuenta cuentaMaria = new Cuenta("Davivienda", TipoCuenta.CORRIENTE, CodigoGenerador.generarId(), CodigoGenerador.generarIdCuenta(), 300000.0);
        cuentas.add(cuentaJuan);
        cuentas.add(cuentaMaria);

        // Usuarios
        usuarioActual = new Usuario(
                CodigoGenerador.generarId(),
                "Camilo Felipe Mendoza Del Castillo",
                "camilodelcastillo321@gmail.com",
                "+571234567890",
                "User12345",
                "Calle Falsa 123",
                new ArrayList<>(List.of(cuentaJuan)),
                new ArrayList<>(),
                new ArrayList<>(List.of(presupuestoJuan)),
                categorias
        );

        Usuario maria = new Usuario(
                CodigoGenerador.generarId(),
                "María López",
                "maria@gmail.com",
                "+573123087123",
                "Mari123456",
                "Calle Falsa 5",
                new ArrayList<>(List.of(cuentaMaria)),
                new ArrayList<>(),
                new ArrayList<>(List.of(presupuestoMaria)),
                categorias
        );

        // Transacciones (una vez que los usuarios existen)
        Transaccion transaccion1 = new Transaccion.Builder()
                .withIdTransaccion("TXN001")
                .withFechaHora(LocalDateTime.now().minusDays(1))
                .withMonto(15000.0)
                .withDescripcion("Almuerzo en restaurante")
                .withTipo(TipoTransaccion.PAGO)
                .withCategoria(comida)
                .withEmisor(usuarioActual)
                .withDestinatario(maria)
                .build();

        Transaccion transaccion2 = new Transaccion.Builder()
                .withIdTransaccion("TXN002")
                .withFechaHora(LocalDateTime.now())
                .withMonto(50000.0)
                .withDescripcion("Pago gasolina")
                .withTipo(TipoTransaccion.RETIRO)
                .withCategoria(transporte)
                .withEmisor(maria)
                .withDestinatario(usuarioActual)
                .build();

        transacciones.add(transaccion1);
        transacciones.add(transaccion2);

        // Asignar transacciones a usuarios
        usuarioActual.getTransacciones().add(transaccion1);
        usuarioActual.getTransacciones().add(transaccion2);
        maria.getTransacciones().add(transaccion1);
        maria.getTransacciones().add(transaccion2);

        // Administrador
        administradorActual = new Administrador(
                CodigoGenerador.generarId(),
                "Esteban Polanco Mendez",
                "estebanpolanco06@gmail.com",
                "+573166558604",
                "Esteban06yt",
                SUPERADMIN
        );

        // Sesiones y registros
        App.sistemaBilleteraFacade = facade;
        AdministradorSession.getInstancia().setAdministrador(administradorActual);
        UsuarioSession.getInstancia().setUsuario(usuarioActual);

        listaAdministrador.add(administradorActual);
        listaUsuarios.add(usuarioActual);
        listaUsuarios.add(maria);

        facade.registrarUsuario(usuarioActual);
        facade.registrarAdministrador(administradorActual);


    }

    
    public static Object getSesionActual() {
        if (administradorActual != null) {
            return administradorActual;
        } else {
            return usuarioActual;
        }
    }

    public static void cerrarSesion() {
        administradorActual = null;
        usuarioActual = null;
    }
}