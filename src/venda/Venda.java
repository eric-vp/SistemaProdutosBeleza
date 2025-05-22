package venda;

import java.util.Date;

public class Venda {
    private int id;
    private String status;
    private int clienteId;
    private Date data;
    private Double totalPagamento;

    public Venda() {
    }

    public Venda(int id, String status, int clienteId, Date data, Double totalPagamento) {
        this.id = id;
        this.status = status;
        this.clienteId = clienteId;
        this.data = data;
        this.totalPagamento = totalPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getTotalPagamento() {
        return totalPagamento;
    }

    public void setTotalPagamento(Double totalPagamento) {
        this.totalPagamento = totalPagamento;
    }    
}
