/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

/**
 *
 * @author Pichau
 */
public class Relatorio {
    private int totalVendas;
    private double totalPago;

    public Relatorio(int totalVendas, double totalPago) {
        this.totalVendas = totalVendas;
        this.totalPago = totalPago;
    }

    public int getTotalVendas() {
        return totalVendas;
    }

    public double getTotalPago() {
        return totalPago;
    }
}
