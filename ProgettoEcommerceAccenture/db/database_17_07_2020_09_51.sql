DROP TABLE COUPON;
DROP TABLE ORDINESTORICO;
DROP TABLE DETTAGLIO2;
DROP TABLE UTENTEORDINE;
DROP TABLE APPARTIENE_CATEGORIA;
DROP TABLE DETTAGLIO;
DROP TABLE ORDINE;
DROP TABLE CATEGORIA;
DROP TABLE PRODOTTO;
DROP TABLE INDIRIZZO;
DROP TABLE UTENTE;
DROP SEQUENCE INDIRIZZO_SEQ;
DROP SEQUENCE PRODOTTO_SEQ;
DROP SEQUENCE CATEGORIA_SEQ;
DROP SEQUENCE ORDINE_SEQ;
DROP SEQUENCE DETTAGLIO_SEQ;
DROP SEQUENCE COUPON_SEQ;

---------------------TABELLE-------------------

CREATE TABLE UTENTE (
	username VARCHAR2(30) PRIMARY KEY,
	password VARCHAR(16) NOT NULL,
	nome VARCHAR2(30) NOT NULL,
	cognome VARCHAR2(30) NOT NULL,
	data_nascita DATE
);

CREATE TABLE INDIRIZZO(
	id_indirizzo INT PRIMARY KEY,
	via VARCHAR2(40) NOT NULL,
	numero INT NOT NULL,
	citta VARCHAR2(30) NOT NULL,
	cap INTEGER NOT NULL,
	utente VARCHAR2(30) NOT NULL,
	ultimo_indirizzo char(1) check (ultimo_indirizzo in ( 'Y', 'N' )),
	CONSTRAINT FK_US_IND FOREIGN KEY(utente) REFERENCES UTENTE(username) ON DELETE CASCADE
);

CREATE TABLE ORDINE(
	id_ordine INT PRIMARY KEY,
	data_ordine DATE NOT NULL,
    prezzo_totale NUMBER(8,2)NOT NULL,
	utente VARCHAR2(30) NOT NULL,
	indirizzo INT NOT NULL,
	CONSTRAINT FK_OR_IND FOREIGN KEY(indirizzo) REFERENCES INDIRIZZO(id_indirizzo) ON DELETE CASCADE,
	CONSTRAINT FK_OR_US FOREIGN KEY(utente) REFERENCES UTENTE(username) ON DELETE CASCADE
);

CREATE TABLE UTENTEORDINE(
    ID_ORDINE   INT PRIMARY KEY,
    USERNAME    VARCHAR2(30)
);

CREATE TABLE PRODOTTO(
    id_prodotto INT PRIMARY KEY,
    prezzo NUMBER(7,2) NOT NULL,
    quantita_disponibile INT NOT NULL,
    nome VARCHAR2(50) NOT NULL,
    descrizione VARCHAR2(100),
    url_immagine VARCHAR2(100)
);

CREATE TABLE DETTAGLIO(
    id_dettaglio INT NOT NULL,
	id_ordine INT NOT NULL,
	id_prodotto INT NOT NULL,
	quantita_acquistata INT NOT NULL,
    prezzo_unitario NUMBER(7,2) NOT NULL,
	CONSTRAINT PK_DETTAGLIO PRIMARY KEY(id_dettaglio,id_ordine,id_prodotto),
	CONSTRAINT FK_DE_ORD FOREIGN KEY (id_ordine) REFERENCES Ordine(id_ordine) ON DELETE CASCADE,
	CONSTRAINT FK_DE_PRO FOREIGN KEY(id_prodotto) REFERENCES Prodotto(id_prodotto) ON DELETE CASCADE 
);

CREATE TABLE DETTAGLIO2(
    id_dettaglio INT NOT NULL,
	id_ordine INT NOT NULL,
	id_prodotto INT NOT NULL,
	quantita_acquistata INT NOT NULL,
    prezzo_unitario NUMBER(7,2) NOT NULL,
	CONSTRAINT PK_DETTAGLIO2 PRIMARY KEY(id_dettaglio,id_ordine,id_prodotto)
);

