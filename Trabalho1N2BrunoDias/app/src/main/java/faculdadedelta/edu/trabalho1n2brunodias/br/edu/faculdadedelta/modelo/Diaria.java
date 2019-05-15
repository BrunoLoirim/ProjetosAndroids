package faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Diaria implements Serializable {

    private Long id;
    private String destino;
    private Date dtSaida;
    private Date dtChegada;
    private String itinerario;
    private String funcionario;
    private double valor;

    public Diaria() {
    }

    public Diaria(Long id, String destino, Date dtSaida, Date dtChegada, String itinerario, String funcionario, double valor) {
        this.id = id;
        this.destino = destino;
        this.dtSaida = dtSaida;
        this.dtChegada = dtChegada;
        this.itinerario = itinerario;
        this.funcionario = funcionario;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(Date dtSaida) {
        this.dtSaida = dtSaida;
    }

    public Date getDtChegada() {
        return dtChegada;
    }

    public void setDtChegada(Date dtChegada) {
        this.dtChegada = dtChegada;
    }

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diaria diaria = (Diaria) o;
        return Objects.equals(id, diaria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void calculaDesconto(){

        if ((dtChegada.getTime() - dtSaida.getTime ()) / 1000 / 60 / 60 / 24 > 20){

            valor -= valor * 0.02;
        }

        if (destino.equals ("Goias")){

            valor -= valor * 0.001;
        }

    }

}
