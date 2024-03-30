package br.com.marcelo.restwithspringbootinjava.unittests.mockito;

import br.com.marcelo.restwithspringbootinjava.exceptions.RequiredObjectIsNullException;
import br.com.marcelo.restwithspringbootinjava.mapper.BookMapper;
import br.com.marcelo.restwithspringbootinjava.model.Book;
import br.com.marcelo.restwithspringbootinjava.repository.BookRepository;
import br.com.marcelo.restwithspringbootinjava.services.BookServices;
import br.com.marcelo.restwithspringbootinjava.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {
	MockBook input;
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repository;
	BookMapper bookMapper = BookMapper.INSTANCE;
	private Logger logger =Logger.getLogger(BookServicesTest.class.getName());
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
	}

	@Test
	void testFindById() throws Exception {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		var vo = bookMapper.bookToBookVO(entity);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		//when(personMapper.personToPersonVO(entity)).thenReturn(vo);
		var result = service.findById(1L);
		assertEquals(entity,bookMapper.bookVOToBook(vo));
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAutor());
		assertEquals(LocalDate.of(1999, Month.APRIL,4), result.getDataLancamento());
		assertEquals(25D, result.getPreco());
		assertEquals("Some Title1", result.getTitulo());
		logger.info("Sucesso");

	}

	@Test
	void testCreate() throws Exception {
		var entity = input.mockEntity(1);
		entity.setId(1L);
		var persisted = entity;
		persisted.setId(1L);
		when(repository.save(entity)).thenReturn(persisted);
		var vo = bookMapper.bookToBookVO(entity);
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAutor());
		assertEquals(LocalDate.of(1999, Month.APRIL,4), result.getDataLancamento());
		assertEquals(25D, result.getPreco());
		assertEquals("Some Title1", result.getTitulo());
		logger.info("Sucesso");
	}

	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
//
//
	@Test
	void testUpdate() throws Exception {
		var entity = input.mockEntity(1);
		entity.setId(1L);
		var persisted = entity;
		persisted.setId(1L);
		when(repository.save(entity)).thenReturn(persisted);
		var vo = bookMapper.bookToBookVO(entity);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAutor());
		assertEquals(LocalDate.of(1999, Month.APRIL,4), result.getDataLancamento());
		assertEquals(25D, result.getPreco());
		assertEquals("Some Title1", result.getTitulo());
		logger.info("Sucesso");
	}
//
//
//
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
//
	@Test
	void testDelete() {
		var entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}
//
	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var books = service.findByAll();

		assertNotNull(books);
		assertEquals(14, books.size());

		var bookOne = books.get(1);

		assertNotNull(bookOne);
		assertNotNull(bookOne.getId());
		assertNotNull(bookOne.getLinks());
		assertTrue(bookOne.toString().contains("links: [</book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", bookOne.getAutor());
		assertEquals(LocalDate.of(1999, Month.APRIL,4), bookOne.getDataLancamento());
		assertEquals(25D, bookOne.getPreco());
		assertEquals("Some Title1", bookOne.getTitulo());
		var bookSeven = books.get(7);

		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getId());
		assertNotNull(bookSeven.getLinks());
		assertTrue(bookSeven.toString().contains("links: [</book/v1/7>;rel=\"self\"]"));
		assertEquals("Some Author7", bookSeven.getAutor());
		assertEquals(LocalDate.of(1999, Month.APRIL,4), bookSeven.getDataLancamento());
		assertEquals(25D, bookSeven.getPreco());
		assertEquals("Some Title7", bookSeven.getTitulo());
	}

}