CREATE TABLE CATEGORIA(
	id_categoria INT PRIMARY KEY,
	nome VARCHAR2(30) NOT NULL,
    url_immagine VARCHAR2(100)
);

CREATE TABLE APPARTIENE_CATEGORIA(
	id_prodotto INT NOT NULL,
	id_categoria INT NOT NULL,
	CONSTRAINT pk_app PRIMARY KEY(id_prodotto,id_categoria),
	CONSTRAINT fk_app_pro FOREIGN KEY(id_prodotto) REFERENCES Prodotto(id_prodotto) ON DELETE CASCADE,
	CONSTRAINT fk_app_cat FOREIGN KEY(id_categoria) REFERENCES Categoria(id_categoria) ON DELETE CASCADE
);

CREATE TABLE ORDINESTORICO(
    id_ordine INT NOT NULL,
    id_prodotto INT NOT NULL,
    username VARCHAR2(30) NOT NULL,
    quantita_acquistata INT NOT NULL,
    prezzo_totale NUMBER(7,2) NOT NULL,
    CONSTRAINT PK_OR_STORICO PRIMARY KEY(id_ordine,id_prodotto),
    CONSTRAINT fk_sto_pro FOREIGN KEY(id_prodotto) REFERENCES Prodotto(id_prodotto) ON DELETE CASCADE,
    CONSTRAINT fk_sto_user FOREIGN KEY(username) REFERENCES UTENTE(username) ON DELETE CASCADE
);

CREATE TABLE COUPON(
    id_coupon INT NOT NULL,
    username VARCHAR2(30) NOT NULL,
    importo NUMBER(8,2) NOT NULL check (importo in (5, 10, 20)),
    data_emissione DATE DEFAULT SYSDATE,
    data_scadenza DATE DEFAULT (SYSDATE + 20),
    utilizzato char(1) check (utilizzato in ( 'Y', 'N' )),
    CONSTRAINT PK_COUP PRIMARY KEY(id_coupon, username),
    CONSTRAINT FK_COUP_US FOREIGN KEY(username) REFERENCES UTENTE(username) ON DELETE CASCADE
);

---------------------SEQUENZE---------------------
CREATE SEQUENCE indirizzo_seq
minvalue 0
start with 4
increment by 1
nomaxvalue;


CREATE SEQUENCE PRODOTTO_SEQ
INCREMENT BY 1
START WITH 31
NOMAXVALUE;


CREATE SEQUENCE categoria_seq
minvalue 0
start with 7
increment by 1
nomaxvalue;

CREATE SEQUENCE ORDINE_SEQ
MINVALUE 0
START WITH 5
INCREMENT BY 1
NOMAXVALUE;

CREATE SEQUENCE DETTAGLIO_SEQ
MINVALUE 0
START WITH 8
INCREMENT BY 1
NOMAXVALUE;

CREATE SEQUENCE COUPON_SEQ
MINVALUE 0
START WITH 10
INCREMENT BY 1
NOMAXVALUE;

---------------------VISTE--------------------

CREATE OR REPLACE VIEW LISTA_PRODOTTI_CATEGORIA AS
SELECT AC.ID_CATEGORIA,P.ID_PRODOTTO,P.PREZZO,P.QUANTITA_DISPONIBILE,P.NOME,P.DESCRIZIONE,P.URL_IMMAGINE
FROM PRODOTTO P
INNER JOIN APPARTIENE_CATEGORIA AC ON P.ID_PRODOTTO=AC.ID_PRODOTTO;

CREATE OR REPLACE VIEW LISTA_PRODOTTI_ORDINE AS
SELECT D.ID_ORDINE, P.ID_PRODOTTO,D.PREZZO_UNITARIO,D.QUANTITA_ACQUISTATA,P.NOME,P.DESCRIZIONE,P.URL_IMMAGINE
FROM PRODOTTO P
INNER JOIN DETTAGLIO D ON P.ID_PRODOTTO=D.ID_PRODOTTO;

