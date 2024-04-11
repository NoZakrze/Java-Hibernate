package org.example;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        System.out.println("Wpisz komendy: ");
        Scanner scanner = new Scanner(System.in);
        boolean koniec = false;
        while(!koniec)
        {
            String linia = scanner.nextLine();
            String[] dane = linia.split(" ");
            if(dane[0].equals("koniec"))
            {
                koniec = true;
            }
            else if(dane[0].equals("dodaj"))
            {
                if(dane[1].equals("Mag"))
                {
                    Wieza siedziba = entityManager.find(Wieza.class, dane[4]);
                    Mag nowy = new Mag(dane[2],Integer.parseInt(dane[3]),siedziba);
                    transaction.begin();
                    entityManager.persist(nowy);
                    transaction.commit();
                    siedziba.getMagowie().add(nowy);
                }
                else if(dane[1].equals("Wieza"))
                {
                    Wieza nowy = new Wieza(dane[2],Integer.parseInt(dane[3]));
                    transaction.begin();
                    entityManager.persist(nowy);
                    transaction.commit();
                }
            }
            else if(dane[0].equals("usun"))
            {
                if(dane[1].equals("Mag"))
                {
                   Mag usun = entityManager.find(Mag.class, dane[2]);
                    if (usun != null)
                    {
                        Wieza w = usun.getWieza();
                        int index = 0;
                        for(Mag m : w.getMagowie())
                        {
                            if(m.getImie().equals(usun.getImie()))
                            {
                                break;
                            }
                            index++;
                        }
                        w.getMagowie().remove(index);
                        transaction.begin();
                        entityManager.remove(usun);
                        transaction.commit();
                    }
                }
                else if(dane[1].equals("Wieza"))
                {
                    Wieza usun = entityManager.find(Wieza.class, dane[2]);
                    if (usun != null)
                    {
                        transaction.begin();
                        entityManager.remove(usun);
                        transaction.commit();
                    }
                }
            }
            else if(dane[0].equals("wyswietl"))
            {
                List<Wieza> odp1 = entityManager.createQuery("FROM Wieza", Wieza.class).getResultList();
                for (Wieza a : odp1)
                {
                    System.out.println(a);
                }
                List<Mag> odp2 = entityManager.createQuery("FROM Mag", Mag.class).getResultList();
                for (Mag a : odp2)
                {
                    System.out.println(a);
                }
            }
        }
        List<Mag> magowieZWiekszymPoziomem = entityManager.createQuery("SELECT m FROM Mag m WHERE m.poziom > 2",
                        Mag.class).getResultList();
        for (Mag a : magowieZWiekszymPoziomem)
        {
            System.out.println(a);
        }

        entityManager.close();
        entityManagerFactory.close();
        /*
        List<Wieza> nizszeWieze = entityManager.createQuery(
                        "SELECT w FROM Wieza w WHERE w.wysokosc < 1000",
                        Wieza.class) .getResultList();
        List<Mag> magowieWyzejNizWWiezy = entityManager.createQuery(
                        "SELECT m FROM Mag m JOIN m.wieza w WHERE w.id = wieza1 AND m.poziom > 2",
                        Mag.class).getResultList();

        */
    }
}


