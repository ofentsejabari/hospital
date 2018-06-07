package hosms;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ofentse
 */
public class MysqlConnector {
    
    public static Connection CONNECTION = null;
    public static Statement STATEMENT = null;

    public MysqlConnector() {
        
        try{
            //-- Load the database class driver --
            Class.forName("com.mysql.jdbc.Driver");
            
            //-- constract a database URl
            String dbURL = "jdbc:mysql://127.0.0.1:3306/hospitalappointment";

            //-- Get database connection handler --
            CONNECTION  =  DriverManager.getConnection(dbURL, "root", "");
            if(CONNECTION != null){ 
                //-- create a Statement object for sending SQL statements to the database. --
               STATEMENT = CONNECTION.createStatement();
               System.out.println("Connection to database server has been established");
            }else{
                System.err.println("<< Connection to database server failed >>");
            }
            
        }catch(Exception error ){
            //-- Print a user friendly error --
            System.out.println(error.getMessage());
        }
    }
    
    
    
    /**
     * Insert record into database
     * @param patient
     * @return 
     */
    public boolean insertPatient(Patient patient){
        String query = "INSERT INTO `patients` (`id`, `fname`, `lname`, `cell`, `email`,"
                + " `physicalAddress`, `postalAddress`, `occupation`, `gender`)\n" +
                  "VALUES ('0', '"+patient.getFname()+"', '"+patient.getLname()+"', 'fasd', 'sdf sf sef we', 'wef we', "
                + "'e we', 'w ewer ', '345 34 3f wef')";
        try{
            int rs = STATEMENT.executeUpdate(query);
            if(rs>0){
                return true;
            }else{
                return false;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
    /**
     * Retrieve all patients from the database 
     * @return 
     */
    public ObservableList<Patient> getPatients(){
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        
        String query = "SELECT `id`, `FName`, `LName`, `cell`, `email`, `gender`, "
                + "`postalAddress`, `physicalAddress`, `occupation`\n" +
                  "FROM `patients`";
        try{
            ResultSet rs = STATEMENT.executeQuery(query);
            //-- Go throght the result set one row at a time --
            while (rs.next()) {
                Patient p = new Patient(rs.getString("id"),rs.getString("FName"), rs.getString("LName"),
                        rs.getString("cell"), rs.getString("email"), rs.getString("gender"),
                        rs.getString("postalAddress"), rs.getString("physicalAddress"), rs.getString("gender"));
                
                patients.add(p);
            }
            
            return patients;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return patients;
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Add or update patient records
     * @param patient
     * @return 
     *
    public boolean updatePatient(Patient patient){
        try{
            String query = "INSERT INTO `patients` (`id`, `fname`, `lname`,`cell`, `email`,"
                    + "`postalAddress`, `physicalAddress`, `occupation`, `gender`)"
                    + " VALUES('0', '"+patient.getFname()+"', '"+patient.getLname()+"',"
                    + " '"+patient.getCell()+"' , '"+patient.getEmail()+"', "
                    + " '"+patient.getPostalAddress()+"', '"+patient.getPhysicalAddress()+"',"
                    + " '"+patient.getOccupation()+"', '"+patient.getGender()+"')";
            
            if(!patient.getId().equalsIgnoreCase("")){
                query = "UPDATE `patients` SET "
                    + " `fname`='"+patient.getFname()+"', `cell`='"+patient.getCell()+"',"
                    + " `lname`='"+patient.getLname()+"', `occupation`='"+patient.getOccupation()+"',"
                    + " `email`='"+patient.getEmail()+"',`postalAddress`='"+patient.getPostalAddress()+"',"
                    + " `physicalAddress`='"+patient.getPhysicalAddress()+"',"
                    + " `gender`='"+patient.getGender()+"'"
                    + " WHERE `id`='"+patient.getId()+"'";
            }
            
            return (STATEMENT.executeUpdate(query) > 0)? true : false;
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    /**
     * Add or update patient records
     * @param appointment
     * @return 
     *
    public boolean updateAppointment(Appointment appointment){
        try{
            String query = "INSERT INTO `appointment` (`id`, `patientID`, `doctorID`,`description`, `appdate`, `status`)"
                    + " VALUES('0', '"+appointment.getPatientID()+"', '"+appointment.getDoctorID()+"',"
                    + " '"+appointment.getDescription()+"' , '"+appointment.getDate()+"', '"+appointment.getStatus()+"')";
            
            if(!appointment.getId().equalsIgnoreCase("")){
                query = "UPDATE `appointment` SET "
                    + " `patientID`='"+appointment.getPatientID()+"', `description`='"+appointment.getDescription()+"',"
                    + " `appdate`='"+appointment.getDate()+"', `doctorID`='"+appointment.getDoctorID()+"',"
                    + " `status`='"+appointment.getStatus()+"'"
                    + " WHERE `id`='"+appointment.getId()+"'";
            }
            
            return (STATEMENT.executeUpdate(query) > 0)? true : false;
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public ObservableList<Appointment> getAppointmentsFor(String ID){
        
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `patientID`, `doctorID`, `description`, `appdate`, "
                         + " `status`"
                         + " FROM `appointment` WHERE `doctorID` = '"+ID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                appointments.add(new Appointment(result.getString("id"), result.getString("patientID"),
                result.getString("doctorID"), result.getString("appdate"), result.getString("description"),
                result.getString("status")));
            }
            return appointments;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return appointments;
        }
    }
    
    
    /**
     * Get patient by ID
     * @return 
     *
    public Patient getPatientByID(String ID){
        
        try{
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patients` WHERE `id` = '"+ID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
           if(result.next()){
                return new Patient(result.getString("id"), result.getString("fname"),
                    result.getString("lname"), result.getString("cell"), result.getString("email"),
                    result.getString("gender"), result.getString("physicalAddress"), 
                    result.getString("postalAddress"), result.getString("occupation"));
            }
            return new Patient();
        
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Patient();
        }
    }
    
    
    /**
     * Get all patients
     * @return 
     *
    public ObservableList<Patient> getPatients(){
        
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patients`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                patients.add(new Patient(result.getString("id"), result.getString("fname"),
                result.getString("lname"), result.getString("cell"), result.getString("email"),
                result.getString("gender"), result.getString("physicalAddress"), 
                result.getString("postalAddress"), result.getString("occupation")));
            }
            return patients;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return patients;
        }
    }
    
    /**
     * Get all patients
     * @return 
     *
    public ObservableList<String> getPatientNames(){
        
        ObservableList<String> patients = FXCollections.observableArrayList();
        try{
            String query = " SELECT CONCAT_WS(' ',`fname`,`lname`) AS `fullname` FROM `patients`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                patients.add(result.getString("fullname"));
            }
            return patients;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return patients;
        }
    }
    
    
    /**
     * Get patient profile by name
     * @param name
     * @return 
     *
    public Patient getPatientByName(String name){
        try{
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patients` WHERE CONCAT_WS(' ',`fname`,`lname`) = '"+name+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Patient(result.getString("id"), result.getString("fname"),
                result.getString("lname"), result.getString("cell"), result.getString("email"),
                result.getString("gender"), result.getString("physicalAddress"), 
                result.getString("postalAddress"), result.getString("occupation"));
            }
            return new Patient();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Patient();
        }
    }*/
   
}
