public interface DbInfo {
    String MysqlDriver ="com.mysql.jdbc.Driver";
    String database="jdbc:mysql://localhost/hospital_management_system";
    String databaseUser ="root";
    String databsePassword="";

    String queryGetLoginData = "SELECT * FROM `login` WHERE userName=? AND password=?";
    String querryInsertPatient = "INSERT into patient(name,gender,age,date,contact,address) VALUES(?,?,?,?,?,?)";
    String querryGetPatientData = "SELECT * FROM patient WHERE ID=?";
}
