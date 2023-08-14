package com.spring.eventPlaning.constant;

public class ExceptionMessageConstant {
    /**
     * Authentication
     */
    public static final String INVALID_PASSWORD = "Invalid password !";
    public static final String INVALID_AUTH = "Invalid userName or password !";

    /**
     * Customer and Vendor CRUD
     */
    public static final String CUSTOMER_ALREADY_SUBMITTED = "Customer already exist!";
    public static final String VENDOR_ALREADY_SUBMITTED = "Vendor already exist!";
    public static final String RATING_ID_ALREADY_EXIST = "Rating id is already exist!";
    public static final String RECORD_NOT_FOUND = "Record not found for ";
    public static final String CUSTOMER_NOT_FOUND = "No such customer to update..!";
    public static final String VENDOR_NOT_FOUND = "No such vendor to update..!";

    /**
     * pagination filter
     */
    public static final String NO_RECORD_FOUND = "No such records found!";
    public static final String CANNOT_FIND_DATA = "Unable to find data with aql query!";
    public static final String INTERNAL_SERVER_ERROR = "An internal server error occurred";

    /**admin
     *
     */
    public static final String INVALID_RECORD_STATUS = "Invalid record status";
    public static final String EVENT_NOT_FOUND = "No such event to update..!";
    public static final String SERVICE_NOT_FOUND = "No such service to update..!";
    public static final String PACKAGE_NOT_FOUND = "No such package to update..!";

}
