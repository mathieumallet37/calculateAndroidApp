package fr.avp41.calculate.model;

import android.os.Build;

import java.time.LocalDate;

public class MetrageModel {

    private long id;
    private String nomClient;
    private String prenomClient;
    private String adresseClient;
    private String codePostalClient;
    private String villeClient;
    private String telephoneClient;
    private String enseigne;
    private LocalDate dateMetrage;

    public MetrageModel(long id, String nomClient, String prenomClient, String adresseClient, String codePostalClient, String villeClient, String telephoneClient, String enseigne, String dateMetrage) {
        this.id = id;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.adresseClient = adresseClient;
        this.codePostalClient = codePostalClient;
        this.villeClient = villeClient;
        this.telephoneClient = telephoneClient;
        this.enseigne = enseigne;
        if (dateMetrage != null && !dateMetrage.isEmpty()) {
            this.dateMetrage = LocalDate.parse(dateMetrage);
        } else {
            this.dateMetrage = null;
        }
    }

    public MetrageModel(long id, String nomClient, String dateMetrage) {
        this.id = id;
        this.nomClient = nomClient;
        if (dateMetrage != null && !dateMetrage.isEmpty()) {
            this.dateMetrage = LocalDate.parse(dateMetrage);
        } else {
            this.dateMetrage = null;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getCodePostalClient() {
        return codePostalClient;
    }

    public void setCodePostalClient(String codePostalClient) {
        this.codePostalClient = codePostalClient;
    }

    public String getVilleClient() {
        return villeClient;
    }

    public void setVilleClient(String villeClient) {
        this.villeClient = villeClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public String getEnseigne() {
        return enseigne;
    }

    public void setEnseigne(String enseigne) {
        this.enseigne = enseigne;
    }

    public String getDateMetrage() {
        return dateMetrage != null ? dateMetrage.toString() : null;
    }
}
