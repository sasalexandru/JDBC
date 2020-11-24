package personal.projects;

public class AccommodationFairRelation {
    private int id;
    private int idAccommodation;
    private int idRoomFair;

    public AccommodationFairRelation(int idAccommodation, int idRoomFair) {
        this.idAccommodation = idAccommodation;
        this.idRoomFair = idRoomFair;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccommodation() {
        return idAccommodation;
    }

    public void setIdAccomodation(int idAccomodation) {
        this.idAccommodation = idAccomodation;
    }

    public int getIdRoomFair() {
        return idRoomFair;
    }

    public void setIdRoomFair(int idRoomFair) {
        this.idRoomFair = idRoomFair;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccommodationFairRelation that = (AccommodationFairRelation) o;

        if (id != that.id) return false;
        if (idAccommodation != that.idAccommodation) return false;
        return idRoomFair == that.idRoomFair;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idAccommodation;
        result = 31 * result + idRoomFair;
        return result;
    }

    @Override
    public String toString() {
        return "AccomodationFairRelation{" +
                "id=" + id +
                ", idAccomodation=" + idAccommodation +
                ", idRoomFair=" + idRoomFair +
                '}';
    }
}
