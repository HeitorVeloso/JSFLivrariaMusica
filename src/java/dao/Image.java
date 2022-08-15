/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author Heitor Veloso
 */
@Entity
@XmlRootElement
public class Image implements Serializable{

    @Id
    private Long id;
    private StreamedContent image;

    public Image(){
        
    }
    
    public Image(Long id, StreamedContent image) {
        this.id = id;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }
    
    @Override 
    public String toString() {
        return "dao.Image[ id=" + id + " ]";
    }
}
