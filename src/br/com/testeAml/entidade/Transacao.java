
package br.com.testeAml.entidade;


public class Transacao {
    
    private Long idTransacao;
    private String orgao;
    private String portador;
    private String favorecido;
    private String tipoTransacao;
    private Double valor;
    private Integer count;
    
    public Transacao(){
        
    }

    public Transacao(String orgao, String portador, String favorecido, String tipoTransacao, Double valor) {
        this.orgao = orgao;
        this.portador = portador;
        this.favorecido = favorecido;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
    }
    
    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public String getPortador() {
        return portador;
    }

    public void setPortador(String portador) {
        this.portador = portador;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
}
