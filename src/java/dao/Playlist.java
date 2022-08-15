/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Metodos.LoginUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Playlist implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    
    private String nome; 
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ImagemMusica> listaImagemMusica;

    public Playlist() {
    }

    public Playlist(String nome, List<ImagemMusica> listaImagemMusica) {
        this.nome=nome;
        this.listaImagemMusica=listaImagemMusica;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ImagemMusica> getListaImagemMusica() {
        return listaImagemMusica;
    }

    public void setListaImagemMusica(List<ImagemMusica> listaImagemMusica) {
        this.listaImagemMusica = listaImagemMusica;
    }  
    
    @Override
    public String toString() {
        return nome;
    }
    
}
