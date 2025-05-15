package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.model.Validar;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaldoServiceImplementa implements SaldoService {
    private final Map<String, Double> saldos;
    private final Map<String, Lock> locks;

    public SaldoServiceImplementa() {
        this.saldos = new HashMap<>();
        this.locks = new HashMap<>();
    }

    @Override
    public void actualizarSaldo(String idCuenta, Double monto) {
        Validar.queNoVacio(idCuenta, "El ID de cuenta no puede estar vacío");
        Validar.queNoNulo(monto, "El monto no puede ser nulo");

        Lock lock = locks.computeIfAbsent(idCuenta, k -> new ReentrantLock());
        lock.lock();
        try {
            double saldoActual = saldos.getOrDefault(idCuenta, 0.0);
            saldos.put(idCuenta, saldoActual + monto);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean haySaldoSuficiente(String idCuenta, Double monto) {
        Validar.queNoVacio(idCuenta, "El ID de cuenta no puede estar vacío");
        Validar.queNoNulo(monto, "El monto no puede ser nulo");
        Validar.que(monto >= 0, "El monto debe ser positivo");

        Lock lock = locks.computeIfAbsent(idCuenta, k -> new ReentrantLock());
        lock.lock();
        try {
            return saldos.getOrDefault(idCuenta, 0.0) >= monto;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double obtenerSaldo(String idCuenta) {
        Validar.queNoVacio(idCuenta, "El ID de cuenta no puede estar vacío");

        Lock lock = locks.computeIfAbsent(idCuenta, k -> new ReentrantLock());
        lock.lock();
        try {
            return saldos.getOrDefault(idCuenta, 0.0);
        } finally {
            lock.unlock();
        }
    }

    // Método adicional para inicializar cuentas
    public void inicializarCuenta(String idCuenta, double saldoInicial) {
        Validar.queNoVacio(idCuenta, "El ID de cuenta no puede estar vacío");
        Validar.que(saldoInicial >= 0, "El saldo inicial no puede ser negativo");

        Lock lock = locks.computeIfAbsent(idCuenta, k -> new ReentrantLock());
        lock.lock();
        try {
            if (!saldos.containsKey(idCuenta)) {
                saldos.put(idCuenta, saldoInicial);
            } else {
                throw new IllegalStateException("La cuenta ya existe");
            }
        } finally {
            lock.unlock();
        }
    }
}