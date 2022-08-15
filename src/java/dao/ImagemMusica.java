/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Metodos.LoginUtil;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stefanini
 */
@Entity
@XmlRootElement
public class ImagemMusica implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String caminhoImagem;
    private String caminhoMusica;
    private String nomeMusica;

    public ImagemMusica() {
    }

    public ImagemMusica(String caminhoImagem, String caminhoMusica, String nomeMusica) {
        this.caminhoImagem = caminhoImagem;
        this.caminhoMusica = caminhoMusica;
        this.nomeMusica = nomeMusica;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getCaminhoMusica() {
        return caminhoMusica;
    }

    public void setCaminhoMusica(String caminhoMusica) {
        this.caminhoMusica = caminhoMusica;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImagemMusica imagemMusica = (ImagemMusica) o;
        return id == imagemMusica.id
                && Objects.equals(caminhoImagem, imagemMusica.caminhoImagem)
                && Objects.equals(caminhoMusica, imagemMusica.caminhoMusica)
                && Objects.equals(nomeMusica, imagemMusica.nomeMusica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caminhoImagem, caminhoMusica,nomeMusica);
    }
    
    @Override
    public String toString() {
        return caminhoImagem + ' ' + caminhoMusica;
    }
    
}
