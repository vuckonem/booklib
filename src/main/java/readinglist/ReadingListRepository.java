package readinglist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// The JpaRepository interface is parameterized with two parameters: 
// the domain type that the repository will work with, and the type of its ID property

public interface ReadingListRepository extends JpaRepository<Book, Long> {
	
	// findByReader() method through which a reading list can be looked up given a reader’s username
	List<Book> findByReader(String reader);

	List<Book> findByIsbn(String isbn);
	
}
