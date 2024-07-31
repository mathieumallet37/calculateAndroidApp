package fr.avp41.calculate.model;

public class EnseigneModel {

    private long id;
    private String nomEnseigne;
    private String adresseEnseigne;
    private String codePostalEnseigne;
    private String villeEnseigne;
    private String telephoneEnseigne;
    private String nomContactEnseigne;
    private String emailEnseigne;

    public EnseigneModel(long id, String nomEnseigne, String adresseEnseigne, String codePostalEnseigne, String villeEnseigne, String telephoneEnseigne, String nomContactEnseigne, String emailEnseigne) {
        this.id = id;
        this.nomEnseigne = nomEnseigne;
        this.adresseEnseigne = adresseEnseigne;
        this.codePostalEnseigne = codePostalEnseigne;
        this.villeEnseigne = villeEnseigne;
        this.telephoneEnseigne = telephoneEnseigne;
        this.nomContactEnseigne = nomContactEnseigne;
        this.emailEnseigne = emailEnseigne;
    }

    public EnseigneModel(long id, String nomEnseigne, String villeEnseigne){
        this.id = id;
        this.nomEnseigne = nomEnseigne;
        this.villeEnseigne = villeEnseigne;
    }

    public long getId() {
        return id;
    }

    public String getNomEnseigne() {
        return nomEnseigne;
    }

    public void setNomEnseigne(String nomEnseigne) {
        this.nomEnseigne = nomEnseigne;
    }

    public String getAdresseEnseigne() {
        return adresseEnseigne;
    }

    public void setAdresseEnseigne(String adresseEnseigne) {
        this.adresseEnseigne = adresseEnseigne;
    }

    public String getCodePostalEnseigne() {
        return codePostalEnseigne;
    }

    public void setCodePostalEnseigne(String codePostalEnseigne) {
        this.codePostalEnseigne = codePostalEnseigne;
    }

    public String getVilleEnseigne() {
        return villeEnseigne;
    }

    public void setVilleEnseigne(String villeEnseigne) {
        this.villeEnseigne = villeEnseigne;
    }

    public String getTelephoneEnseigne() {
        return telephoneEnseigne;
    }

    public void setTelephoneEnseigne(String telephoneEnseigne) {
        this.telephoneEnseigne = telephoneEnseigne;
    }

    public String getNomContactEnseigne() {
        return nomContactEnseigne;
    }

    public void setNomContactEnseigne(String nomContactEnseigne) {
        this.nomContactEnseigne = nomContactEnseigne;
    }

    public String getEmailEnseigne() {
        return emailEnseigne;
    }

    public void setEmailEnseigne(String emailEnseigne) {
        this.emailEnseigne = emailEnseigne;
    }


}
