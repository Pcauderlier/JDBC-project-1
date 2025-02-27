DROP TABLE IF EXISTS ArticleCommande;
DROP TABLE IF EXISTS Commande;
DROP TABLE IF EXISTS Article;
DROP TABLE IF EXISTS Client;



CREATE TABLE Client(
    ID_CLIENT INT NOT NULL AUTO_INCREMENT,
    NOM VARCHAR(50) NOT NULL,
    PRENOM VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID_CLIENT)
);
CREATE TABLE Commande(
    ID_COMMANDE INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NUM_COMMANDE VARCHAR(150) NOT NULL ,
    DATE_COMMANDE  DATETIME NOT NULL,
    CLIENT_ID INT NOT NULL,
    FOREIGN KEY (CLIENT_ID) REFERENCES Client(ID_CLIENT)
);
CREATE TABLE Article(
    ID_ARTICLE INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NOM VARCHAR(50) NOT NULL,
    DESCRIPTION TEXT NOT NULL,
    PRIX DOUBLE UNSIGNED NOT NULL,
    STOCK INT UNSIGNED NOT NULL
);
CREATE TABLE ArticleCommande(
    ARTICLE_ID INT NOT NULL,
    COMMANDE_ID INT NOT NULL,
    QUANTITE INT NOT NULL,
    PRIMARY KEY (ARTICLE_ID, COMMANDE_ID),
    FOREIGN KEY (ARTICLE_ID) REFERENCES Article(ID_ARTICLE),
    FOREIGN KEY (COMMANDE_ID) REFERENCES Commande(ID_COMMANDE)
);

