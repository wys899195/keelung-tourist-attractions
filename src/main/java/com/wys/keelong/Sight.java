package com.wys.keelong;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Sight {

    private String sightName;
    private String zone;
    private String category;
    private String photoURL;
    private String description;
    private String address;

    public Sight() {
        this.sightName = "";
        this.zone = "";
        this.category = "";
        this.photoURL = "";
        this.description = "";
        this.address = "";
    }
    public Sight(String sightName, String zone, String category,
                 String photoURL, String description, String address) {
        this.sightName = sightName;
        this.zone = zone;
        this.category = category;
        this.photoURL = photoURL;
        this.description = description;
        this.address = address;

    }

    @Override
    public String toString(){
        return String.format(
                "SightName: %s\nZone: %s\nCategory: %s\nPhotoURL: %s\nDescription: %s\nAddress: %s\n",
                this.sightName,
                this.zone,
                this.category,
                this.photoURL,
                this.description,
                this.address
        );
    }
}
