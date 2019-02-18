package com.faisalalfareza.smartprint.database.models;

public class DocumentModels {
    public static final String TABLE_NAME = "tr_doc";

    public static final String COLUMN_id = "document_id";
    public static final String COLUMN_role = "merchant_role";

    public static final String COLUMN_ud1_curLocTitle = "ud1_curLocTitle";
    public static final String COLUMN_ud1_curLocCode = "ud1_curLocCode";

    public static final String COLUMN_ud2_curMerTitle = "ud2_curMerTitle";

    public static final String COLUMN_ud3_curService = "ud3_curService";

    public static final String COLUMN_ud4_curSubService = "ud4_curSubService";
    public static final String COLUMN_ud4_setBuildQuality = "ud4_setBuildQuality";
    public static final String COLUMN_ud4_setPrintedPage = "ud4_setPrintedPage";
    public static final String COLUMN_ud4_setSidesOfPrint = "ud4_setSidesOfPrint";
    public static final String COLUMN_ud4_setPaperSize = "ud4_setPaperSize";
    public static final String COLUMN_ud4_setPaperMargin = "ud4_setPaperMargin";
    public static final String COLUMN_ud4_setOrientation = "ud4_setOrientation";
    public static final String COLUMN_ud4_setPagePerSheet = "ud4_setPagePerSheet";
    public static final String COLUMN_ud4_setBaseColor = "ud4_setBaseColor";

    public static final String COLUMN_ud5_setDocName = "ud5_setDocName";
    public static final String COLUMN_ud5_setAttachFileDir = "ud5_setAttachFileDir";
    public static final String COLUMN_ud5_setNotes = "ud5_setNotes";
    public static final String COLUMN_ud5_setFinishLimitDate = "ud5_setFinishLimitDate";
    public static final String COLUMN_ud5_setBookDate = "ud5_setBookDate";
    public static final String COLUMN_ud5_isToday = "ud5_isToday";

    public static final String COLUMN_status = "status";


    private int document_id;
    private String merchant_role,
            ud1_curLocTitle, ud1_curLocCode,
            ud2_curMerTitle,
            ud3_curService,
            ud4_curSubService, ud4_setBuildQuality, ud4_setPrintedPage, ud4_setSidesOfPrint,
            ud4_setPaperSize, ud4_setPaperMargin, ud4_setOrientation, ud4_setPagePerSheet, ud4_setBaseColor,
            ud5_isToday, ud5_setDocName, ud5_setAttachFileDir, ud5_setNotes, ud5_setFinishLimitDate, ud5_setBookDate,
            status;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_role + " TEXT,"
                    + COLUMN_ud1_curLocTitle + " TEXT,"
                    + COLUMN_ud1_curLocCode + " TEXT,"
                    + COLUMN_ud2_curMerTitle + " TEXT,"
                    + COLUMN_ud3_curService + " TEXT,"
                    + COLUMN_ud4_curSubService + " TEXT,"
                    + COLUMN_ud4_setBuildQuality + " TEXT,"
                    + COLUMN_ud4_setPrintedPage + " TEXT,"
                    + COLUMN_ud4_setSidesOfPrint + " TEXT,"
                    + COLUMN_ud4_setPaperSize + " TEXT,"
                    + COLUMN_ud4_setPaperMargin + " TEXT,"
                    + COLUMN_ud4_setOrientation + " TEXT,"
                    + COLUMN_ud4_setPagePerSheet + " TEXT,"
                    + COLUMN_ud4_setBaseColor + " TEXT,"
                    + COLUMN_ud5_setDocName + " TEXT,"
                    + COLUMN_ud5_setAttachFileDir + " TEXT,"
                    + COLUMN_ud5_setNotes + " TEXT,"
                    + COLUMN_ud5_setFinishLimitDate + " TEXT,"
                    + COLUMN_ud5_setBookDate + " TEXT,"
                    + COLUMN_ud5_isToday + " TEXT,"
                    + COLUMN_status + " TEXT DEFAULT requested"
            + ")";

    public DocumentModels() {
    }

