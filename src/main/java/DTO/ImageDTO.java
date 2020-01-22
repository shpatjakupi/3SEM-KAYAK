/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Image;

/**
 *
 * @author shpattt
 */
public class ImageDTO {
    private Long id;
    private String url;

    public ImageDTO(Image i) {
        this.id = i.getId();
        this.url = i.getUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageDTO{" + "id=" + id + ", url=" + url + '}';
    }
    
}
