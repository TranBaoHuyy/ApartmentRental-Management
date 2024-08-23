package entity;

public class Apartment {
    private String id;
    private String notes;
    private String areaName;
    private String householdId;
    private int typeId;
    private String status;

    public Apartment() {
    }

    public Apartment(String id, String notes, String areaName,
                     String householdId, int typeId, String status) {
        this.id = id;
        this.notes = notes;
        this.areaName = areaName;
        this.householdId = householdId;
        this.typeId = typeId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(String householdId) {
        this.householdId = householdId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id='" + id + '\'' +
                ", notes='" + notes + '\'' +
                ", areaName='" + areaName + '\'' +
                ", householdId='" + householdId + '\'' +
                ", typeId=" + typeId +
                ", status='" + status + '\'' +
                '}';
    }
}
