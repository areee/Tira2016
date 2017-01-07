
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Prioriteettijonon abstraktin tietotyypin toteutus kekona.
 *
 * @author ylhaart
 */
public class HeapSimplePriorityQueue implements PriorityQueueInterface {

    /* Keko T, jossa ovat solmuissa talletettuina prioriteettijonon alkiot 
    ja avaimet:*/
    BinaryTree T;
    HeapNode last; // Viittaus keon T viimeisen solmun paikkaan.
    /* Vertain, joka määrittelee avainten täydellisen järjestyksen relaation:*/
    Comparator comparator;

    /**
     * Keko, joka on toteutettu käyttäen linkitettyä binääripuuta. Kun luodaan
     * uusi keko, luodaan myös uusi binääripuu, viimeiseen kekosolmuun viittaava
     * solmu sekä vertain.
     */
    public HeapSimplePriorityQueue() {
        T = new BinaryTree();
        last = null; // tyhjässä keossa ei ole vielä viimeistä solmua
        comparator = new Comparator();
    }

    @Override
    public HeapNode insertItem(int k, String e) throws InvalidKeyException {
        // Jos vertaimen mukaan avain ei ole vertailtavissa:
        if (!comparator.isComparable(k)) {
            throw new InvalidKeyException("Avain ei kelpaa.");
        }
        // Jos avain on vertailtavissa:
        HeapNode z;

        // Jos keko on ennestään tyhjä:
        if (isEmpty()) { //root == null
            z = T.addRoot(new KeyElementPair(k, e));
        } // Jos keko ei ole tyhjä:
        else {
            // Jos avain löytyy jo keosta:
            if (keyIsAlreadyInHeap(k)) {
                throw new InvalidKeyException("Avain on jo keossa.");
            } else if (keyIsZero(k)) {
                // Jos avain on arvoltaan nolla:
                throw new InvalidKeyException("Avain on nolla.");
            }
            // Etsitään uudelle solmulle lisäyspaikka käyttäen apuna keon viimeistä solmua:
            z = last;
            try {

                while (!T.isRoot(z) && !isLeftChild(z)) {
                    try {
                        /* Niin kauan kuin solmu ei ole juuri eikä vasen lapsi
                        siirrytään keossa ylöspäin:*/
                        z = T.parent(z);

                    } catch (BoundaryViolationException
                            | InvalidPositionException ex) {
                        System.out.println("Solmu on juuri:\n" + ex);
                    }
                }
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            } catch (EmptyTreeException ex) {
                System.out.println("Puu on tyhjä:\n" + ex);
            }
            try {

                if (!T.isRoot(z)) {
                    try {
                        /* Jos solmu ei ole juuri, asetetaan z oikeaan 
                        sisarukseen:*/
                        z = rightSibling(z);

                    } catch (BoundaryViolationException
                            | InvalidPositionException ex) {
                        System.out.println("Solmu on lehti:\n" + ex);
                    }
                }
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            } catch (EmptyTreeException ex) {
                System.out.println("Puu on tyhjä:\n" + ex);
            }
            try {

                while (!T.isExternal(z)) {
                    /*Niin kauan kuin solmu ei ole lehti, siirrytään solmun 
                    vasempaan lapseen:*/
                    z = T.leftChild(z);
                }
            } catch (InvalidPositionException ex) {
                System.out.println("Ongelma solmun sijainnissa:\n" + ex);
            }
        }
        try {
            /*Tehdään nykyisestä lehtisolmusta z sisäsolmu lisäämällä sille tyhjät 
            lehtisolmut lapsiksi:*/
            T.expandExternal(z, new KeyElementPair(), new KeyElementPair());

        } catch (InvalidPositionException ex) {
            System.out.println("Kyseessä on sisäsolmu:\n" + ex);
        }
        try {
            // Asetetaan nykyiseen solmuun z uuden avaimen ja uuden merkkijonon:
            T.replace(z, new KeyElementPair(k, e));

        } catch (InvalidPositionException ex) {
            System.out.println("Ongelma solmun sijainnissa:\n" + ex);
        }

        last = z; // asetetaan viimeiseksi solmuksi juuri lisätty uusi solmu
        /* Suoritetaan uuden solmun ylöskupliminen, jos avain on pienempi kuin 
        solmun vanhempisolmut:*/
        z = upHeapBubbling(z);
        return z;
    }

    /**
     * Palauttaa solmun z vasemman sisarussolmun.
     *
     * @param z HeapNode
     * @return HeapNode
     * @throws InvalidPositionException
     * @throws BoundaryViolationException
     */
    private HeapNode leftSibling(HeapNode z) throws InvalidPositionException,
            BoundaryViolationException {
        return T.leftChild(T.parent(z));
    }

