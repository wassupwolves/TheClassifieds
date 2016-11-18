package edu.neumont.pro150.datamodels;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
			name="post_all",
			query="from Post order by post_date"
	),
	@NamedQuery(
			name="single_post",
			query="from Post where post_id = :post_id"
	)
})

@Entity
@Table(name="post")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer post_id;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="user_id", columnDefinition="user_id")
	private Consumer consumer;
	
	@Column
	private String post_title;
	
	@Column
	private String post_description;
	
	@Column
	private double post_price;
	
	@Column
	private String post_pictures;
	
	@Column
	private String post_tags;
	
	@Column
	private Date post_date;

	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_description() {
		return post_description;
	}

	public void setPost_description(String post_description) {
		this.post_description = post_description;
	}

	public double getPost_price() {
		return post_price;
	}

	public void setPost_price(double post_price) {
		this.post_price = post_price;
	}

	public String getPost_pictures() {
		return post_pictures;
	}

	public void setPost_pictures(String post_pictures) {
		this.post_pictures = post_pictures;
	}

	public String getPost_tags() {
		return post_tags;
	}

	public void setPost_tags(String post_tags) {
		this.post_tags = post_tags;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

}
