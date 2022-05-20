package qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "delete_history")
public class DeleteHistory {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "content_type", length = 255)
	private ContentType contentType;
	@Column(name = "content_id")
	private Long contentId;
	@Column(name = "create_date")
	private LocalDateTime createDate = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deleted_by_id", foreignKey = @ForeignKey(name = "fk_delete_history_to_user"))
	private User deletedByUser;

	public DeleteHistory(ContentType contentType, Long contentId, User deletedByUser, LocalDateTime createDate) {
		this.contentType = contentType;
		this.contentId = contentId;
		this.deletedByUser = deletedByUser;
		this.createDate = createDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DeleteHistory that = (DeleteHistory) o;
		return Objects.equals(id, that.id) && contentType == that.contentType
				&& Objects.equals(contentId, that.contentId)
				&& Objects.equals(deletedByUser.getId(), that.deletedByUser.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, contentType, contentId, deletedByUser.getId());
	}

	@Override
	public String toString() {
		return "DeleteHistory{" + "id=" + id + ", contentType=" + contentType + ", contentId=" + contentId
				+ ", deletedById=" + deletedByUser.getId() + ", createDate=" + createDate + '}';
	}
}
