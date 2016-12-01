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
			name="postImage_all",
			query="from PostImage where post_id = :post_id"
	)
})

@Entity
@Table(name="postimage")
public class PostImage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postimage_id;
	
	@ManyToOne
	@JoinColumn(name="post_id", referencedColumnName="post_id")
	private Post post;
	
	@Column
	private String url;
	
	@Column
	private String absolutepath;
	
	@Column
	private Date upload_datetime;

	public Integer getPostimage_id() {
		return postimage_id;
	}

	public void setPostimage_id(Integer postimage_id) {
		this.postimage_id = postimage_id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAbsolutepath() {
		return absolutepath;
	}

	public void setAbsolutepath(String absolutepath) {
		this.absolutepath = absolutepath;
	}

	public Date getUpload_datetime() {
		return upload_datetime;
	}

	public void setUpload_datetime(Date upload_datetime) {
		this.upload_datetime = upload_datetime;
	}

}
