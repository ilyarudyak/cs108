package gui.events;

import model.AgeCategory;

import java.util.EventObject;

/**
 * Created by ilyarudyak on 6/14/16.
 */
public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private AgeCategory ageCategory;
    private String empCat;
    private String taxId;
    private boolean usCitizen;
    private String gender;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String occupation, AgeCategory ageCat,
                     String empCat, String taxId, boolean usCitizen, String gender) {
        super(source);

        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCat;
        this.empCat = empCat;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
        this.gender = gender;
    }

    // getters and setters
    public String getGender() {
        return gender;
    }
    public String getTaxId() {
        return taxId;
    }
    public boolean isUsCitizen() {
        return usCitizen;
    }
    public String getName() {
        return name;
    }
    public String getOccupation() {
        return occupation;
    }
    public AgeCategory getAgeCategory() {
        return ageCategory;
    }
    public String getEmploymentCategory() {
        return empCat;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }


}
























