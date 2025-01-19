package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private List<ElementoCatalogo> catalogo = new ArrayList<>();

    // Aggiunta di un elemento
    public void aggiungiElemento(ElementoCatalogo elemento) throws IllegalArgumentException {
        if (catalogo.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            throw new IllegalArgumentException("Elemento con ISBN " + elemento.getIsbn() + " già presente.");
        }
        catalogo.add(elemento);
    }

    // Ricerca per ISBN
    public ElementoCatalogo cercaPerIsbn(String isbn) throws NoSuchElementException {
        return catalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Elemento con ISBN " + isbn + " non trovato."));
    }

    // Rimozione di un elemento
    public void rimuoviElemento(String isbn) throws NoSuchElementException {
        ElementoCatalogo elemento = cercaPerIsbn(isbn);
        catalogo.remove(elemento);
    }

    // Ricerca per anno di pubblicazione
    public List<ElementoCatalogo> cercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    // Ricerca per autore
    public List<Libro> cercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    // Aggiornamento di un elemento
    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) throws NoSuchElementException {
        ElementoCatalogo elementoEsistente = cercaPerIsbn(isbn);
        int index = catalogo.indexOf(elementoEsistente);
        catalogo.set(index, nuovoElemento);
    }

    // Statistiche del catalogo
    public void stampaStatistiche() {
        long numLibri = catalogo.stream().filter(e -> e instanceof Libro).count();
        long numRiviste = catalogo.stream().filter(e -> e instanceof Rivista).count();
        ElementoCatalogo maxPagine = catalogo.stream()
                .max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine))
                .orElseThrow(() -> new NoSuchElementException("Catalogo vuoto."));
        double mediaPagine = catalogo.stream()
                .collect(Collectors.averagingInt(ElementoCatalogo::getNumeroPagine));

        System.out.println("Numero totale di libri: " + numLibri);
        System.out.println("Numero totale di riviste: " + numRiviste);
        System.out.println("Elemento con maggior numero di pagine: " + maxPagine);
        System.out.println("Media delle pagine: " + mediaPagine);
    }

    // Metodo per stampare tutti gli elementi del catalogo
    public void stampaCatalogo() {
        catalogo.forEach(System.out::println);
    }
}
