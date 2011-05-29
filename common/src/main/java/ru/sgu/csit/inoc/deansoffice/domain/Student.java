package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:28:40 AM
 */
@Entity
public class Student extends Person {
    /**
     *  This is number of student ticket.
     */
    private String studentIdNumber;
    
    private Integer course;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Group group;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Speciality speciality;

    private Division division;
    private StudyForm studyForm;
    private Role role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private EnrollmentOrder enrollmentOrder;

    //@OneToOne(fetch = FetchType.LAZY)
    @OneToOne(cascade = CascadeType.ALL)
    private AdditionalStudentData additionalData;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Stipend> stipends = new HashSet<Stipend>();

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(StudyForm studyForm) {
        this.studyForm = studyForm;
    }

    public EnrollmentOrder getEnrollmentOrder() {
        return enrollmentOrder;
    }

    public void setEnrollmentOrder(EnrollmentOrder enrollmentOrder) {
        this.enrollmentOrder = enrollmentOrder;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public AdditionalStudentData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalStudentData additionalData) {
        this.additionalData = additionalData;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Stipend> getStipends() {
        return stipends;
    }

    public void setStipends(Set<Stipend> stipends) {
        this.stipends = stipends;
    }

    public void addStipend(Stipend stipend) {
        stipends.add(stipend);
    }

    public enum Division {
        INTRAMURAL, EXTRAMURAL, EVENINGSTUDY
    }

    public enum StudyForm {
        BUDGET, COMMERCIAL
    }

    public enum Role {
        CAPTAIN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Student)) {
            return false;
        }

        Student that = (Student) o;

        return super.equals(o) &&
                Objects.equal(this.studentIdNumber, that.studentIdNumber) &&
                Objects.equal(this.course, that.course) &&
                Objects.equal(this.group, that.group) &&
                Objects.equal(this.speciality, that.speciality) &&
                Objects.equal(this.division, that.division) &&
                Objects.equal(this.studyForm, that.studyForm) &&
                Objects.equal(this.role, that.role) &&
                Objects.equal(this.enrollmentOrder, that.enrollmentOrder) &&
                Objects.equal(this.additionalData, that.additionalData) &&
                Objects.equal(this.stipends, that.stipends);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                studentIdNumber,
                course,
                group,
                speciality,
                division,
                studyForm,
                role,
                enrollmentOrder,
                additionalData,
                stipends);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("studentIdNumber", studentIdNumber)
                .add("course", course)
                .add("group", group)
                .add("speciality", speciality)
                .add("division", division)
                .add("studyForm", studyForm)
                .add("role", role)
                .add("enrollmentOrder", enrollmentOrder)
                .add("additionalData", additionalData)
                .add("stipends", stipends)
                .toString();
    }

    @Entity
    public static class AdditionalStudentData extends PersistentItem {
        //@OneToOne(fetch = FetchType.LAZY)
        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Photo photo;

        private String birthPlace;
        private String education;
        private String workInfo;

//        @ElementCollection(fetch = FetchType.EAGER)
        @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
        @OrderColumn(name="passports_index")
        private List<Passport> passports = new ArrayList<Passport>();

        private String maritalStatus;
        private String childrenInfo;

        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Parent father;

        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Parent mother;

        private String oldAddress;

        private String actualAddress;

        public Photo getPhoto() {
            return photo;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        public void setBirthPlace(String birthPlace) {
            this.birthPlace = birthPlace;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getWorkInfo() {
            return workInfo;
        }

        public void setWorkInfo(String workInfo) {
            this.workInfo = workInfo;
        }

        @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
        public List<Passport> getPassports() {
            return passports;
        }

        public void setPassports(List<Passport> passports) {
            this.passports = passports;
        }

        public void addPassport(Passport passport) {
            if (this.passports == null) {
                this.passports = new ArrayList<Passport>();
            }
            this.passports.add(passport);
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public String getChildrenInfo() {
            return childrenInfo;
        }

        public void setChildrenInfo(String childrenInfo) {
            this.childrenInfo = childrenInfo;
        }

        public Parent getFather() {
            return father;
        }

        public void setFather(Parent father) {
            this.father = father;
        }

        public Parent getMother() {
            return mother;
        }

        public void setMother(Parent mother) {
            this.mother = mother;
        }

        public String getOldAddress() {
            return oldAddress;
        }

        public void setOldAddress(String oldAddress) {
            this.oldAddress = oldAddress;
        }

        public String getActualAddress() {
            return actualAddress;
        }

        public void setActualAddress(String actualAddress) {
            this.actualAddress = actualAddress;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof AdditionalStudentData)) {
                return false;
            }

            AdditionalStudentData that = (AdditionalStudentData) o;

            return super.equals(o) &&
                    Objects.equal(this.photo, that.photo) &&
                    Objects.equal(this.birthPlace, that.birthPlace) &&
                    Objects.equal(this.education, that.education) &&
                    Objects.equal(this.workInfo, that.workInfo) &&
                    Objects.equal(this.passports, that.passports) &&
                    Objects.equal(this.maritalStatus, that.maritalStatus) &&
                    Objects.equal(this.childrenInfo, that.childrenInfo) &&
                    Objects.equal(this.father, that.father) &&
                    Objects.equal(this.mother, that.mother) &&
                    Objects.equal(this.oldAddress, that.oldAddress) &&
                    Objects.equal(this.actualAddress, that.actualAddress);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(
                    super.hashCode(),
                    photo,
                    birthPlace,
                    education,
                    workInfo,
                    passports,
                    maritalStatus,
                    childrenInfo,
                    father,
                    mother,
                    oldAddress,
                    actualAddress);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .addValue(super.toString())
                    .add("photo", photo)
                    .add("birthPlace", birthPlace)
                    .add("education", education)
                    .add("workInfo", workInfo)
                    .add("passports", passports)
                    .add("maritalStatus", maritalStatus)
                    .add("childrenInfo", childrenInfo)
                    .add("father", father)
                    .add("mother", mother)
                    .add("oldAddress", oldAddress)
                    .add("actualAddress", actualAddress)
                    .toString();
        }
    }
}
