package com.capgemini.studentManagementSystem.entity;



import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String course;
    private int marks;

    @Lob
    private byte[] profileImage;

    @Lob
    private byte[] assignmentFile;

    public Student() {}

    public Student(String name,String email,String course,int marks){
        this.name=name;
        this.email=email;
        this.course=course;
        this.marks=marks;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public byte[] getAssignmentFile() {
		return assignmentFile;
	}

	public void setAssignmentFile(byte[] assignmentFile) {
		this.assignmentFile = assignmentFile;
	}

   
}