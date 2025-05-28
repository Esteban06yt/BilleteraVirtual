package co.edu.uniquindio.poo.billeteravirtual.command;

import java.util.Stack;

public class GestorComandos {

    private Stack<Command> historialComandos = new Stack<>();

    public void ejecutarComando(Command comando) {
        comando.ejecutar();
        historialComandos.push(comando);
    }

    public void deshacerUltimo() {
        if (!historialComandos.isEmpty()) {
            Command comando = historialComandos.pop();
            comando.deshacer();
        }
    }
}

