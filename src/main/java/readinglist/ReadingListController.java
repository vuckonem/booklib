package readinglist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// ReadingListController is annotated with @Controller in order to be picked up by component-scanning and 
// automatically be registered as a bean in the Spring application context
@Controller
// It’s also annotated with @RequestMapping to map all of its handler methods
// to a base URL path of “/”.
@RequestMapping("/")
public class ReadingListController {
	
	private ReadingListRepository readingListRepository;
	
	@Autowired
	public ReadingListController( ReadingListRepository readingListRepository ) {
			this.readingListRepository = readingListRepository;
	}
	
	@RequestMapping(value="/{reader}", method=RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
		}
		
		return "readingList";
	}
	
	
	@RequestMapping(value="/{reader}", method=RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		
		book.setReader(reader);
		readingListRepository.save(book);
		
		return "redirect:/{reader}";
	}
	
//	// TASK!!!!!!
	// In this way when removing book with isbn you have to load this url http://localhost:8080/readingList/{reader}/{isbn} in the browser and then book is removed
	@RequestMapping(value="/{reader}/{isbn}", method=RequestMethod.GET)
	public String removeFromReadingList(@PathVariable("isbn") String isbn) {
		
		Book deleteBook = readingListRepository.findByIsbn(isbn).get(0);
		
		readingListRepository.delete(deleteBook);
		
		return "redirect:/{reader}";
	}
	
}