    /**
     * Palauttaa solmun z oikean sisarussolmun.
     *
     * @param z HeapNode
     * @return HeapNode
     * @throws InvalidPositionException
     * @throws BoundaryViolationException
     */
    private HeapNode rightSibling(HeapNode z) throws InvalidPositionException,
            BoundaryViolationException {
        return T.rightChild(T.parent(z));
    }

    /**
     * Selvittää, löytyykö avain jo keosta.
     *
     * @param key int
     * @return boolean
     */
    private boolean keyIsAlreadyInHeap(int key) {
        String print = "";
        /* Hyödynnetään binääripuun esijärjestystulostus-metodia keon solmujen 
        selvittämiseen:*/
        print = T.preorderPrint(T, T.root(), print, 0);
        // Jaetaan esijärjestystulostus taulukkoon rivinvaihtojen mukaan:
        String[] split1 = print.split("\n");

        // Käydään taulukko läpi:
        for (String string1 : split1) {
            /* Jaetaan em. taulukon rivit edelleen uuteen taulukkoon 
            välilyöntien mukaan:*/
            String[] split2 = string1.split("  ");
            // Käydään toinenkin taulukko läpi:
            for (String string2 : split2) {
                if (string2.equals("" + key)) {
                    return true; // avain löytyi taulukosta
                }
            }
        }
        return false;
    }

    /**
     * Selvittää, onko avain arvoltaan nolla.
     *
     * @param key int
     * @return boolean
     */
    private boolean keyIsZero(int key) {
        return key == 0;
    }

    /**
     * Suoritetaan solmun ylöskupliminen sen mukaan, onko avain pienempi kuin
     * solmun vanhempisolmut.
     *
     * @param z HeapNode
     * @return HeapNode
     */
    private HeapNode upHeapBubbling(HeapNode z) {
        HeapNode u = null;
        try {
            while (!T.isRoot(z)) { // niin kauan kuin solmu ei ole keon juuri
                try {

                    u = T.parent(z);
                } catch (Exception ex) {
                    System.out.println("Kyseessä on juuri:\n" + ex);
                }

                /*Jos vanhempisolmu u on pienempi kuin varsinainen solmu z, 
                ylöskuplinta voidaan lopettaa:*/
                if (comparator.isLessThanOrEqualTo(keyOfPosition(u),
                        keyOfPosition(z))) {
                    break;
                }

                /* Vaihdetaan vanhempisolmun u ja varsinaisen solmun z avainten 
                ja merkkijonojen paikkoja keskenään:*/
                T.swap(u, z);
                z = u;
            }

        } catch (InvalidPositionException ex) {
            System.out.println("Ongelma solmun sijainnissa:\n" + ex);
        } catch (EmptyTreeException ex) {
            System.out.println("Puu on tyhjä:\n" + ex);
        }
        return z;
    }

    /**
     * Palauttaa nykyisen solmun avaimen arvon.
     *
     * @param p HeapNode
     * @return int
     * @throws InvalidPositionException
     */
    private int keyOfPosition(HeapNode p) throws InvalidPositionException {
        return p.getKey();
    }

    /**
     * Selvittää, onko annettu solmu p vasen lapsi.
     *
     * @param p HeapNode
     * @return boolean
     * @throws InvalidKeyException
     */
    public boolean isLeftChild(HeapNode p) throws InvalidKeyException {
        try {
            return T.isLeftChild(p);

        } catch (BoundaryViolationException | InvalidPositionException ex) {
            System.out.println("Poikkeus:\n" + ex);
            return false;
        }
    }

    /**
     * Selvittää, onko annettu solmu p oikea lapsi.
     *
     * @param p HeapNode
     * @return boolean
     * @throws InvalidKeyException
     */
    public boolean isRightChild(HeapNode p) throws InvalidKeyException {
        try {
            return T.isRightChild(p);
        } catch (BoundaryViolationException | InvalidPositionException ex) {
            System.out.println("Poikkeus:\n" + ex);
            return false;
        }
    }