CREATE OR REPLACE VIEW ORDINESTORICOVIEW AS
SELECT D.ID_ORDINE,D.ID_PRODOTTO,UO.USERNAME,D.QUANTITA_ACQUISTATA,D.PREZZO_UNITARIO
FROM DETTAGLIO2 D
INNER JOIN UTENTEORDINE UO ON D.ID_ORDINE=UO.ID_ORDINE;

---------------------TRIGGER------------------------------
CREATE OR REPLACE TRIGGER CANCELLA_INDIRIZZO_PARTE_1
BEFORE DELETE 
ON DETTAGLIO
FOR EACH ROW
DECLARE
VAR_TOTALE  NUMBER(7,2);
BEGIN
    VAR_TOTALE:=:OLD.PREZZO_UNITARIO*:OLD.QUANTITA_ACQUISTATA;
    INSERT INTO DETTAGLIO2 VALUES(:OLD.ID_DETTAGLIO,:OLD.ID_ORDINE,:OLD.ID_PRODOTTO,:OLD.QUANTITA_ACQUISTATA,VAR_TOTALE);
END;
/

CREATE OR REPLACE TRIGGER CANCELLA_INDIRIZZO_PARTE_2
BEFORE DELETE
ON ORDINE 
FOR EACH ROW
BEGIN
    INSERT INTO UTENTEORDINE VALUES(:OLD.ID_ORDINE,:OLD.UTENTE);
END;
/

CREATE OR REPLACE TRIGGER CANCELLA_INDIRIZZO_PARTE_3
AFTER DELETE
ON INDIRIZZO
DECLARE
VAR_ID_ORDINE               INT;
VAR_ID_PRODOTTO             INT;
VAR_USERNAME                VARCHAR2(30);
VAR_QUANTITA_ACQUISTATA     INT;
VAR_PREZZO_TOTALE           NUMBER(7,2);
CURSOR C1 IS
    SELECT ID_ORDINE,ID_PRODOTTO,USERNAME,QUANTITA_ACQUISTATA,PREZZO_UNITARIO FROM ORDINESTORICOVIEW;
BEGIN
    OPEN C1;
    LOOP
        FETCH C1 INTO  VAR_ID_ORDINE,VAR_ID_PRODOTTO,VAR_USERNAME,VAR_QUANTITA_ACQUISTATA,VAR_PREZZO_TOTALE;
        EXIT WHEN C1%NOTFOUND;
        INSERT INTO ORDINESTORICO VALUES(VAR_ID_ORDINE,VAR_ID_PRODOTTO,VAR_USERNAME,VAR_QUANTITA_ACQUISTATA,VAR_PREZZO_TOTALE);
    END LOOP;
    CLOSE C1;
    DELETE FROM DETTAGLIO2;
    DELETE FROM UTENTEORDINE;
END;
/

create or replace trigger GENERA_COUPON  
   before insert on COUPON
   for each row 
begin  
   if inserting then 
      if :NEW.ID_COUPON is null then 
         select COUPON_SEQ.nextval into :NEW.ID_COUPON from dual; 
      end if; 
   end if; 
end;
/

---------------------INSERIMENTO VALORI TEST--------------
INSERT INTO UTENTE VALUES('Utente1','PasswordUtente1$','Nome1','Cognome1','15-LUG-2020');
INSERT INTO UTENTE VALUES('Utente2','PasswordUtente2$','Nome2','Cognome2','16-LUG-2020');
INSERT INTO UTENTE VALUES('Utente3','PasswordUtente3$','Nome3','Cognome3','17-LUG-2020');


