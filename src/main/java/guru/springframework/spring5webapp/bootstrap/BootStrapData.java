package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Publisher publisher=new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Peterburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: "+publisherRepository.count());

        Author eric=new Author("Eric","Evans");
        Book book=new Book("Down to the Earth","123456");
        eric.getBookSet().add(book);
        book.getAuthorSet().add(eric);

        book.setPublisher(publisher);
        publisher.getBookSet().add(book);

        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author rod=new Author("Rod","Johson");
        Book noEJB=new Book("J2EE Developer without EJB","39945656");

        rod.getBookSet().add(noEJB);
        noEJB.getAuthorSet().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBookSet().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Started in BootStrap");
        System.out.println("Number of Books:"+bookRepository.count());
        System.out.println("Number of Books publisher:"+publisher.getBookSet().size());

        System.out.println("Books:"+bookRepository.findAll());
        System.out.println("Authors:"+authorRepository.findAll());

    }
}
