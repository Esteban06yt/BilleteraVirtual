package co.edu.uniquindio.poo.billeteravirtual.command;

import co.edu.uniquindio.poo.billeteravirtual.model.Cuenta;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;

import java.time.LocalDateTime;

public class RealizarTransaccionCommand implements Command {

    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private double monto;
    private Transaccion transaccionRealizada;
    private Usuario emisor;        // Usuario que envía
    private Usuario destinatario;  // Usuario que recibe

    public RealizarTransaccionCommand(Cuenta origen, Usuario emisor, Cuenta destino, Usuario destinatario, double monto) {
        this.cuentaOrigen = origen;
        this.emisor = emisor;
        this.cuentaDestino = destino;
        this.destinatario = destinatario;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        if (cuentaOrigen.getMonto() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        // Realizar la transferencia usando método de Cuenta
        cuentaOrigen.transferir(monto, cuentaDestino);

        // Construir la transacción usando el Builder
        transaccionRealizada = new Transaccion.Builder()
                .withIdTransaccion(generarId()) // Método para generar ID único (debes implementarlo o usar tu generador)
                .withFechaHora(LocalDateTime.now())
                .withMonto(monto)
                .withDescripcion("Transferencia de dinero")
                .withTipo(TipoTransaccion.TRANSFERENCIA) // Usa el enum correcto que tengas
                .withEmisor(emisor)
                .withDestinatario(destinatario)
                .build();

        // Aquí podrías almacenar la transacción en una lista o base de datos, si tienes esa lógica
    }

    @Override
    public void deshacer() {
        // Revertir la transferencia
        cuentaDestino.setMonto(cuentaDestino.getMonto() - monto);
        cuentaOrigen.setMonto(cuentaOrigen.getMonto() + monto);

        // Eliminar la transacción si fue guardada (depende de tu implementación)
        transaccionRealizada = null;
    }

    // Ejemplo sencillo de generación de ID, ajusta según tu clase CodigoGenerador
    private String generarId() {
        return "TXN-" + System.currentTimeMillis();
    }
}