INSERT INTO INDIRIZZO VALUES(indirizzo_seq.nextval,'via1',1,'citta1',11111,'Utente1','N');
INSERT INTO INDIRIZZO VALUES(indirizzo_seq.nextval,'via2',2,'citta2',22222,'Utente2','Y');
INSERT INTO INDIRIZZO VALUES(indirizzo_seq.nextval,'via3',3,'citta3',33333,'Utente1','Y');
INSERT INTO INDIRIZZO VALUES(indirizzo_seq.nextval,'via4',4,'citta4',44444,'Utente3','N');
INSERT INTO INDIRIZZO VALUES(indirizzo_seq.nextval,'via5',5,'citta5',55555,'Utente3','N');
INSERT INTO INDIRIZZO VALUES(indirizzo_seq.nextval,'via6',6,'citta6',66666,'Utente3','Y');

insert into categoria values(1,'Articoli scolastici','img/diario.png');
insert into categoria values(2,'Computer e telefoni','img/tecnologia.png');
insert into categoria values(3,'CD musicale','img/cd1.png');
insert into categoria values(4,'Abbigliamento','img/abbigliamento.png');
insert into categoria values(5,'Alimentari','img/cibi.png');
insert into categoria values(6,'Gioielli','img/gioielli.png');

insert into prodotto values(16,24.00,10,'Levis','T-shirt','img/abbigliamento5.png'); 
insert into prodotto values(17,600.00,10,'Woolrich','Cappotto donna','img/abbigliamento1.png');
insert into prodotto values(18,60.99,10,'Levis','Jeans','img/abbigliamento2.png');
insert into prodotto values(19,46.60,10,'Kangol','Cappello uomo Porkpie nero','img/abbigliamento3.png');
insert into prodotto values(20,39.95,10,'Desigual','Foulard donna nero','img/abbigliamento4.png');

insert into prodotto values(21,5.50,10,'Rio Mare','Tonno all''olio d''oliva extravergine','img/alimentari.png');
insert into prodotto values(22,4.50,10,'Negroni','Prosciutto Crudo','img/alimentari1.png');
insert into prodotto values(23,4.49,10,'Gentilini Novellini','Biscotti 250gr','img/alimentari2.png');
insert into prodotto values(24,1.90,10,'Rummo','Pasta di semola di grano duro','img/alimentari3.png');
insert into prodotto values(25,15.90,10,'Barbera','Vino rosso','img/alimentari4.png');

insert into prodotto values(26,50.95,10,'Swarovski','Collana','img/gioielli5.png');
insert into prodotto values(27,69.00,10,'Pandora','Anello','img/gioielli1.png');
insert into prodotto values(28,19.95,10,'Pandora','Bracciale argento','img/gioielli2.png');
insert into prodotto values(29,48.30,10,'Soho','Orecchini Soho Gold','img/gioielli3.png');
insert into prodotto values(30,20.00,10,'Pandora','Orecchini argento','img/gioielli4.png');

insert into prodotto values(4,17.99,10,'Cartuccia stampante','Cartuccia Originale HP 301 nero','img/prodotto1.png');
insert into prodotto values(12,9.33,18,'Buste trasparenti','100pz bustre trasparenti forate','img/prodotto2.png');
insert into prodotto values(10,5.08,4,'Evidenziatore ','Evidenziatore a colori pastello','img/prodotto3.png');
insert into prodotto values(11,62.99,15,'Zaino','Zaino Unisex Adulto','img/prodotto4.png');
insert into prodotto values(2,15.00,3,'Astuccio','Eastpak Benchmark Single Astuccio, 21 cm, Bianco','img/prodotto5.png');
insert into prodotto values(13,649.00,17,'Samsung Galaxy S20','Smartphone, Display 6.2" Dynamic AMOLED 2X, 3 Fotocamere Posteriori, 128 GB Espandibili','img/prodotto6.png');
insert into prodotto values(5,17.99,10,'Gigaset E260','Telefono Corldess, Tasti Grandi, Numeri sul Display Grandi','img/prodotto7.png');
insert into prodotto values(14,299.00,5,'Chuwi- HeroBook Pro','computer portatile Ultrabook, 14,1 pollici, Intel Gemini Lake N4000, fino a 2.6 GHz','img/prodotto8.png');
insert into prodotto values(1,2530.00,3,'Apple MacBook Pro','Apple MacBook Pro 16 pollici, 16GB RAM, Archiviazione 512GB','img/prodotto9.png');
insert into prodotto values(7,1399.00,16,'Msi trident ','Msi trident a 9sc-807eu computer desktop gaming, intel core i5-9400f, nvidia rtx 2060 super 8gb','img/prodotto10.png');
insert into prodotto values(6,15.75,8,'Radio Italia Summer 2020','Audio CD','img/prodotto11.png');
insert into prodotto values(3,20.99,1,'Gemelli - CD autografato','Audio CD','img/prodotto12.png');
insert into prodotto values(8,19.90,20,'Rough And Rowdy Ways','Audio CD','img/prodotto13.png');
insert into prodotto values(9,14.51,8,'Living in a ghost town','Vinile 10" colorato','img/prodotto14.png');
insert into prodotto values(15,13.05,6,'Musica Da Giostra Vol.7','Dj Matrix e Matt Joe (Artista) Formato: Audio CD','img/prodotto15.png');