    public DocumentModels(
            int document_id,
            String merchant_role,
            String ud1_curLocTitle, String ud1_curLocCode,
            String ud2_curMerTitle,
            String ud3_curService,
            String ud4_curSubService, String ud4_setBuildQuality, String ud4_setPrintedPage, String ud4_setSidesOfPrint,
            String ud4_setPaperSize, String ud4_setPaperMargin, String ud4_setOrientation, String ud4_setPagePerSheet, String ud4_setBaseColor,
            String ud5_isToday, String ud5_setDocName, String ud5_setAttachFileDir, String ud5_setNotes, String ud5_setFinishLimitDate, String ud5_setBookDate,
            String status
    ) {
        this.document_id = document_id;
        this.merchant_role = merchant_role;
        this.ud1_curLocTitle = ud1_curLocTitle;
        this.ud1_curLocCode = ud1_curLocCode;
        this.ud2_curMerTitle = ud2_curMerTitle;
        this.ud3_curService = ud3_curService;
        this.ud4_curSubService = ud4_curSubService;
        this.ud4_setBuildQuality = ud4_setBuildQuality;
        this.ud4_setPrintedPage = ud4_setPrintedPage;
        this.ud4_setSidesOfPrint = ud4_setSidesOfPrint;
        this.ud4_setPaperSize = ud4_setPaperSize;
        this.ud4_setPaperMargin = ud4_setPaperMargin ;
        this.ud4_setOrientation = ud4_setOrientation ;
        this.ud4_setPagePerSheet = ud4_setPagePerSheet ;
        this.ud4_setBaseColor = ud4_setBaseColor;
        this.ud5_isToday = ud5_isToday;
        this.ud5_setDocName = ud5_setDocName;
        this.ud5_setAttachFileDir = ud5_setAttachFileDir;
        this.ud5_setNotes = ud5_setNotes;
        this.ud5_setFinishLimitDate = ud5_setFinishLimitDate;
        this.ud5_setBookDate = ud5_setBookDate;
        this.status = status;
    }

    public int getDocId() {
        return document_id;
    }
    public String getUd5_setDocName() {
        return ud5_setDocName;
    }

    public void setDocument_id(int document_id) { this.document_id = document_id; }
    public void setMerchant_role(String merchant_role) { this.merchant_role = merchant_role; }
    public void setUd1_curLocTitle(String ud1_curLocTitle) { this.ud1_curLocTitle = ud1_curLocTitle; }
    public void setUd1_curLocCode(String ud1_curLocCode) { this.ud1_curLocCode = ud1_curLocCode; }
    public void setUd2_curMerTitle(String ud2_curMerTitle) { this.ud2_curMerTitle = ud2_curMerTitle; }
    public void setUd3_curService(String ud3_curService) { this.ud3_curService = ud3_curService; }
    public void setUd4_curSubService(String ud4_curSubService) { this.ud4_curSubService = ud4_curSubService; }
    public void setUd4_setBuildQuality(String ud4_setBuildQuality) { this.ud4_setBuildQuality = ud4_setBuildQuality; }
    public void setUd4_setPrintedPage(String ud4_setPrintedPage) { this.ud4_setPrintedPage = ud4_setPrintedPage; }
    public void setUd4_setSidesOfPrint(String ud4_setSidesOfPrint) { this.ud4_setSidesOfPrint = ud4_setSidesOfPrint; }
    public void setUd4_setPaperSize(String ud4_setPaperSize) { this.ud4_setPaperSize = ud4_setPaperSize; }
    public void setUd4_setPaperMargin(String ud4_setPaperMargin) { this.ud4_setPaperMargin = ud4_setPaperMargin ; }
    public void setUd4_setOrientation(String ud4_setOrientation) { this.ud4_setOrientation = ud4_setOrientation ; }
    public void setUd4_setPagePerSheet(String ud4_setPagePerSheet) { this.ud4_setPagePerSheet = ud4_setPagePerSheet ; }
    public void setUd4_setBaseColor(String ud4_setBaseColor) { this.ud4_setBaseColor = ud4_setBaseColor; }
    public void setUd5_isToday(String ud5_isToday) { this.ud5_isToday = ud5_isToday; }
    public void setUd5_setDocName(String ud5_setDocName) { this.ud5_setDocName = ud5_setDocName; }
    public void setUd5_setAttachFileDir(String ud5_setAttachFileDir) { this.ud5_setAttachFileDir = ud5_setAttachFileDir; }
    public void setUd5_setNotes(String ud5_setNotes) { this.ud5_setNotes = ud5_setNotes; }
    public void setUd5_setFinishLimitDate(String ud5_setFinishLimitDate) { this.ud5_setFinishLimitDate = ud5_setFinishLimitDate; }
    public void setUd5_setBookDate(String ud5_setBookDate) { this.ud5_setBookDate = ud5_setBookDate; }
    public void setStatus(String status) { this.status = status; }
}
