package com.example.model;


@Entity
@Table(name="biodata")
public class Biodata {
	@id
	@GeneratedValue(strategy = GenerationType.IDENTITY);
	private long id;

	@Column(name="name")
	private String name;

	@Column(name="nim")
	private String nim;

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}
	public long getNamr(){
		return name;
	}

	public void setName(long id){
		this.name = name;
	}
	public long getNim(){
		return nim;
	}

	public void setNim(long id){
		this.nim = nim;
	}

}