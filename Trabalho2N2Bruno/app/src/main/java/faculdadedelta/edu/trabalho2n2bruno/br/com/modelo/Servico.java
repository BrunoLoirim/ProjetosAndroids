package faculdadedelta.edu.trabalho2n2bruno.br.com.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Servico implements Serializable {

    private Long id;
    private String cliente;
    private String servico;
    private int qtdServico;
    private double vlrServico;
    private Date dtExecucao;

    public Servico() {}

    public Servico(Long id, String cliente, String servico, int qtdServico, double vlrServico, Date dtExecucao) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.qtdServico = qtdServico;
        this.vlrServico = vlrServico;
        this.dtExecucao = dtExecucao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public int getQtdServico() {
        return qtdServico;
    }

    public void setQtdServico(int qtdServico) {
        this.qtdServico = qtdServico;
    }

    public double getVlrServico() {
        return vlrServico;
    }

    public void setVlrServico(double vlrServico) {
        this.vlrServico = vlrServico;
    }

    public Date getDtExecucao() {
        return dtExecucao;
    }

    public void setDtExecucao(Date dtExecucao) {
        this.dtExecucao = dtExecucao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(id, servico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void calculaDesconto(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dtInicio = null;
        Date dtDesconto = null;
        Date dtAtual = new Date();
        String mensagem = "";
        double vlrDesconto = 0;
        try {
            dtInicio = sdf.parse("01/01/1900");
            dtDesconto = sdf.parse("01/01/2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dtExecucao.getTime() < dtInicio.getTime() || dtExecucao.getTime() > dtAtual.getTime()){
            mensagem += "A Data deve ser Maior que 01/01/1900 ou menor que a data atual";
        }

        if (qtdServico <= 1) {
            mensagem += "Quantidade deve ser maior que 1";
        }

        if (vlrServico > 500.00) {
            vlrDesconto = (vlrServico * 0.03);
            vlrServico += vlrServico - vlrDesconto;
        }

        if (dtExecucao.getTime() > dtDesconto.getTime()) {
            vlrDesconto = (vlrServico * 0.01);
            vlrServico += vlrServico - vlrDesconto;
        }

    }

}