    @Override
    public HeapNode removeMinElement() throws EmptyTreeException {
        // Jos keko on tyhjä, ei voida poistaa:
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        // Pienimmän avaimen alkio on juuressa:
        HeapNode r = T.root();
        HeapNode w = last;
        HeapNode removedNode = null;

        /* Alkion poistoalgoritmi toimii eri tavalla riippuen siitä, onko solmuja
        1, 2 vai enemmän.*/
        switch (size()) {

            case 1:
                /*Jos alkio on keon ainoa solmu, riittää pelkästään poistaa 
                solmu:*/
                try {

                    T.remove(r);
                    last = null;

                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }
                break;

            case 2:
                r = swap(r, w);
                try {
                    // Poistetaan juuren lapseen siirretty ex-juuri:
                    T.remove(r);
                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }

                /* Tämän jälkeen vanha juuri on poistettu ja uusi juuri on 
                viimeinen:*/
                last = T.root();
                break;

            // Jos keon koko on suurempi kuin 2:
            default:

                // Vaihdetaan juuren ja viimeisen solmun paikkoja keskenään sekä 
                // asetetaan poistettava juuri ex-viimeiseen solmuun:
                removedNode = swap(r, w);

                // Etsitään uusi last-solmu.
                // Jos removedRoot on oikeanpuoleinen lapsi, asetetaan last-
                // muuttuja sen vanhemman vasemmanpuoleiseen lapseen.
                if (isRightChild(w)) {
                    HeapNode parent = w.getParent();
                    HeapNode left = parent.getLeft();
                    last = left;
                } else {

                    /* Muussa tapauksessa removedRoot on itse vasemmanpuoleinen
                    lapsi vanhempaansa nähden.*/
                    try {
                        /*Niin kauan kuin tämä vasempi lapsi ei ole juuri tai 
                        oikea lapsi:*/
                        while (!T.isRoot(w) && !isRightChild(w)) {
                            w = T.parent(w);
                        }
                    } catch (InvalidPositionException ex) {
                        System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                    }

                    // Jos solmu on oikea lapsi, siirry vasemmanpuoleiseen sisarukseen:
                    if (isRightChild(w)) {
                        w = T.sibling(w);

                    }

                    try {
                        // Siirrytään alaspäin, kunnes saavutetaan oikeanpuoleinen lehtisolmu:
                        while (!T.rightKeyZero(w)) {
                            w = T.rightChild(w);
                        }
                    } catch (InvalidPositionException ex) {
                        System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                    }
                    last = w;
                }


                /* Kun uuden last-solmun sijainti on asetettu, poistetaan vanha
                juuri:*/
                try {
                    T.remove(w);

                } catch (InvalidPositionException ex) {
                    System.out.println("Ongelma solmun sijainnissa:\n" + ex);
                }

                /* Tarkistetaan seuraavaksi, onko kekojärjestysominaisuus mennyt 
                rikki poiston yhteydessä. Jos on, korjataan:*/
                downHeapBubbling();
                break;
        }
        return removedNode;
    }

    /**
     * Alaskupliminen.
     *
     * @throws EmptyTreeException
     */
    private void downHeapBubbling() throws EmptyTreeException {
        HeapNode r = T.root();
        HeapNode s;
        try {
            /* Niin kauan kuin r on sisäsolmu (eli jomman kumman lapsen
            avain ei ole nolla):*/
            while (!T.leftKeyZero(r) || !T.rightKeyZero(r)) {

                /*Jos juuren r vasen lapsi on sen ainoa lapsisolmu, olkoon
                s juuren r vasen lapsi.*/
                if (!T.hasRightNotZero(r)) {
                    s = r.getLeft();
                } else { // juurella on myös oikea lapsi
                    HeapNode left = r.getLeft();
                    HeapNode right = r.getRight();
                    // Jos vasen lapsi < oikea lapsi:
                    if (comparator.isLessThan(keyOfPosition(left),
                            keyOfPosition(right))) {
                        s = left;
                    } else {
                        s = right;
                    }
                }

                if (comparator.isLessThan(keyOfPosition(r), keyOfPosition(s))) {
                    break;
                }
                T.swap(r, s);
                r = s;
            }
        } catch (InvalidPositionException ex) {
            System.out.println("Ongelma solmun sijainnissa:\n" + ex);
        }
    }

    /**
     * Vaihtaa kahden solmun avaimen ja merkkijonon arvoa keskenään sekä vaihdon
     * jälkeen asettaa poistettavan juuren aiempaan viimeiseen solmuun.
     *
     * @param r HeapNode
     * @param w HeapNode
     * @return HeapNode
     */
    private HeapNode swap(HeapNode r, HeapNode w) {
        /* Jos keossa on kaksi solmua (juuri ja juuren lapsi (=last)),
        vaihdetaan niiden paikkaa keskenään:*/
        try {
            T.swap(r, w);

        } catch (InvalidPositionException ex) {
            System.out.println("Ongelma solmun sijainnissa:\n" + ex);
        }
        return w;// poistettava juuri on nyt viimeisessä solmussa
    }

    @Override
    public HeapNode minKey() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        return T.root();
    }

    @Override
    public HeapNode minElement() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        return T.root();
    }

    /**
     * Palauttaa merkkijonona keon avaimet esijärjestyksessä.
     *
     * @return String
     * @throws EmptyTreeException
     */
    public String print() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Puu on tyhjä.");
        }
        String print = "";
        return T.preorderPrint(T, T.root(), print, 0);
    }

    @Override
    public int size() {
        return T.size();
    }

    @Override
    public boolean isEmpty() {
        return T.isEmpty();
    }

}
