package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {
   private static final long serialVersionUID = 2222L;
   
   private int noticeNo;
   private String noticeTitle;
   private java.sql.Date noticeDate;
   private String noticeWriter;
   private String noticeContent;
   private String originalFilepath;
   private String renameFilepath;

   public Notice() {}

   public Notice(int noticeNo, String noticeTitle, Date noticeDate, String noticeWriter, String noticeContent,
         String originalFilepath, String renameFilepath) {
      super();
      this.noticeNo = noticeNo;
      this.noticeTitle = noticeTitle;
      this.noticeDate = noticeDate;
      this.noticeWriter = noticeWriter;
      this.noticeContent = noticeContent;
      this.originalFilepath = originalFilepath;
      this.renameFilepath = renameFilepath;
   }

   public int getNoticeNo() {
      return noticeNo;
   }

   public void setNoticeNo(int noticeNo) {
      this.noticeNo = noticeNo;
   }

   public String getNoticeTitle() {
      return noticeTitle;
   }

   public void setNoticeTitle(String noticeTitle) {
      this.noticeTitle = noticeTitle;
   }

   public java.sql.Date getNoticeDate() {
      return noticeDate;
   }

   public void setNoticeDate(java.sql.Date noticeDate) {
      this.noticeDate = noticeDate;
   }

   public String getNoticeWriter() {
      return noticeWriter;
   }

   public void setNoticeWriter(String noticeWriter) {
      this.noticeWriter = noticeWriter;
   }

   public String getNoticeContent() {
      return noticeContent;
   }

   public void setNoticeContent(String noticeContent) {
      this.noticeContent = noticeContent;
   }

   public String getOriginalFilepath() {
      return originalFilepath;
   }

   public void setOriginalFilepath(String originalFilepath) {
      this.originalFilepath = originalFilepath;
   }

   public String getRenameFilepath() {
      return renameFilepath;
   }

   public void setRenameFilepath(String renameFilepath) {
      this.renameFilepath = renameFilepath;
   }

   @Override
   public String toString() {
      return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeDate=" + noticeDate
            + ", noticeWriter=" + noticeWriter + ", noticeContent=" + noticeContent + ", originalFilepath="
            + originalFilepath + ", renameFilepath=" + renameFilepath + "]";
   }
   
}