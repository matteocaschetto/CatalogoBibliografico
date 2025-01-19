package org.example;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();

        while (true) {
            System.out.println("*** Menu Catalogo Bibliotecario ***");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Aggiungi rivista");
            System.out.println("3. Cerca per ISBN");
            System.out.println("4. Rimuovi per ISBN");
            System.out.println("5. Cerca per anno pubblicazione");
            System.out.println("6. Cerca per autore");
            System.out.println("7. Aggiorna elemento");
            System.out.println("8. Statistiche del catalogo");
            System.out.println("9. Stampa catalogo");
            System.out.println("10. Esci");
            System.out.print("Scegli un'operazione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1: // Aggiungi libro
                    System.out.print("Inserisci ISBN: ");
                    String isbnLibro = scanner.nextLine();
                    System.out.print("Inserisci titolo: ");
                    String titoloLibro = scanner.nextLine();
                    System.out.print("Inserisci anno pubblicazione: ");
                    int annoLibro = scanner.nextInt();
                    System.out.print("Inserisci numero pagine: ");
                    int pagineLibro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci autore: ");
                    String autoreLibro = scanner.nextLine();
                    System.out.print("Inserisci genere: ");
                    String genereLibro = scanner.nextLine();
                    try {
                        archivio.aggiungiElemento(new Libro(isbnLibro, titoloLibro, annoLibro, pagineLibro, autoreLibro, genereLibro));
                        System.out.println("Libro aggiunto con successo!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 2: // Aggiungi rivista
                    System.out.print("Inserisci ISBN: ");
                    String isbnRivista = scanner.nextLine();
                    System.out.print("Inserisci titolo: ");
                    String titoloRivista = scanner.nextLine();
                    System.out.print("Inserisci anno pubblicazione: ");
                    int annoRivista = scanner.nextInt();
                    System.out.print("Inserisci numero pagine: ");
                    int pagineRivista = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                    String periodicita = scanner.nextLine().toUpperCase();
                    try {
                        Rivista.Periodicita period = Rivista.Periodicita.valueOf(periodicita);
                        archivio.aggiungiElemento(new Rivista(isbnRivista, titoloRivista, annoRivista, pagineRivista, period));
                        System.out.println("Rivista aggiunta con successo!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 3: // Cerca per ISBN
                    System.out.print("Inserisci ISBN: ");
                    String isbnCerca = scanner.nextLine();
                    try {
                        ElementoCatalogo elemento = archivio.cercaPerIsbn(isbnCerca);
                        System.out.println("Elemento trovato: " + elemento);
                    } catch (NoSuchElementException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 4: // Rimuovi per ISBN
                    System.out.print("Inserisci ISBN da rimuovere: ");
                    String isbnRimuovi = scanner.nextLine();
                    try {
                        archivio.rimuoviElemento(isbnRimuovi);
                        System.out.println("Elemento rimosso con successo!");
                    } catch (NoSuchElementException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 5: // Cerca per anno pubblicazione
                    System.out.print("Inserisci anno pubblicazione: ");
                    int annoCerca = scanner.nextInt();
                    scanner.nextLine();
                    archivio.cercaPerAnno(annoCerca).forEach(System.out::println);
                    break;

                case 6: // Cerca per autore
                    System.out.print("Inserisci autore: ");
                    String autoreCerca = scanner.nextLine();
                    archivio.cercaPerAutore(autoreCerca).forEach(System.out::println);
                    break;

                case 7: // Aggiorna elemento
                    System.out.print("Inserisci ISBN dell'elemento da aggiornare: ");
                    String isbnAggiorna = scanner.nextLine();
                    break;

                case 8: // Statistiche
                    archivio.stampaStatistiche();
                    break;

                case 9: // Stampa catalogo
                    archivio.stampaCatalogo();
                    break;

                case 10: // Esci
                    System.out.println("Uscita dall'applicazione...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opzione non valida.");
            }
        }
    }
}
