package User;

import java.math.BigDecimal;

public class student {
    private String classesName;
    private Integer sn;
    private String studentName;
    private String qqMail;
    private String courseName;
    private BigDecimal score;

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getQqMail() {
        return qqMail;
    }

    public void setQqMail(String qqMail) {
        this.qqMail = qqMail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "student{" +
                "classesName='" + classesName + '\'' +
                ", sn='" + sn + '\'' +
                ", studentName='" + studentName + '\'' +
                ", qqMail='" + qqMail + '\'' +
                ", courseName='" + courseName + '\'' +
                ", score=" + score +
                '}';
    }
}
