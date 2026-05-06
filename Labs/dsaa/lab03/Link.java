package dsaa.lab03;

public class Link{
	public String ref;
	public Link(String ref) {
		this.ref=ref;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Link other = (Link) obj;

		return (this.ref == null) ? (other.ref == null) : this.ref.equals(other.ref);
	}

	@Override
	public String toString() {
		return ref;
	}
	
}