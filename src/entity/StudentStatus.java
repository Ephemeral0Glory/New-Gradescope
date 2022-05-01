package entity;

public enum StudentStatus {

    ACTIVE, WITHDRAWN;

    public static StudentStatus getStudentStatus(String season) {
        for (StudentStatus status : StudentStatus.values()) {
            if (status.toString().equalsIgnoreCase(season)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }
}