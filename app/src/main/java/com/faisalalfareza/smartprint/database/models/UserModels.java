package com.faisalalfareza.smartprint.database.models;

public class UserModels {
    public static final String TABLE_NAME = "ms_user";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_ROLEDESC = "roleDesc";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CREATEDDATE = "createdDate";
    public static final String COLUMN_ISLOGGEDIN = "isLoggedIn";
    public static final String COLUMN_RATING = "howManyRating";
    public static final String COLUMN_TRANSACTION = "howManyTransaction";

    private int id, isLoggedIn, howManyTransaction;
    private String name, role, roleDesc, email, createdDate;
    private double howManyRating;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_ROLE + " TEXT,"
                    + COLUMN_ROLEDESC + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_CREATEDDATE + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_ISLOGGEDIN + " INTEGER,"
                    + COLUMN_RATING + " DOUBLE DEFAULT 0,"
                    + COLUMN_TRANSACTION + " INTEGER DEFAULT 0"
                    + ")";

    public static final String U_NAME_VALUE = "FAISAL ALFAREZA";
    public static final String U_ROLEDESC_VALUE = "Users or customers who use printing services for their documents";
    public static final String U_EMAIL_VALUE = "user@smartprint.com";

    public static final String M_NAME_VALUE = "MAESTRO PRINTING";
    public static final String M_ROLEDESC_VALUE = "There is to provide printing services to customers";
    public static final String M_EMAIL_VALUE = "merchant@smartprint.com";

    public UserModels() {
    }

    public UserModels(int id, String name, String role, String roleDesc, String email, String createdDate, int isLoggedIn, double howManyRating, int howManyTransaction) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.roleDesc = roleDesc;
        this.email = email;
        this.createdDate = createdDate;
        this.isLoggedIn = isLoggedIn;
        this.howManyRating = howManyRating;
        this.howManyTransaction = howManyTransaction;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getRoleDesc() {
        return roleDesc;
    }
    public String getEmail() { return email; }
    public int getStatus() { return isLoggedIn; }
    public double getRating() { return howManyRating; }
    public int getTransaction() { return howManyTransaction; }
}
