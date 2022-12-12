package org.sid.entities;


	import javax.persistence.*;

	@Entity
	@Table(name = "Reclamation")
	public class Reclamation {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;

		@Column(name = "title")
		private String title;

		@Column(name = "description")
		private String description;

		@Column(name = "traité")
		private boolean traité;

		public Reclamation() {

		}

		public Reclamation(String title, String description, boolean published) {
			this.title = title;
			this.description = description;
			this.traité = traité;
		}

		public long getId() {
			return id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public boolean isTraité() {
			return traité;
		}

		public void setTraité(boolean isTraité) {
			this.traité = isTraité;
		}

		@Override
		public String toString() {
			return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", traité=" + traité + "]";
		
	}

}