insert into appartiene_categoria values(1,2);
insert into appartiene_categoria values(2,1);
insert into appartiene_categoria values(3,3);
insert into appartiene_categoria values(4,1);
insert into appartiene_categoria values(5,2);
insert into appartiene_categoria values(6,3);
insert into appartiene_categoria values(7,2);
insert into appartiene_categoria values(8,3);
insert into appartiene_categoria values(9,3);
insert into appartiene_categoria values(10,1);
insert into appartiene_categoria values(11,1);
insert into appartiene_categoria values(12,1);
insert into appartiene_categoria values(13,2);
insert into appartiene_categoria values(14,2);
insert into appartiene_categoria values(15,3);
insert into appartiene_categoria values(16,4);
insert into appartiene_categoria values(17,4);
insert into appartiene_categoria values(18,4);
insert into appartiene_categoria values(19,4);
insert into appartiene_categoria values(20,4);
insert into appartiene_categoria values(21,5);
insert into appartiene_categoria values(22,5);
insert into appartiene_categoria values(23,5);
insert into appartiene_categoria values(24,5);
insert into appartiene_categoria values(25,5);
insert into appartiene_categoria values(26,6);
insert into appartiene_categoria values(27,6);
insert into appartiene_categoria values(28,6);
insert into appartiene_categoria values(29,6);
insert into appartiene_categoria values(30,6);

INSERT INTO ORDINE VALUES(1,'15-GIU-2020',192.00,'Utente1',4);
INSERT INTO ORDINE VALUES(2,'17-GIU-2020',1.25,'Utente2',5);
INSERT INTO ORDINE VALUES(3,'18-GIU-2020',114.22,'Utente1',6);
INSERT INTO ORDINE VALUES(4,'19-GIU-2020',589.5,'Utente1',7);

INSERT INTO DETTAGLIO VALUES(1,1,5,2,10.50);
INSERT INTO DETTAGLIO VALUES(2,1,8,7,1.50);
INSERT INTO DETTAGLIO VALUES(3,1,13,1,150);
INSERT INTO DETTAGLIO VALUES(4,2,7,1,1.25);
INSERT INTO DETTAGLIO VALUES(5,3,1,4,25.88);
INSERT INTO DETTAGLIO VALUES(6,3,17,1,10.70);
INSERT INTO DETTAGLIO VALUES(7,4,6,9,65.50);

INSERT INTO COUPON(username, importo, utilizzato) VALUES('Utente1', 10, 'Y');
INSERT INTO COUPON(username, importo, utilizzato) VALUES('Utente2', 5, 'N');
INSERT INTO COUPON(username, importo, utilizzato) VALUES('Utente3', 20, 'N');
INSERT INTO COUPON(username, importo, utilizzato) VALUES('Utente2', 5, 'Y');
INSERT INTO COUPON(username, importo, utilizzato) VALUES('Utente1', 10, 'N');

COMMIT;
