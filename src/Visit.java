
public class Visit {


    private int id;
    private int patientId;
    private String specialty;
    private String date;
    private String place;
    private boolean confirmed;


    public Visit(String specialty, String date, String place, boolean confirmed) {
        this.specialty = specialty;
        this.date = date;
        this.place = place;
        this.confirmed = confirmed;
    }

    public Visit(int id, int patientId, String specialty, String date, String place, boolean confirmed) {
        this.id = id;
        this.patientId = patientId;
        this.specialty = specialty;
        this.date = date;
        this.place = place;
        this.confirmed = confirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", specialty='" + specialty + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", confirmed=" + confirmed +
                '}';
    }
}
